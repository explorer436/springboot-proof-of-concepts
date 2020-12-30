/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

/**
 * Handler to output soap messages to file system.
 * TODO: Move to property place
 * 
 * @author Jonathan Lemon (original author for QBE)
 * @author Matthew Duffy (adapted for eSales)
 * @author Jim Crume (adapted for PI)
 * @author Tom Leary (adapted temporarily until service recording decision is made)
 */
public class WebServiceRecorderHandler implements SOAPHandler<SOAPMessageContext> {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	/** 
	 * The Constant SUB_DIRECTORY. 
	 * Log file output sub directory
	 */
	private static final String SUB_DIRECTORY = "PipeTempXmls";

	static {
		// Clear existing directory
		FileUtils.clearDirectory(SUB_DIRECTORY);
	}

	/** The Constant DEFAULT_OPERATION_NAME. */
	// Default operation name
	private static final String DEFAULT_OPERATION_NAME = "UNKNOWN";

	/**
	 * Close.
	 *
	 * @param context the context
	 */
	@Override
	public void close(MessageContext context) {
	}

	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	/**
	 * Handle fault.
	 *
	 * @param context the context
	 * @return true, if successful
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		recordMessage(context);
		return true;
	}

	/**
	 * Handle message.
	 *
	 * @param context the context
	 * @return true, if successful
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		recordMessage(context);
		return true;
	}

	/**
	 * Method to record the soap message and dump to file if local.
	 *
	 * @param smc the smc
	 */
	private void recordMessage(SOAPMessageContext smc) {
		// Only log files for local development
		// if (StringUtils.equals(EnvironmentType.LOCAL.getValue(), environmentConfig.getEnvironment())) {
			try	{
				// Get log data
				String serviceOperation = getOperationName(smc);
				String xmlString = getXmlString(smc);

				// Send to file
				sendPayloadToFile(xmlString, serviceOperation);
			}
			catch (Exception e) {
				LOGGER.error("Exception in Hhandler: " + e);
			}
		// }
	}
	
	/**
	 * Returns the XML representation of the message for a message context.
	 *
	 * @param soapMessageContext the soap message context
	 * @return the xml string
	 * @pre soapMessageContext != null
	 * @post A non-null reference to a {@link String} containing the XML representation of the message associated to
	 * 	   <code>soapMessageContext</code> has been returned.
	 * @note An empty {@link String} may be returned if the message associated to <code>soapMessageContext</code> cannot
	 * 	   be serialized.
	 */
	private String getXmlString(SOAPMessageContext soapMessageContext) {
		// Assert preconditions
		// TODO Validate.notNull(soapMessageContext, "SOAPMessageContext cannot be null");

		// Serialize to an output stream and then to a String
		String xmlString = "";
		try {
			SOAPMessage soapMessage = soapMessageContext.getMessage();
			if (soapMessage != null) {
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				soapMessage.writeTo(bytes);
				xmlString = bytes.toString();
			}
		}
		catch (Exception e) {
			xmlString = "Failed to serialize";
			LOGGER.error("Failed to serialize");
		}

		// Return the XML String
		return xmlString;
	}
	
	/**
	 * Returns the service operation name of operation being executed.
	 *
	 * @param soapMessageContext the soap message context
	 * @return the operation name
	 * @pre soapMessageContext != null
	 * @post A non-null reference to a {@link String} representing the service operation name being executed has been
	 * 	   returned.
	 */
	private String getOperationName(SOAPMessageContext soapMessageContext) {
		// Assert preconditions
		// TODO Validate.notNull(soapMessageContext, "SOAPMessageContext cannot be null");

		// Default operation name
		String operationName = DEFAULT_OPERATION_NAME;

		try {
			// Get the soap message from the context
			SOAPMessage soapMessage = soapMessageContext.getMessage();
			if (soapMessage != null) {
				// Get the soap body
				SOAPBody body = soapMessage.getSOAPBody();
				if (body != null) {
					// The first node in the body should be the operation name
					Node requestNode = body.getFirstChild();
					while (null != requestNode) {
						operationName = requestNode.getLocalName();
						if (null != operationName) {
							break;
						}
						requestNode = requestNode.getNextSibling();
					}
				}
			}
		}
		catch (SOAPException e) {
			LOGGER.error("Failed to obtain operation");
		}

		// Return the operation name
		return operationName;
	}

	/**
	 * Writes the specified payload to a file on the files system.
	 *
	 * @param payload the payload
	 * @param serviceOperation the service operation
	 * @pre payload != null
	 * @pre payload.length() > 0
	 * @pre serviceOperation != null
	 * @pre serviceOperation.length() > 0
	 * @post The <code>payload</code> has been written to a file on the filesystem.
	 * @note This method is synchronized to avoid race conditions arising from file name contention on different
	 * 	   threads.
	 */
	public synchronized void sendPayloadToFile(String payload, String serviceOperation) {
		
		// Assert preconditions
		// TODO Validate.notEmpty(payload, "Payload cannot be empty");
		// TODO Validate.notEmpty(serviceOperation, "Service Operation cannot be empty");

		// Retrieve the system name
		// TODO: Put these in an enum for app.properties
		String systemName = System.getProperty("com.lmig.pi.systemname");
		// Default if empty
		if (StringUtils.isEmpty(systemName)) {
			systemName = "PIPE";
		}

		// Create filename
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		timeStamp = timeStamp.replace(':', '_');
		timeStamp = timeStamp.replace('.', '_');
		timeStamp = timeStamp.replace('-', '_');
		timeStamp = timeStamp.replace(' ', '_');
		
		String fileName = systemName
			+ "-"
			+ timeStamp
			+ "-"
			+ serviceOperation
			+ ".xml";
		
		// Output to the file
		PrintWriter writer = FileUtils.getPrintWriter(fileName, SUB_DIRECTORY);
		try {
			writer.write(payload);
			writer.close();
		}
		catch (Exception e) {
			// Eat the exception.
			LOGGER.error(e.getMessage());
		}
	}

}
