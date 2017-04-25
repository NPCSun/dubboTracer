package com.sun.service;

import org.springframework.stereotype.Service;

import com.sun.util.ApplicationContextHolder;

@Service("bService")
public class BServiceImpl implements BService {

	public void sayHi(String name){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("BService executed! ");
		
		CService cs = (CService) ApplicationContextHolder.getBean("cServiceDubbo");
	    cs.sayHi("sun");
	}

}
