package com.sun.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.service.AService;
import com.sun.service.BService;
import com.sun.util.ApplicationContextHolder;

@RestController
public class Hello {

	/*@Resource(name="aServiceDubbo")
	private AService aServiceDubbo;
	
	@Resource(name="bServiceDubbo")
	private BService bServiceDubbo;*/
	
    @RequestMapping("/")
    public String greeting() {
    	AService as = (AService) ApplicationContextHolder.getBean("aServiceDubbo");
    	as.sayHi("sun");
    	BService bs = (BService) ApplicationContextHolder.getBean("bServiceDubbo");
    	bs.sayHi("sun");
        return "";
    }

}