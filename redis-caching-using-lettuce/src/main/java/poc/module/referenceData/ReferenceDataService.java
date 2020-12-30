package poc.module.referenceData;

import java.util.List;

import com.lmig.pm.domain.common.layers.service.messages.getreferencedatatable.v1_0.GetReferenceDataTableResponse;
import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceDataItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import poc.shared.Environment;
import poc.shared.EnvironmentLookup;

@Service
public class ReferenceDataService {

    @Autowired ReferenceDataRepository repository;
    @Autowired EnvironmentLookup environmentLookup;

    public List<ReferenceDataItem> getReferenceDataDirect(String environmentKey) {
        Environment environment = environmentLookup.getEnvironment(environmentKey);
        GetReferenceDataTableResponse response = repository.getReferenceDataTable(environment);

        return response.getReferenceDataTable().getReferenceDataItem();
    }

    @Cacheable(cacheNames="referenceData", key="'PIPE::'.concat(#environmentKey)")
    public List<ReferenceDataItem> getReferenceDataFromCache(String environmentKey) {
        System.out.println( "WENT TO SERVICE ---2#$@#$@$@$");
        
        Environment environment = environmentLookup.getEnvironment(environmentKey);
        GetReferenceDataTableResponse response = repository.getReferenceDataTable(environment);

        return response.getReferenceDataTable().getReferenceDataItem();
    }

    @CacheEvict(cacheNames = "referenceData", allEntries = true)
    public void cacheEvict() 
    {
        System.out.println("CACHE CLEARED   ----@#$@#$@#$@#$$@$");
    }
}