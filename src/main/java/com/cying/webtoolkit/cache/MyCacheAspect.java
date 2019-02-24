package com.cying.webtoolkit.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengying13378 chengying13378@hundsun.com
 * @ClassName MyCacheAspectSupport
 * @Description TODO
 * @date 2018/10/19 5:07 PM
 */
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE-10)
public class MyCacheAspect{


    private ReentrantLock lock = new ReentrantLock();

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.cying.webtoolkit.cache.CacheSync)")
    public void syncPointcut(){}

    @Around("syncPointcut()")
    public Object syncAdvice(ProceedingJoinPoint thisJoinPoint) throws Throwable {

        Signature signature = thisJoinPoint.getSignature();
        if(signature instanceof MethodSignature){
            MethodSignature methodSignature = (MethodSignature)signature;
            Method method = methodSignature.getMethod();
            CacheSync cacheSync = AnnotationUtils.findAnnotation(method, CacheSync.class);
            //
        }
        // key生成,默认直接用|拼接 TODO
        String key = "|";
        // 乐观的方式去看有没有值
        Object value =  redisTemplate.opsForValue().get(key);
        if(value != null){
            return value;
        }
        // 如果值是空的,加锁锁住
        lock.lock();
         try{
             // 再次检测缓存是否有值,没有就直接去调目标方法
             value = redisTemplate.opsForValue().get(key);
             if(value == null){
                 Object processResult = thisJoinPoint.proceed();
                 redisTemplate.opsForValue().set(key,processResult);
                 return processResult;
             }else{
                 // 有值的话直接返回缓存内容
                return value;
             }
         }finally {
             lock.unlock();
         }
    }


}
