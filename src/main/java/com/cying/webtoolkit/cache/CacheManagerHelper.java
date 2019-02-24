package com.cying.webtoolkit.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengying13378 chengying13378@hundsun.com
 * @ClassName CacheManagerHelper
 * @Description TODO
 * @date 2018/10/18 9:52 AM
 */
@Service
public class CacheManagerHelper {

    private AtomicInteger count = new AtomicInteger(0);

    /**
     * spring3.2版本没有这个sync,不过它可能会影响性能。每次进去都会加同步锁
     * @param key
     * @return
     */
    @Cacheable(cacheNames = "myTest", key="#p0",sync = true)
    @CacheSync
    public String getTestKey(String key){
        System.out.println("进入到方法了哦:"+count.getAndIncrement());
        return "ok";
    }
}
