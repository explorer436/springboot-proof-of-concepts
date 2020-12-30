package io.redis.jedis.jedisdemo.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.redis.jedis.jedisdemo.helper.AcordMediationReferenceTableType;
import io.redis.jedis.jedisdemo.service.refdata.ReferenceDataService;

@Configuration
@EnableScheduling
public class CacheReloadScheduler {
	
	/** The reference data service. */
	@Autowired
	private ReferenceDataService referenceDataService;
	
	/**
     * <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year> <command>
     */
	@Scheduled(cron="00 30 01 * * *")
    public void scheduleTaskUsingCronExpression() {
    	System.out.println("schedule tasks using cron jobs - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis())));
        
    	referenceDataService.clearCache();
    	
    	for (AcordMediationReferenceTableType acordMediationReferenceTableType : AcordMediationReferenceTableType.class.getEnumConstants()) {
    		referenceDataService.populateCache(acordMediationReferenceTableType.getTableName());
        }
    }
}
