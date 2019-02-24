package com.cying.webtoolkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author chengying13378 chengying13378@hundsun.com
 * @ClassName Application
 * @Description TODO
 * @date 2018/10/12 6:33 PM
 */
@SpringBootApplication
@EnableCaching
public class CyingApplication {

    public static void main(String[] args){
        SpringApplication.run(CyingApplication.class,args);
    }
}
