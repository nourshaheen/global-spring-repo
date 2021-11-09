package com.global;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ShowBeanLifCycle implements InitializingBean , DisposableBean, BeanPostProcessor {

	@Override
	public void afterPropertiesSet() throws Exception {
		
		System.out.println("When bean is Initializing");
	}

	@Override
	public void destroy() throws Exception {
		
		System.out.println("When bean is destroy");
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("In  post Process After Initialization and bean is " + beanName);
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("In  post Process Before Initialization and bean is " + beanName);
		return bean;
	}

}
