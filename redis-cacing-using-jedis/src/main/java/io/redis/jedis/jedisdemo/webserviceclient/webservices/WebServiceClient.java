/*
 * Copyright (c) 2017, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.webserviceclient.webservices;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.ws.handler.Handler;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

/**
 * Service Client Utility.
 *
 * @param <S>
 * The Service Endpoint Interface
 *
 * @param <S> The Service Endpoint Interface
 * @author Dan Clark
 * @author Anthony Capozzi
 */
@SuppressWarnings({ "PMD.EmptyCatchBlock", "PMD.PreserveStackTrace" })
@Component
public abstract class WebServiceClient<S> {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceClient.class);

	private static final String CONNECTION_TIMEOUT_PROPERTY = "javax.xml.ws.client.connectionTimeout";

	private static final String RECEIVE_TIMEOUT_PROPERTY = "javax.xml.ws.client.receiveTimeout";

	@SuppressWarnings("unchecked")
	private final Class<S> clientClass = (Class<S>) GenericTypeResolver.resolveTypeArgument(getClass(),
		WebServiceClient.class);

	protected S client;

	@Autowired
	private WebServiceConfigurationProperties wsProperties;

	@PostConstruct
	@SuppressWarnings("PMD.UnusedPrivateMethod")
	private void init() {
		try {
			this.client = init(this.clientClass, this.wsProperties, getHandlers());
		}
		catch (Throwable e) {

			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Exception in initialization.", e);
			}

			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	protected abstract List<Handler> getHandlers();

	@SuppressWarnings("rawtypes")
	public static <S> S init(final Class<S> serviceEndpointInterface, final WebServiceConfigurationProperties wsConfigs,
		final List<Handler> handlers) throws WebServiceClientException, UnrecoverableKeyException, CertificateException,
		NoSuchAlgorithmException, KeyStoreException, IOException {

		if (null == serviceEndpointInterface) {
			throw new WebServiceClientException("The SEI class is null!");
		}

		if (null == wsConfigs.getConfigs()) {
			throw new WebServiceClientException("Cannot locate web service configurations!");
		}

		String serviceKey = serviceEndpointInterface.getSimpleName();

		if (!wsConfigs.webServiceConfigurationKeyExists(serviceKey)) {
			throw new WebServiceClientException("Cannot locate configuration for '" + serviceKey + "'");
		}

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		WebServiceConfiguration wsConfig = wsConfigs.getWebServiceConfiguration(serviceKey);
		LOGGER.info("Setting endpoint address: serviceKey=\"{}\" endpoint=\"{}\"", serviceKey, wsConfig.getEndpoint());
		factory.setAddress(wsConfig.getEndpoint());

		if (null != handlers) {
			factory.setHandlers(handlers);
		}

		S proxy = factory.create(serviceEndpointInterface);
		Client client = ClientProxy.getClient(proxy);
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();

		configureTLSClientParameters(httpConduit);

		if (wsConfig.isBasicAuthSpecified()) {
			configureBasicAuth(serviceKey, wsConfig, httpConduit);
		}

		configureConnectionTimeouts(wsConfig, client);

		return proxy;
	}

	/**
	 * Add TLS parameters to the HTTP conduit to ensure the default SSL socket factory is used.
	 * This allows for reuse of any pre-existing trust that has been established
	 *
	 * @param httpConduit the http conduit to set the parameters onto
	 * @throws WebServiceClientException WebServiceClientException
	 * @throws UnrecoverableKeyException UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException NoSuchAlgorithmException
	 * @throws KeyStoreException KeyStoreException
	 * @throws CertificateException CertificateException
	 * @throws IOException IOException
	 */
	private static void configureTLSClientParameters(
		final HTTPConduit httpConduit)
		throws WebServiceClientException, UnrecoverableKeyException, NoSuchAlgorithmException {

		TLSClientParameters tlsClientParameters = new TLSClientParameters();
		tlsClientParameters.setUseHttpsURLConnectionDefaultSslSocketFactory(true);

		httpConduit.setTlsClientParameters(tlsClientParameters);
	}

	private static void configureBasicAuth(
		final String serviceKey, final WebServiceConfiguration wsConfig, final HTTPConduit httpConduit)
		throws WebServiceClientException {

		if (null == wsConfig.getUsername()) {
			throw new WebServiceClientException("Username not set for '" + serviceKey + "'");
		}

		if (null == wsConfig.getPassword()) {
			throw new WebServiceClientException("Password not set for '" + serviceKey + "'");
		}

		AuthorizationPolicy basicAuthPolicy = new AuthorizationPolicy();
		basicAuthPolicy.setAuthorizationType("Basic");
		basicAuthPolicy.setUserName(wsConfig.getUsername());
		basicAuthPolicy.setPassword(wsConfig.getPassword());

		httpConduit.setAuthorization(basicAuthPolicy);
	}

	private static void configureConnectionTimeouts(
		final WebServiceConfiguration wsConfig, final Client client) {

		Integer connectionTimeoutMs = wsConfig.getConnectionTimeoutMs();
		Integer receiveTimeoutMs = wsConfig.getReceiveTimeoutMs();

		if (null != connectionTimeoutMs) {
			client.getRequestContext().put(CONNECTION_TIMEOUT_PROPERTY, connectionTimeoutMs);
		}

		if (null != receiveTimeoutMs) {
			client.getRequestContext().put(RECEIVE_TIMEOUT_PROPERTY, receiveTimeoutMs);
		}
	}
}
