/*
 * Copyright (c) 2015, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Interface to get application level environment variables.
 *
 * @author n0281526 (Harsha Edupuganti)
 */
@Component
public class EnvironmentConfig {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	/** The environment. */
	@Value("${env:local}")
	private String environment;

	/**
	 * Returns the environment name - development, test, ete, production etc.
	 *
	 * @return the environment
	 */
	public String getEnvironment() {
		return this.environment;
	}
}
