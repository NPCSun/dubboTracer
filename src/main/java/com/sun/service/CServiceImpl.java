package com.sun.service;

import org.springframework.stereotype.Service;

@Service("cService")
public class CServiceImpl implements CService {

	public void sayHi(String name){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CService executed! ");
	}

}
