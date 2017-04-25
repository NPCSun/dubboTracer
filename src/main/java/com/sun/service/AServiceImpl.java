package com.sun.service;

import org.springframework.stereotype.Service;

@Service("aService")
public class AServiceImpl implements AService {

	public void sayHi(String name){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AService executed! ");
	}

}
