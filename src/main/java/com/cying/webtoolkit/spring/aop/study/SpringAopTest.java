package com.cying.webtoolkit.spring.aop.study;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Aspect
public class SpringAopTest {

	private static Logger logger = LogManager.getLogger(SpringAopTest.class);
	
	@Before("execution(* com.cying.webtoolkit.spring.aop.study.SpringAopTest..(..))")
    public void doAccessCheck() {
		logger.info("before test");
    }
	      
	public  void test(){    	
		logger.info("do test");
	}
	public static void main(String[] args) {
		logger.info("start main");
		ApplicationContext ap = new ClassPathXmlApplicationContext(new String[]{"classpath:/com/cying/webtoolkit/spring/aop/study/spring-aop-study.xml"});
		SpringAopTest aop = (SpringAopTest)ap.getBean("aopTest");
		aop.test();
		logger.info("end main");
	}

}
