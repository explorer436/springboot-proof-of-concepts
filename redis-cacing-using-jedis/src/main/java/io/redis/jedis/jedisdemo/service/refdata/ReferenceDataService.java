/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.service.refdata;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.lmig.pm.domain.common.layers.service.messages.getreferencedatatable.v1_0.GetReferenceDataTableRequest;
import com.lmig.pm.domain.common.layers.service.messages.getreferencedatatable.v1_0.GetReferenceDataTableResponse;
import com.lmig.pm.domain.common.types.v1_0.SystemInformationType;
import com.lmig.pm.domain.common.types.v1_0.VersionInformationType;
import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceTableData;

import io.redis.jedis.jedisdemo.helper.EnvironmentConfig;
import io.redis.jedis.jedisdemo.helper.ReferenceDataLoadClient;

/**
 * ReferenceDataService.
 */
@Component
public class ReferenceDataService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	/** The reference data load client. */
	@Autowired
	private ReferenceDataLoadClient referenceDataLoadClient;
	
	@Autowired
	private EnvironmentConfig environmentConfig;
	
	/** The Constant ERROR_CONVERTING_TO_XML. */
	private static final String ERROR_CONVERTING_TO_XML = "Unexpected error converting to XML";
	
	private static final String EXECUTION_CONTEXT = "fully-integrated-mode";
	private static final String APPLICATION_NAME = "ReferenceDataClient";
	private static final String APPLICATION_VERSION = "1.0";
	private static final String APPLICATION_TYPE = "online";
	
	/** 
	 * A map to hold the key-value pairs that need to be set for 
	 * the attribute GetReferenceDataTableRequest/ClientInformation/Environment.
	 * 
	 */
	private static Map<String, String> environmentMap;
	static {
		environmentMap = new HashMap<>();
		environmentMap.put("local", "development");
		environmentMap.put("development", "development");
		environmentMap.put("test", "test");
	}
	
	/** 
	 * A map to hold the key-value pairs that need to be set for 
	 * the attribute GetReferenceDataTableRequest/ReleaseContext.
	 * 
	 */
	private static Map<String, String> releaseContextMap;
	static {
		releaseContextMap = new HashMap<>();
		releaseContextMap.put("local", "developmenta");
		releaseContextMap.put("development", "developmenta");
		releaseContextMap.put("test", "testa");
	}
	
	@Cacheable(cacheNames = "referenceDataCache", key = "#tableName")
	public ReferenceTableData getFromCache(String tableName) {
		return null;
	}

	@CachePut(cacheNames = "referenceDataCache", key = "#tableName")
	public ReferenceTableData populateCache(String tableName) {
		return getReferenceData(tableName);
	}
	
	@CacheEvict(cacheNames = "referenceDataCache", allEntries = true)
	public void clearCache() {
		System.out.println(">>> clearCache()");
	}

	/**
	 * Gets the reference data.
	 *
	 * @param tableName the table name
	 * @return the reference data
	 */
	public ReferenceTableData getReferenceData(String tableName) {
		
		String deployedEnvironment = environmentConfig.getEnvironment();
		
		GetReferenceDataTableRequest getReferenceDataTableRequest = new GetReferenceDataTableRequest();
		
		VersionInformationType versionInformationType = new VersionInformationType();
		versionInformationType.setMajor(1);
		versionInformationType.setMinor(0);
		getReferenceDataTableRequest.setVersionInformation(versionInformationType);

		SystemInformationType systemInformationType = new SystemInformationType();
		systemInformationType.setEnvironment(environmentMap.get(deployedEnvironment));
		systemInformationType.setApplicationName(APPLICATION_NAME);
		systemInformationType.setApplicationType(APPLICATION_TYPE);
		systemInformationType.setApplicationVersion(APPLICATION_VERSION);
		getReferenceDataTableRequest.setClientInformation(systemInformationType);
		
		getReferenceDataTableRequest.setExecutionContext(EXECUTION_CONTEXT);
		
		getReferenceDataTableRequest.setTableName(tableName);
		
		getReferenceDataTableRequest.setReleaseContext(releaseContextMap.get(deployedEnvironment));
		
		getReferenceDataTableRequest.setIsError(false);
		
		GetReferenceDataTableResponse getReferenceDataTableResponse = 
				referenceDataLoadClient.getReferenceDataTable(getReferenceDataTableRequest);
		
		if (null != getReferenceDataTableResponse && 
				null != getReferenceDataTableResponse.getReferenceDataTable() && 
					null != getReferenceDataTableResponse.getReferenceDataTable().getReferenceDataItem()) {
			return getReferenceDataTableResponse.getReferenceDataTable();
		}
		
		return null; 
	}
	
	/**
	 * Prints the reference data table request.
	 *
	 * @param getReferenceDataTableRequest the get reference data table request
	 */
	@SuppressWarnings("PMD.DefaultPackage")
	void printReferenceDataTableRequest(GetReferenceDataTableRequest getReferenceDataTableRequest) {

		try {
			JAXBContext jc = JAXBContext.newInstance(GetReferenceDataTableRequest.class);

			LOGGER.error("GetReferenceDataTableRequest:");

			QName qName = new QName(
				"http://pm.lmig.com/domain/common/layers/service/referencedata/v1_0/ReferenceDataLoad/",
				"getReferenceDataTableRequest");

			JAXBElement<GetReferenceDataTableRequest> responseWrapper = new JAXBElement<GetReferenceDataTableRequest>(
				qName, GetReferenceDataTableRequest.class, null, getReferenceDataTableRequest);

			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, java.lang.Boolean.TRUE);
			m.marshal(responseWrapper, System.out);

			LOGGER.error("\n");
		}
		catch (JAXBException e1) {
			LOGGER.error(ERROR_CONVERTING_TO_XML, e1);
		}
	}
	
	/**
	 * Prints the reference data table response.
	 *
	 * @param getReferenceDataTableResponse the get reference data table response
	 */
	@SuppressWarnings("PMD.DefaultPackage")
	void printReferenceDataTableResponse(GetReferenceDataTableResponse getReferenceDataTableResponse) {

		try {
			JAXBContext jc = JAXBContext.newInstance(GetReferenceDataTableResponse.class);

			LOGGER.error("GetReferenceDataTableResponse:");

			QName qName = new QName(
				"http://pm.lmig.com/domain/common/layers/service/referencedata/v1_0/ReferenceDataLoad/",
				"getReferenceDataTableResponse");

			JAXBElement<GetReferenceDataTableResponse> responseWrapper = new JAXBElement<GetReferenceDataTableResponse>(
				qName, GetReferenceDataTableResponse.class, null, getReferenceDataTableResponse);

			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, java.lang.Boolean.TRUE);
			m.marshal(responseWrapper, System.out);

			LOGGER.error("\n");
		}
		catch (JAXBException e1) {
			LOGGER.error(ERROR_CONVERTING_TO_XML, e1);
		}
	}


}
