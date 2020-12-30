package poc.shared;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class EnvironmentLookup {

    private Map<String, Environment> environments = new HashMap<String, Environment>();

    public EnvironmentLookup() throws MalformedURLException{
        environments.put("deva", new Environment("http://vxkid-prdm0010.lmig.com:40163/PIUtilityServices/ReferenceDataLoad/WEB-INF/wsdl/ReferenceDataLoad.wsdl","developmenta","development"));
        environments.put("ete", new Environment("http://vxpit-prdm0002.lmig.com:40143/PIUtilityServices/ReferenceDataLoad/WEB-INF/wsdl/ReferenceDataLoad.wsdl","etoeany","etoe"));
    }


    public Environment getEnvironment( String key){
        return environments.get(key);
    }


}