/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

/**
 * RDM Tables used but AcordSalesMediationServices.
 * 
 * @author n0061865
 */
public enum AcordMediationReferenceTableType implements ReferenceTableType {
	
	/** The acord third party companies. */
	ACORD_THIRD_PARTY_COMPANIES("ACORD_THIRD_PARTY_COMPANIES", false),
	
	/** The affiliate web ids. */
	AFFILIATE_WEB_IDS("AFFILIATE_WEB_IDS", false),
	
	/** The business events. */
	BUSINESS_EVENTS("BUSINESS_EVENTS", false),
	
	/** The third party affinity id. */
	THIRD_PARTY_AFFINITY_ID("THIRD_PARTY_AFFINITY_ID", false),
	
	/** The acord lead credentials. */
	ACORD_LEAD_CREDENTIALS("ACORD_LEAD_CREDENTIALS", false),
	
	/** The acord lead to lm roof composition. */
	ACORD_LEAD_TO_LM_ROOF_COMPOSITION("ACORD_LEAD_TO_LM_ROOF_COMPOSITION", false),
	
	/** The acord third party clients. */
	ACORD_THIRD_PARTY_CLIENTS("ACORD_THIRD_PARTY_CLIENTS", false),
	
	/** The acord to lm highest education level. */
	ACORD_TO_LM_HIGHEST_EDUCATION_LEVEL("ACORD_TO_LM_HIGHEST_EDUCATION_LEVEL", false),
	
	/** The acord to lm marital status type. */
	ACORD_TO_LM_MARITAL_STATUS_TYPE("ACORD_TO_LM_MARITAL_STATUS_TYPE", false),
	
	/** The acord to lm prim heating source. */
	ACORD_TO_LM_PRIM_HEATING_SOURCE("ACORD_TO_LM_PRIM_HEATING_SOURCE", false),
	
	/** The acord to lm py burglar alarm. */
	ACORD_TO_LM_PY_BURGLAR_ALARM("ACORD_TO_LM_PY_BURGLAR_ALARM", false),
	
	/** The acord to lm py construction. */
	ACORD_TO_LM_PY_CONSTRUCTION("ACORD_TO_LM_PY_CONSTRUCTION", false),
	
	/** The acord to lm py fire alarm. */
	ACORD_TO_LM_PY_FIRE_ALARM("ACORD_TO_LM_PY_FIRE_ALARM", false),
	
	/** The acord to lm py insurance carrier. */
	ACORD_TO_LM_PY_INSURANCE_CARRIER("ACORD_TO_LM_PY_INSURANCE_CARRIER", false),
	
	/** The acord to lm sprinkler system. */
	ACORD_TO_LM_SPRINKLER_SYSTEM("ACORD_TO_LM_SPRINKLER_SYSTEM", false),
	
	/** The pipe state lob deployment. */
	PIPE_STATE_LOB_DEPLOYMENT("PIPE_STATE_LOB_DEPLOYMENT", true),
	
	/** The state list deployment geico. */
	STATE_LIST_DEPLOYMENT_GEICO("STATE_LIST_DEPLOYMENT_GEICO", false),
	
	/** The ado acord dwelling use. */
	ADO_ACORD_DWELLING_USE("ADO_ACORD_DWELLING_USE", false),
	
	/** The dwelling rented duration codes. */
	DWELLING_RENTED_DURATION_CODES("DWELLING_RENTED_DURATION_CODES", false),
	
	/** The internet property loss description. */
	INTERNET_PROPERTY_LOSS_DESCRIPTION("INTERNET_PROPERTY_LOSS_DESCRIPTION", false),
	
	/** The property loss description. */
	PROPERTY_LOSS_DESCRIPTION("PROPERTY_LOSS_DESCRIPTION", true),
	
	/** The homeowner insurance carrier name. */
	HOMEOWNER_INSURANCE_CARRIER_NAME("HOMEOWNER_INSURANCE_CARRIER_NAME", false),
	
	/** The acord to lm dog breed. */
	ACORD_TO_LM_DOG_BREED("ACORD_TO_LM_DOG_BREED", false),
	
	/** The msb to acord roofing. */
	MSB_TO_ACORD_ROOFING("MSB_TO_ACORD_ROOFING", false),
	
	/** The msb to acord style of home. */
	MSB_TO_ACORD_STYLE_OF_HOME("MSB_TO_ACORD_STYLE_OF_HOME", false),
	
