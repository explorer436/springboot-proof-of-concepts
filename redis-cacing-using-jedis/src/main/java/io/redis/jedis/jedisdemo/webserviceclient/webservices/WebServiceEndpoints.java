/*
 * Copyright (c) 2017, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.webserviceclient.webservices;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Service Endpoint properties.
 *
 * @author Dan Clark
 * @author Anthony Capozzi
 */
@Deprecated
@Component
@ConfigurationProperties("webservices")
public class WebServiceEndpoints {

	private Map<String, String> endpoints;

	public Map<String, String> getEndpoints() {
		return this.endpoints;
	}

	public void setEndpoints(final Map<String, String> endpoints) {

		this.endpoints = endpoints;
	}

	public String getServiceEndpoint(final String serviceKey) {

		return this.endpoints.get(serviceKey);
	}

	public boolean serviceKeyExists(final String serviceKey) {

		return this.endpoints.containsKey(serviceKey);
	}
}
