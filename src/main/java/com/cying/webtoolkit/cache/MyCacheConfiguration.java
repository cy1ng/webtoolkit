package com.cying.webtoolkit.cache;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.annotation.AbstractCachingConfiguration;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.cache.config.CacheManagementConfigUtils;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author chengying13378 chengying13378@hundsun.com
 * @ClassName MyCacheConfiuration
 * @Description TODO
 * @date 2018/10/19 5:04 PM
 */
@Configuration
public class MyCacheConfiguration {

//    @Configuration
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public class ProxyCachingConfiguration extends AbstractCachingConfiguration {
//
//        @Bean(name = CacheManagementConfigUtils.CACHE_ADVISOR_BEAN_NAME)
//        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//        public BeanFactoryCacheOperationSourceAdvisor cacheAdvisor() {
//            BeanFactoryCacheOperationSourceAdvisor advisor = new BeanFactoryCacheOperationSourceAdvisor();
//            advisor.setCacheOperationSource(cacheOperationSource());
//            advisor.setAdvice(cacheInterceptor());
//            if (this.enableCaching != null) {
//                advisor.setOrder(this.enableCaching.<Integer>getNumber("order"));
//            }
//            return advisor;
//        }
//
//        @Bean
//        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//        public CacheOperationSource cacheOperationSource() {
//            return new AnnotationCacheOperationSource();
//        }
//
//        @Bean
//        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//        public CacheInterceptor cacheInterceptor() {
//            CacheInterceptor interceptor = new CacheInterceptor();
//            interceptor.setCacheOperationSources(cacheOperationSource());
//            if (this.cacheResolver != null) {
//                interceptor.setCacheResolver(this.cacheResolver);
//            }
//            else if (this.cacheManager != null) {
//                interceptor.setCacheManager(this.cacheManager);
//            }
//            if (this.keyGenerator != null) {
//                interceptor.setKeyGenerator(this.keyGenerator);
//            }
//            if (this.errorHandler != null) {
//                interceptor.setErrorHandler(this.errorHandler);
//            }
//            return interceptor;
//        }
//
//    }
}