	/** The msb to acord interior floor materials. */
	MSB_TO_ACORD_INTERIOR_FLOOR_MATERIALS("MSB_TO_ACORD_INTERIOR_FLOOR_MATERIALS", false),
	
	/** The msb to acord interior wall materials. */
	MSB_TO_ACORD_INTERIOR_WALL_MATERIALS("MSB_TO_ACORD_INTERIOR_WALL_MATERIALS", false),
	
	/** The msb to acord exterior wall materials. */
	MSB_TO_ACORD_EXTERIOR_WALL_MATERIALS("MSB_TO_ACORD_EXTERIOR_WALL_MATERIALS", false),
	
	/** The msb to acord foundation type. */
	MSB_TO_ACORD_FOUNDATION_TYPE("MSB_TO_ACORD_FOUNDATION_TYPE", false),
	
	/** The msb to acord ceiling materials. */
	MSB_TO_ACORD_CEILING_MATERIALS("MSB_TO_ACORD_CEILING_MATERIALS", false),
	
	/** The msb to acord garage type. */
	MSB_TO_ACORD_GARAGE_TYPE("MSB_TO_ACORD_GARAGE_TYPE", false),
	
	/** The msb to acord primary cooling source. */
	MSB_TO_ACORD_PRIMARY_COOLING_SOURCE("MSB_TO_ACORD_PRIMARY_COOLING_SOURCE", false),
	
	/** The msb to acord primary heating source. */
	MSB_TO_ACORD_PRIMARY_HEATING_SOURCE("MSB_TO_ACORD_PRIMARY_HEATING_SOURCE", false),
	
	/** The cfip repl cost feat bathroom map. */
	CFIP_REPL_COST_FEAT_BATHROOM_MAP("CFIP_REPL_COST_FEAT_BATHROOM_MAP", false),
	
	/** The fire department type. */
	FIRE_DEPARTMENT_TYPE("FIRE_DEPARTMENT_TYPE", true),
	
	/** The cfi nextgen electrical system breaker type. */
	CFI_NEXTGEN_ELECTRICAL_SYSTEM_BREAKER_TYPE("CFI_NEXTGEN_ELECTRICAL_SYSTEM_BREAKER_TYPE", false),
	
	/** The number of children in daycare. */
	NUMBER_OF_CHILDREN_IN_DAYCARE("NUMBER_OF_CHILDREN_IN_DAYCARE", false),
	
	/** The day care operating hours. */
	DAY_CARE_OPERATING_HOURS("DAY_CARE_OPERATING_HOURS", false),
	
	/** The cfi age of tank. */
	CFI_AGE_OF_TANK("CFI_AGE_OF_TANK", true),
	
	/** The business type. */
	BUSINESS_TYPE("BUSINESS_TYPE", false),
	
	/** The customer frequency. */
	CUSTOMER_FREQUENCY("CUSTOMER_FREQUENCY", false),
	
	/** The cfi wind resistance features. */
	CFI_WIND_RESISTANCE_FEATURES("CFI_WIND_RESISTANCE_FEATURES", true),
	
	/** The acord to internet occupation. */
	ACORD_TO_INTERNET_OCCUPATION("ACORD_TO_INTERNET_OCCUPATION", false),
	
	/** The internet ca occupation. */
	INTERNET_CA_OCCUPATION("INTERNET_CA_OCCUPATION", false),
	
	/** The acord to lm auto gears employment. */
	ACORD_TO_LM_AUTO_GEARS_EMPLOYMENT("ACORD_TO_LM_AUTO_GEARS_EMPLOYMENT", false),
	
	/** The premise occupied day night. */
	PREMISE_OCCUPIED_DAY_NIGHT("PREMISE_OCCUPIED_DAY_NIGHT", false),
	
	/** The renovation under construction type. */
	RENOVATION_UNDER_CONSTRUCTION_TYPE("RENOVATION_UNDER_CONSTRUCTION_TYPE", false),
	
	/** The other structure type. */
	OTHER_STRUCTURE_TYPE("OTHER_STRUCTURE_TYPE", false),
	
	/** The trust type. */
	TRUST_TYPE("TRUST_TYPE", false),
	
	/** The function toggle. */
	FUNCTION_TOGGLE("FUNCTION_TOGGLE", true),
	
	/** The water protection type. */
	WATER_PROTECTION_TYPE("WATER_PROTECTION_TYPE", false),
	
	/** The water protection provider options. */
	WATER_PROTECTION_PROVIDER_OPTIONS("WATER_PROTECTION_PROVIDER_OPTIONS", false),
	
