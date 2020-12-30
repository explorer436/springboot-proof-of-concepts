/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

/**
 * Reference table enumerated type.
 * 
 * @author Jim Crume (adapted for PI)
 */
public interface ReferenceTableType {
	
	/** The Constant ALL_JURISDICTIONS. */
	static final String ALL_JURISDICTIONS = "ALL";

	/**
	 * returns the name of the table entry.
	 *
	 * @return String containing the table name
	 */
	String getTableName();
}
