/*
 * Copyright (c) 2018, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import com.lmig.pm.domain.common.layers.service.referencedata.v1_0.referencedataload.ReferenceDataLoad;

import io.redis.jedis.jedisdemo.webserviceclient.webservices.WebServiceClient;

import com.lmig.pm.domain.common.layers.service.messages.getreferencedatatable.v1_0.GetReferenceDataTableRequest;
import com.lmig.pm.domain.common.layers.service.messages.getreferencedatatable.v1_0.GetReferenceDataTableResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.handler.Handler;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;


/**
 * Client for ReferenceDataLoad service.
 *
 * @author n0281526 (Harsha Edupuganti)
 */
@Component
public class ReferenceDataLoadClient extends WebServiceClient<ReferenceDataLoad> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private EnvironmentConfig environmentConfig;

	/**
	 * Gets the handlers.
	 *
	 * @return the handlers
	 */
	// @Override
	@SuppressWarnings("rawtypes")
	protected List<Handler> getHandlers() {
		List<Handler> handlerChain = null;
		if (StringUtils.equals(EnvironmentType.LOCAL.getValue(), environmentConfig.getEnvironment())) {
			handlerChain = new ArrayList<Handler>();
			handlerChain.add(new WebServiceRecorderHandler());
		}
		return handlerChain;
	}

	/**
	 * Gets the reference data table.
	 *
	 * @param getReferenceDataTableRequest the get reference data table request
	 * @return the reference data table
	 */
	public GetReferenceDataTableResponse getReferenceDataTable(GetReferenceDataTableRequest getReferenceDataTableRequest) {
		
		GetReferenceDataTableResponse response = client.getReferenceDataTable(getReferenceDataTableRequest);
		
		return response;
	}

}