	/** The theft protection provider options. */
	THEFT_PROTECTION_PROVIDER_OPTIONS("THEFT_PROTECTION_PROVIDER_OPTIONS", false),
	
	/** The fire protection provider options. */
	FIRE_PROTECTION_PROVIDER_OPTIONS("FIRE_PROTECTION_PROVIDER_OPTIONS", false),
	
	/** The cfi water prot device provider nms state specific. */
	CFI_WATER_PROT_DEVICE_PROVIDER_NMS_STATE_SPECIFIC("CFI_WATER_PROTECTIVE_DEVICE_PROVIDER_NAMES", true),
	
	/** The cfi water prot device provider nms non state specific. */
	CFI_WATER_PROT_DEVICE_PROVIDER_NMS_NON_STATE_SPECIFIC("CFI_WATER_PROTECTIVE_DEVICE_PROVIDER_NAMES", false),
	
	/** The cfi fire prot device provider names state specific. */
	CFI_FIRE_PROT_DEVICE_PROVIDER_NAMES_STATE_SPECIFIC("CFI_FIRE_PROTECTIVE_DEVICE_PROVIDER_NAMES", true),
	
	/** The cfi fire prot device provider names non state specific. */
	CFI_FIRE_PROT_DEVICE_PROVIDER_NAMES_NON_STATE_SPECIFIC("CFI_FIRE_PROTECTIVE_DEVICE_PROVIDER_NAMES", false),
	
	/** The cfi theft prot device provider names state specific. */
	CFI_THEFT_PROT_DEVICE_PROVIDER_NAMES_STATE_SPECIFIC("CFI_THEFT_PROTECTIVE_DEVICE_PROVIDER_NAMES", true),
	
	/** The cfi theft prot device provider names non state specific. */
	CFI_THEFT_PROT_DEVICE_PROVIDER_NAMES_NON_STATE_SPECIFIC("CFI_THEFT_PROTECTIVE_DEVICE_PROVIDER_NAMES", false),
	
	/** The dwelling rented type codes. */
	DWELLING_RENTED_TYPE_CODES("DWELLING_RENTED_TYPE_CODES", false),
	
	/** The property elements state list. */
	PROPERTY_ELEMENTS_STATE_LIST("PROPERTY_ELEMENTS_STATE_LIST", true),
	
	/** The ext report claim type. */
	EXT_REPORT_CLAIM_TYPE("EXT_REPORT_CLAIM_TYPE", false),
	
	/** The acord to lm py claim status. */
	ACORD_TO_LM_PY_CLAIM_STATUS("ACORD_TO_LM_PY_CLAIM_STATUS", false),
	
	/** The pipe prefill toggle. */
	PIPE_PREFILL_TOGGLE("PIPE_PREFILL_TOGGLE", false),
	
	/** The third party affiliate webid priority. */
	THIRD_PARTY_AFFILIATE_WEBID_PRIORITY("THIRD_PARTY_AFFILIATE_WEBID_PRIORITY", false),
	
	/** The pipe fin rsc. */
	PIPE_FIN_RSC("PIPE_FIN_RSC", false),
	
	/** The condo rented days. */
	CONDO_RENTED_DAYS("CONDO_RENTED_DAYS", false),
	
	/** The description of interest. */
	DESCRIPTION_OF_INTEREST("DESCRIPTION_OF_INTEREST", false),
	
	/** The business pursuits. */
	BUSINESS_PURSUITS("BUSINESS_PURSUITS", true),
	
	/** The geico simplified renters state list. */
	GEICO_SIMPLIFIED_RENTERS_STATE_LIST("GEICO_SIMPLIFIED_RENTERS_STATE_LIST", true),
	
	/** The simplified renters state list. */
	SIMPLIFIED_RENTERS_STATE_LIST("SIMPLIFIED_RENTERS_STATE_LIST", true);

	/** The table name. */
	private String tableName;

	/** The is state specific. */
	private boolean isStateSpecific;

	/**
	 * Instantiates a new acord mediation reference table type.
	 *
	 * @param tableNameIn the table name in
	 * @param stateSpecific the state specific
	 */
	private AcordMediationReferenceTableType(String tableNameIn, boolean stateSpecific) {
		tableName = tableNameIn;
		isStateSpecific = stateSpecific;
	}

	/**
	 * Equals.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean equals(String name) {
		return tableName.equals(name);
	}

	/**
	 * Checks if is state specific.
	 *
	 * @return the isStateSpecific
	 */
	public boolean isStateSpecific() {
		return isStateSpecific;
	}

	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}

}
