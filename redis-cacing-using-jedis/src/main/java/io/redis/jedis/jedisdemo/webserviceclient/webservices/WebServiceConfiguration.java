/*
 * Copyright (c) 2017, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.webserviceclient.webservices;

/**
 * Web service configuration class.
 *
 * @author Anthony Capozzi
 */
public class WebServiceConfiguration {

	private String endpoint;

	private String username;

	private String password;

	private Integer connectionTimeoutMs;

	private Integer receiveTimeoutMs;

	public String getEndpoint() {
		return this.endpoint;
	}

	public void setEndpoint(final String endpoint) {

		this.endpoint = endpoint;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {

		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {

		this.password = password;
	}

	public Integer getConnectionTimeoutMs() {
		return this.connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(final Integer connectionTimeout) {
		this.connectionTimeoutMs = connectionTimeout;
	}

	public Integer getReceiveTimeoutMs() {
		return this.receiveTimeoutMs;
	}

	public void setReceiveTimeoutMs(final Integer receiveTimeout) {
		this.receiveTimeoutMs = receiveTimeout;
	}

	public boolean isBasicAuthSpecified() {

		// If either is not null, it is assumed the intent was to specify basic auth credentials. The consumer of the
		// web service configuration properties will be responsible for detecting errors with the data provided.
		return null != username || null != password;
	}
}
