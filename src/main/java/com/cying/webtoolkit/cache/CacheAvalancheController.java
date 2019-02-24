package com.cying.webtoolkit.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengying13378 chengying13378@hundsun.com
 * @ClassName CacheAvalancheController
 * @Description TODO
 * @date 2018/10/18 9:44 AM
 */
@RestController
@RequestMapping("/cache/redis")
public class CacheAvalancheController{

    @Autowired
    private CacheManagerHelper cacheManagerHelper;

    @RequestMapping("/avalanche")
    public String testCache(){
        return cacheManagerHelper.getTestKey("cying");
    }

}
