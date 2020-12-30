package poc.module.referenceData;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.lmig.pm.domain.referencedata.types.v1_0.ReferenceDataItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ReferenceDataController {

  @Autowired ReferenceDataService service;

  @RequestMapping("/")
  public String index() {
    return "Hello World";
  }


  @RequestMapping("/direct")
  public List<ReferenceDataItem> getValueDirectFromService(@RequestParam("env")String environmentKey) {
    return service.getReferenceDataDirect( environmentKey );
  }

  @RequestMapping("/cached")
  public List<ReferenceDataItem> getValueFromCache(@RequestParam("env")String environmentKey) {
    return service.getReferenceDataFromCache( environmentKey );
  }
}