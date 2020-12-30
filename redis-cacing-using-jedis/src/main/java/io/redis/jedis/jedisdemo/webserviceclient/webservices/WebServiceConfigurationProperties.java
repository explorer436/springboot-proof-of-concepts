/*
 * Copyright (c) 2017, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.webserviceclient.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Web service configuration properties.
 *
 * @author Anthony Capozzi
 */
@Component
@ConfigurationProperties("ws")
public class WebServiceConfigurationProperties {

	@Autowired
	private WebServiceEndpoints endpoints;

	private Map<String, WebServiceConfiguration> configs;

	public Map<String, WebServiceConfiguration> getConfigs() {

		return this.configs;
	}

	public void setConfigs(final Map<String, WebServiceConfiguration> configs) {

		this.configs = configs;
	}

	@PostConstruct
	@SuppressWarnings("PMD.UnusedPrivateMethod")
	private void convertWebServiceEndpointsToWebServiceConfigurations() {

		if (null != this.endpoints && null != this.endpoints.getEndpoints()) {

			if (null == this.configs) {

				this.configs = new HashMap<>();
			}

			Iterator<String> epIterator = this.endpoints.getEndpoints().keySet().iterator();
			while (epIterator.hasNext()) {

				String serviceKey = epIterator.next();
				String endpoint = this.endpoints.getServiceEndpoint(serviceKey);

				@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
				WebServiceConfiguration wsConfig = new WebServiceConfiguration();
				wsConfig.setEndpoint(endpoint);
				this.configs.put(serviceKey, wsConfig);
			}
		}
	}

	public boolean webServiceConfigurationKeyExists(final String webServiceConfigurationKey) {

		return this.configs.containsKey(webServiceConfigurationKey);
	}

	public WebServiceConfiguration getWebServiceConfiguration(final String webServiceConfigurationKey) {

		return this.configs.get(webServiceConfigurationKey);
	}
}
