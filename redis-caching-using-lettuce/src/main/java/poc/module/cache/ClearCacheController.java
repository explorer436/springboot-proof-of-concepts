package poc.module.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClearCacheController {

    @Autowired ClearCacheService clearCacheService;

    @GetMapping("/cache/clear")
    public void clearCacheAndRepopulate(){
        clearCacheService.evictAndPopulateCache();
    }
}