/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Enumeration of System property environment values.
 *
 * @author n0161065
 */
public enum EnvironmentType {
	
	/** The local. */
	LOCAL("local"),
	
	/** The development. */
	DEVELOPMENT("development"),
	
	/** The test. */
	TEST("test"),
	
	/** The etoe. */
	ETOE("etoe"),
	
	/** The performance. */
	PERFORMANCE("performance"),
	
	/** The uat. */
	UAT("uat"),
	
	/** The training. */
	TRAINING("training"),
	
	/** The stage. */
	STAGE("stage"),
	
	/** The prod2. */
	PROD2("prod2"),
	
	/** The production. */
	PRODUCTION("production"),
	
	/** The not set. */
	NOT_SET("not_set"),
	
	/** The unit. */
	UNIT("unit");

	/** The Constant lookup. */
	private static final Map<String, EnvironmentType> lookup = new HashMap<String, EnvironmentType>();

	/** The Constant productionEnvironments. */
	private static final Set<String> productionEnvironments = new HashSet<String>();

	static {
		for (EnvironmentType env : EnumSet.allOf(EnvironmentType.class)) {
			lookup.put(env.getValue(), env);
		}

		// Add all production environments for helper method
		productionEnvironments.add(EnvironmentType.PRODUCTION.value);
		productionEnvironments.add(EnvironmentType.PROD2.value);
	}

	/** The value. */
	private String value;

	/**
	 * Constructor.
	 *
	 * @param valueIn the value in
	 */
	private EnvironmentType(String valueIn) {
		this.value = valueIn;
	}

	/**
	 * Return the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the.
	 *
	 * @param value the value
	 * @return the environment type
	 */
	public static EnvironmentType get(String value) {
		return lookup.get(value);
	}

}
