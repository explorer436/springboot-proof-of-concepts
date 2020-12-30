/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceDataItem;
import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceTableData;

import io.redis.jedis.jedisdemo.service.refdata.ReferenceDataService;

/**
 * ReferenceDataAccessor to wrap the access of the RDM jar.
 * 
 * @author Brian Hamill
 * @author Jim Crume (Adapted for Pi)
 */
@Component
public class ReferenceDataAccessor {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	/** The reference data service. */
	@Autowired
	private ReferenceDataService referenceDataService;

	/**
	 * Calls to RDM client and returns the result as a String.
	 *
	 * @param description the description
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the code for description
	 * @pre String description != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre StringappName != null
	 * @post non-null String
	 */
	public String getCodeForDescription(String description, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType, String appName) {
		String result = null;
		
		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);		
		
		List<ReferenceDataItem> listFilteredByDescription = null;
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			listFilteredByDescription = refDataItemsByStateAndEffDate.stream()
					.filter(refDataItem -> StringUtils.equals(description, refDataItem.getDescription()))
					.collect(Collectors.toList());
		}

		// The call to the RDM client returns a list but theoretically there should only be one result in it. The
		// equivalent RDM call in NextGen WPS only returns one result
		if (!CollectionUtils.isEmpty(listFilteredByDescription)) {
			result = listFilteredByDescription.get(0).getCode();
		}

		return result;
	}

	/**
	 * Calls to RDM client and returns the result as a String.
	 *
	 * @param value the value
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the code for value
	 * @pre String value != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public String getCodeForValue(String value, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType, String appName) {
		String result = null;

		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);		
		
		List<ReferenceDataItem> listFilteredByValue = null;
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			listFilteredByValue = refDataItemsByStateAndEffDate.stream()
					.filter(refDataItem -> StringUtils.equals(value, refDataItem.getValue()))
					.collect(Collectors.toList());
		}

		// The call to the RDM client returns a list but theoretically there should only be one result in it. The
		// equivalent RDM call in NextGen WPS only returns one result
		if (!CollectionUtils.isEmpty(listFilteredByValue)) {
			result = listFilteredByValue.get(0).getCode();
		}

		return result;
	}

	/**
	 * Calls to RDM client and returns the result as a String.
	 *
	 * @param code the code
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the description for code
	 * @pre String code != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public String getDescriptionForCode(String code, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType, String appName) {
		String result = null;
		
		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);		
		
		List<ReferenceDataItem> listFilteredByCode = null;
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			listFilteredByCode = refDataItemsByStateAndEffDate.stream()
					.filter(refDataItem -> StringUtils.equals(code, refDataItem.getCode()))
					.collect(Collectors.toList());
		}

		// The call to the RDM client returns a list but theoretically there should only be one result in it. The
		// equivalent RDM call in NextGen WPS only returns one result
		if (!CollectionUtils.isEmpty(listFilteredByCode)) {
			result = listFilteredByCode.get(0).getDescription();
		}

		return result;
	}

	/**
	 * Calls to RDM client and returns the result as a String.
	 *
	 * @param value the value
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the description for value
	 * @pre String value != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public String getDescriptionForValue(String value, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType,
		String appName) {
		String result = null;
		
		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);		
		
		List<ReferenceDataItem> listFilteredByValue = null;
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			listFilteredByValue = refDataItemsByStateAndEffDate.stream()
					.filter(refDataItem -> StringUtils.equals(value, refDataItem.getValue()))
					.collect(Collectors.toList());
		}

		// The call to the RDM client returns a list but theoretically there should only be one result in it. The
		// equivalent RDM call in NextGen WPS only returns one result
		if (!CollectionUtils.isEmpty(listFilteredByValue)) {
			result = listFilteredByValue.get(0).getDescription();
		}

		return result;
	}

	/**
	 * Calls to RDM client and returns the result as a String.
	 *
	 * @param code the code
	 * @param value the value
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the description for code and value
	 * @pre String value != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public String getDescriptionForCodeAndValue(String code, String value, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType,
		String appName) {
		String result = null;
		
		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);		
		
		List<ReferenceDataItem> listFilteredByCodeAndValue = null;
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			listFilteredByCodeAndValue = refDataItemsByStateAndEffDate.stream()
					.filter(refDataItem -> StringUtils.equals(code, refDataItem.getCode()) && StringUtils.equals(value, refDataItem.getValue()))
					.collect(Collectors.toList());
		}

		// The call to the RDM client returns a list but theoretically there should only be one result in it. The
		// equivalent RDM call in NextGen WPS only returns one result
		if (!CollectionUtils.isEmpty(listFilteredByCodeAndValue)) {
			result = listFilteredByCodeAndValue.get(0).getDescription();
		}

		return result;
	}

	/**
	 * Calls to RDM client and returns the result as a ReferenceDataItem List.
	 *
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the table data
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public List<ReferenceDataItem> getTableDataByStateProvinceCodeAndEffDate(String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType,
		String appName) {
		
		ReferenceTableData fullTableFromCache = referenceDataService.getFromCache(tableType.getTableName());
		if (null == fullTableFromCache) {
	        fullTableFromCache = referenceDataService.populateCache(tableType.getTableName());
	    }
		
		filterEffectiveItems(fullTableFromCache, effectiveDate);
		filterByRDMStateProvince(fullTableFromCache, rdmStateProvince);
		
		return fullTableFromCache.getReferenceDataItem();
	}

	/**
	 * Calls to RDM client and returns the result as a ReferenceDataItem List.
	 *
	 * @param code the code
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the table data for code
	 * @pre String code != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public List<ReferenceDataItem> getTableDataForCode(String code, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType, String appName) {
		
		List<ReferenceDataItem> result = null;
		
		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);
		
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			result = refDataItemsByStateAndEffDate.stream()
			.filter(refDataItem -> StringUtils.equals(code, refDataItem.getCode()))
			.collect(Collectors.toList());
		}
		return result;
	}

	/**
	 * Calls to RDM client and returns the result as a String.
	 *
	 * @param code the code
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the value for code
	 * @pre String code != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public String getValueForCode(String code, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType,
		String appName) {
		String result = null;
		
		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);		
		
		List<ReferenceDataItem> listFilteredByCode = null;
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			listFilteredByCode = refDataItemsByStateAndEffDate.stream()
					.filter(refDataItem -> StringUtils.equals(code, refDataItem.getCode()))
					.collect(Collectors.toList());
		}
		

		// The call to the RDM client returns a list but theoretically there should only be one result in it. The
		// equivalent RDM call in NextGen WPS only returns one result
		if (!CollectionUtils.isEmpty(listFilteredByCode)) {
			result = listFilteredByCode.get(0).getValue();
		}
		
		return result;
	}

	/**
	 * Calls to RDM client and returns the result as a String.
	 *
	 * @param description the description
	 * @param rdmStateProvince the rdm state province
	 * @param effectiveDate the effective date
	 * @param tableType the table type
	 * @param appName the app name
	 * @return the value for description
	 * @pre String description != null
	 * @pre String stateProvince != null
	 * @pre String tableName != null
	 * @pre String appName != null
	 * @post non-null String
	 */
	public String getValueForDescription(String description, String rdmStateProvince, Date effectiveDate,
		ReferenceTableType tableType, String appName) {
		String result = null;
		
		List<ReferenceDataItem> refDataItemsByStateAndEffDate = getTableDataByStateProvinceCodeAndEffDate(rdmStateProvince, effectiveDate, tableType, appName);		
		
		List<ReferenceDataItem> listFilteredByDescription = null;
		if (!CollectionUtils.isEmpty(refDataItemsByStateAndEffDate)) {
			listFilteredByDescription = refDataItemsByStateAndEffDate.stream()
					.filter(refDataItem -> StringUtils.equals(description, refDataItem.getDescription()))
					.collect(Collectors.toList());
		}

		// The call to the RDM client returns a list but theoretically there should only be one result in it. The
		// equivalent RDM call in NextGen WPS only returns one result
		if (!CollectionUtils.isEmpty(listFilteredByDescription)) {
			result = listFilteredByDescription.get(0).getValue();
		}

		return result;
	}

	/**
	 * Remove non effective items from the list.
	 *
	 * @param rdmData the rdm data
	 * @param effectiveDate the effective date
	 */
	private void filterEffectiveItems(ReferenceTableData rdmData, Date effectiveDate) {
		
		if (!CollectionUtils.isEmpty(rdmData.getReferenceDataItem())) {
			ListIterator<ReferenceDataItem> listIter = rdmData.getReferenceDataItem().listIterator();
			while (listIter.hasNext()) {
				if (!isReferenceDataItemEffective(listIter.next(), effectiveDate)) {
					listIter.remove();
				}

			}
		}
	}

	/**
	 * Remove non matching jurisdictions.
	 *
	 * @param rdmData the rdm data
	 * @param rdmStateProvince the rdm state province
	 */
	private void filterByRDMStateProvince(ReferenceTableData rdmData, String rdmStateProvince) {
		if (!CollectionUtils.isEmpty(rdmData.getReferenceDataItem())) {
			ListIterator<ReferenceDataItem> listIter = rdmData.getReferenceDataItem().listIterator();
			while (listIter.hasNext()) {
				if (!StringUtils.equals(rdmStateProvince, listIter.next().getStateProvince())) {
					listIter.remove();
				}
			}
		}
	}
	
	/**
	 * A convenience method to help determine if a reference data item is effective with respect to a speciefied date.
	 *
	 * @param item the item
	 * @param targetDate the target date
	 * @return true, if is reference data item effective
	 * @pre  item != null
	 * @post If the <code>item</code> effective date is less than or equal to the <code>targetDate</code> and
	 * 	   the <code>item</code> expiration date is greater than the <code>targetDate</code> then <code>true</code>
	 * 	   has been returned. Otherwise <code>false</code> has been returned.
	 * @note If <code>targetDate</code> == <code>null</code> then <code>targetDate</code> is defaulted to today.
	 * @note if <code>item.getEffectiveDate()</code> is <code>null</code> the item is considered to have a "start
	 * 	   of time" effective date.
	 * @note if <code>item.getExpirationDate()</code> is <code>null</code> the item is considered to have a "end
	 * 	   of time" expiration date.
	 */
	@SuppressWarnings("deprecation")
	public boolean isReferenceDataItemEffective(ReferenceDataItem item, Date targetDate) {
		boolean itemEffective = false;

		if (null == targetDate) {
			targetDate = new Date();
		}
		int targetYear = targetDate.getYear() + 1900;
		int targetMonth = targetDate.getMonth() + 1;
		int targetDay = targetDate.getDate();
		
		itemEffective = isReferenceDataItemEffective(item, targetYear, targetMonth, targetDay);
		
		return itemEffective;
	}
	
	/**
	 * A convenience method to help determine if a reference data item is effective with respect to a speciefied date.
	 *
	 * @param item the item
	 * @param targetYear the target year
	 * @param targetMonth the target month
	 * @param targetDay the target day
	 * @return true, if is reference data item effective
	 * @pre  item != null
	 * @post If the <code>item</code> effective date is less than or equal to the the date specified by <code>year</code>,
	 * 	   <code>month</code> and <code>day</code>, and the <code>item</code> expiration date is greater than the 
	 * 	   the date specified by <code>year</code>, <code>month</code> and <code>day</code> then <code>true</code>
	 * 	   has been returned. Otherwise <code>false</code> has been returned.
	 * @note if <code>item.getEffectiveDate()</code> is <code>null</code> the item is considered to have a "start
	 * 	   of time" effective date.
	 * @note if <code>item.getExpirationDate()</code> is <code>null</code> the item is considered to have a "end
	 * 	   of time" expiration date.
	 */
	public boolean isReferenceDataItemEffective(ReferenceDataItem item, int targetYear, int targetMonth, int targetDay) {
		boolean itemEffective = false;
		boolean isItemEffectiveLessThanOrEqualTarget = false;
		boolean isItemExpirationGreaterThanTarget = false;

		XMLGregorianCalendar itemEffectiveDate = item.getEffectiveDate();
		XMLGregorianCalendar itemExpirationDate = item.getExpirationDate();

		if (itemEffectiveDate == null) {
			isItemEffectiveLessThanOrEqualTarget = true;
		}
		else if (itemEffectiveDate.getYear() < targetYear) {
			isItemEffectiveLessThanOrEqualTarget = true;
		}
		else if (itemEffectiveDate.getYear() == targetYear &&
			itemEffectiveDate.getMonth() < targetMonth) {
			isItemEffectiveLessThanOrEqualTarget = true;
		}
		else if (itemEffectiveDate.getYear() == targetYear &&
			itemEffectiveDate.getMonth() == targetMonth &&
			itemEffectiveDate.getDay() <= targetDay) {
			isItemEffectiveLessThanOrEqualTarget = true;
		}
		
		if (itemExpirationDate == null) {
			isItemExpirationGreaterThanTarget = true;
		}
		else if (itemExpirationDate.getYear() > targetYear) {
			isItemExpirationGreaterThanTarget = true;
		}
		else if (itemExpirationDate.getYear() == targetYear &&
				itemExpirationDate.getMonth() > targetMonth) {
			isItemExpirationGreaterThanTarget = true;
		}
		else if (itemExpirationDate.getYear() == targetYear &&
			itemExpirationDate.getMonth() == targetMonth &&
			itemExpirationDate.getDay() > targetDay) {
			isItemExpirationGreaterThanTarget = true;
		}

		itemEffective = isItemEffectiveLessThanOrEqualTarget && isItemExpirationGreaterThanTarget;
		
		return itemEffective;
	}

}
