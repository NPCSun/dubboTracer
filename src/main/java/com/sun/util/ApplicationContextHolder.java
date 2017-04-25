package com.sun.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware{
	
	public static ApplicationContext context = null;
	
	public void setApplicationContext(ApplicationContext context){
		ApplicationContextHolder.context = context;
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
}
