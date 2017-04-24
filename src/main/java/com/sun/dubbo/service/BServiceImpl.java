package com.sun.dubbo.service;

import com.sun.dubbo.filter.Main;

public class BServiceImpl implements BService {

	public void sayHi(String name){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("BService executed! ");
		
		CService cs = (CService) Main.context.getBean("cServiceDubbo");
	    cs.sayHi("sun");
	}

}
