/*
 * Copyright (c) 2017, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.webserviceclient.webservices;

/**
 * Web Service Client Exception.
 * @author Dan Clark
 */
public class WebServiceClientException extends Exception {

	public WebServiceClientException(final String error) {
		super(error);
	}

	public WebServiceClientException(final Exception e) {
		super(e);
	}
}
