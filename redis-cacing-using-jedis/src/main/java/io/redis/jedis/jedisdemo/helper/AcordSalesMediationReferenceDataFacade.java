/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Wraps the ReferenceDataAccessor using the thread local to obtain RDM look up related fields such as state and quote
 * effective date. Uses AcordSalesMediationReferenceTableType which understands which tables are state specific.
 * 
 * @author n0061865
 */
@Component
public class AcordSalesMediationReferenceDataFacade extends AcordMediationReferenceDataFacade {
	
	/** The Constant ACORD_SALES_MEDIATION_APP_NAME. */
	private static final String ACORD_SALES_MEDIATION_APP_NAME = "ECACRDSMSWLP";

	/**
	 * Supplies the jurisdiction to use for lookups.
	 *
	 * @return the jurisdiction
	 */
	@Override
	public String getJurisdiction() {
		return "NH";
	}

	/**
	 * Supplies the effective date to use for lookups.
	 *
	 * @return the effective date
	 */
	@Override
	public Date getEffectiveDate() {
		return new Date();
	}

	/**
	 * Supplies the application name to use for lookups.
	 * 
	 * @return String containing the application name
	 */
	public String getApplicationName() {
		return ACORD_SALES_MEDIATION_APP_NAME;
	}
}
