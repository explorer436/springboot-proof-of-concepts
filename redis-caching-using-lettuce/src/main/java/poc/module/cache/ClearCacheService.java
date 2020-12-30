package poc.module.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import poc.module.referenceData.ReferenceDataService;


/* 
    If you want to repopulate the data here, it can't be called within the same class where @Cacheable is defined.
    The way the annotations work is that they create a proxy between each cache. If we were to call getReferenceDataFromCache() 
    from this method, in the same class, it would ignore all the annotations and not deal with the caching mechanism.
*/
@Service 
public class ClearCacheService {

    @Autowired ReferenceDataService service;

    //@Scheduled(cron = "0 0 2 * * *") /* 2am every day */
    @Scheduled(cron = "0 * * * * *")
    public void evictAndPopulateCache(){
        service.cacheEvict();
        service.getReferenceDataFromCache("deva");
    }
}