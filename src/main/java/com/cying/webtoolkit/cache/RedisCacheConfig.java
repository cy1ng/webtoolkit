package com.cying.webtoolkit.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author chengying13378 chengying13378@hundsun.com
 * @ClassName RedisCacheConfig
 * @Description TODO
 * @date 2018/10/18 1:28 PM
 */
@Configuration
public class RedisCacheConfig {

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("www.lovecying.com", 6379));
//    }
}
