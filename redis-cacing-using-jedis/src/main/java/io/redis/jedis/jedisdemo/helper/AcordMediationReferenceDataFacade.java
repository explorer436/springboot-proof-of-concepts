/*
 * Copyright (c) 2017, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceDataItem;

/**
 * Wraps the ReferenceDataAccessor using the thread local to obtain RDM look up related fields such as state and quote
 * effective date. Uses AcordSalesMediationReferenceTableType which understands which tables are state specific.
 * 
 * @author n0061865
 */
@Component
public abstract class AcordMediationReferenceDataFacade {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	/** The reference data accessor. */
	@Autowired
	private ReferenceDataAccessor referenceDataAccessor;
	
	/**
	 * Returns all table data.
	 *
	 * @param rdmTable the rdm table
	 * @return the all table data
	 */
	public List<ReferenceDataItem> getAllTableData(AcordMediationReferenceTableType rdmTable) {
		String rdmStateProvince = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, getEffectiveDate(), rdmTable, getApplicationName());
	}
	
	/**
	 * Returns all table data for code using mediation state.
	 *
	 * @param rdmTable the rdm table
	 * @param code the code
	 * @return the table data for code
	 */
	public List<ReferenceDataItem> getTableDataForCode(AcordMediationReferenceTableType rdmTable, String code) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getTableDataForCode(code, rdmStateCode, getEffectiveDate(),
			rdmTable, getApplicationName());
	}

	/**
	 * Lookup code for passed description using mediation state.
	 *
	 * @param rdmTable the rdm table
	 * @param description the description
	 * @return the code for description
	 */
	public String getCodeForDescription(AcordMediationReferenceTableType rdmTable, String description) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getCodeForDescription(description,
			rdmStateCode, getEffectiveDate(), rdmTable, getApplicationName());
	}
	
	/**
	 * Lookup value for passed description using mediation state.
	 *
	 * @param rdmTable the rdm table
	 * @param value the value
	 * @return the code for value
	 */
	public String getCodeForValue(AcordMediationReferenceTableType rdmTable, String value) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getCodeForValue(value,
			rdmStateCode, getEffectiveDate(), rdmTable, getApplicationName());
	}

	/**
	 * Lookup description for passed using mediation state.
	 *
	 * @param rdmTable the rdm table
	 * @param code the code
	 * @return the description for code
	 */
	public String getDescriptionForCode(AcordMediationReferenceTableType rdmTable, String code) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getDescriptionForCode(code,
			rdmStateCode, getEffectiveDate(), rdmTable, getApplicationName());
	}

	/**
	 * Lookup value for passed description using mediation state.
	 *
	 * @param rdmTable the rdm table
	 * @param value the value
	 * @return the description for value
	 */
	public String getDescriptionForValue(AcordMediationReferenceTableType rdmTable, String value) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getDescriptionForValue(value,
			rdmStateCode, getEffectiveDate(), rdmTable, getApplicationName());
	}
	
	/**
	 * Lookup description for value and code.
	 *
	 * @param rdmTable the rdm table
	 * @param code the code
	 * @param value the value
	 * @return the description for code and state as value
	 */
	public String getDescriptionForCodeAndValue(AcordMediationReferenceTableType rdmTable, String code, String value) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getDescriptionForCodeAndValue(code, value, 
			rdmStateCode, getEffectiveDate(), rdmTable, getApplicationName());
	}

	/**
	 * Lookup value for passed description using mediation state.
	 *
	 * @param rdmTable the rdm table
	 * @param description the description
	 * @return the value for description
	 */
	public String getValueForDescription(AcordMediationReferenceTableType rdmTable, String description) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());

		return referenceDataAccessor.getValueForDescription(description,
			rdmStateCode, getEffectiveDate(), rdmTable, getApplicationName());
	}

	/**
	 * Lookup value for passed description using mediation state.
	 *
	 * @param rdmTable the rdm table
	 * @param code the code
	 * @return the value for code
	 */
	public String getValueForCode(AcordMediationReferenceTableType rdmTable, String code) {
		String rdmStateCode = getRDMStateCode(rdmTable, getJurisdiction());
		
		return referenceDataAccessor.getValueForCode(code,
			rdmStateCode, getEffectiveDate(), rdmTable, getApplicationName());
	}

	/**
	 * Utility method to determine what to pass for statProvinceCode depending on the RDM table.
	 *
	 * @param rdmTable the rdm table
	 * @param jurisdicationCode the jurisdication code
	 * @return the RDM state code
	 */
	private String getRDMStateCode(AcordMediationReferenceTableType rdmTable, String jurisdicationCode) {
		String rdmStateCode = ReferenceTableType.ALL_JURISDICTIONS;
		if (rdmTable.isStateSpecific()) {
			rdmStateCode = jurisdicationCode;
		}
		
		return rdmStateCode;
	}

	/**
	 * Returns the jurisdiction to use for lookups.
	 * 
	 * @return String containing the jurisdiction code
	 */
	public abstract String getJurisdiction();

	/**
	 * Returns the effective date to use for lookups.
	 * 
	 * @return Date containing the effective date
	 */
	public abstract Date getEffectiveDate();

	/**
	 * Returns the application name to use for lookups.
	 * 
	 * @return String containing the application name
	 */
	public abstract String getApplicationName();
}
