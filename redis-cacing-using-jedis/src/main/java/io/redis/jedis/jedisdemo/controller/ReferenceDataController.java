package io.redis.jedis.jedisdemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceDataItem;
import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceTableData;

import io.redis.jedis.jedisdemo.helper.AcordMediationReferenceTableType;
import io.redis.jedis.jedisdemo.helper.AcordSalesMediationReferenceDataFacade;
import io.redis.jedis.jedisdemo.service.refdata.ReferenceDataService;

@RestController
public class ReferenceDataController {

	@Autowired
	private AcordSalesMediationReferenceDataFacade referenceDataFacade;

	/** The reference data service. */
	@Autowired
	private ReferenceDataService referenceDataService;
	
	@RequestMapping(value = "/evictReferenceDataCache",
		produces = { "application/json" }, 
		method = RequestMethod.GET)
	public void evictReferenceDataForTable() {
		System.out.println(">>> ReferenceDataController.evictReferenceDataForTable()");

			  long now = System.currentTimeMillis();      
		      System.out.println("current time in controller - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(now)));

		      referenceDataService.clearCache();

			  System.out.println("<<< ReferenceDataController.evictReferenceDataForTable()");
	}

	@RequestMapping(value = "/evictAndPopulateReferenceDataInCache",
	produces = { "application/json" }, 
	method = RequestMethod.GET)
	public ResponseEntity getReferenceDataForTable() {
	  System.out.println(">>> ReferenceDataController.getReferenceDataForTable()");

	  long now = System.currentTimeMillis();      
      System.out.println("current time in controller - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(now)));

      referenceDataService.clearCache();
  	
      for (AcordMediationReferenceTableType acordMediationReferenceTableType : AcordMediationReferenceTableType.class.getEnumConstants()) {
  		referenceDataService.populateCache(acordMediationReferenceTableType.getTableName());
      }

	  List<ReferenceDataItem> returnVal = referenceDataFacade.getAllTableData(
				AcordMediationReferenceTableType.BUSINESS_EVENTS);

	  System.out.println("<<< ReferenceDataController.getReferenceDataForTable()");
	  return ResponseEntity.ok(returnVal);
	}

	@RequestMapping(value = "/referenceDataFromCache",
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	public ResponseEntity getReferenceDataFromCache() {
		System.out.println(">>> ReferenceDataController.getReferenceDataFromCache()");

		long now = System.currentTimeMillis();      
		System.out.println("current time in controller - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(now)));

		Map<String, ReferenceTableData> hm = new HashMap<String, ReferenceTableData>(); 
		for (AcordMediationReferenceTableType acordMediationReferenceTableType : AcordMediationReferenceTableType.class.getEnumConstants()) {
		    ReferenceTableData fullTableFromCache = referenceDataService.getFromCache(acordMediationReferenceTableType.getTableName());
		    hm.put(acordMediationReferenceTableType.getTableName(), fullTableFromCache);
		}

		System.out.println("<<< ReferenceDataController.getReferenceDataFromCache()");
		return ResponseEntity.ok(hm);
	}
}
