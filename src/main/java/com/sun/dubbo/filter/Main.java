package com.sun.dubbo.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;
import com.sun.dubbo.service.AService;
import com.sun.dubbo.service.BService;

public class Main {
	
	public static ApplicationContext context = null;
	
	public static void setApplicationContext(ApplicationContext context){
		Main.context = context;
	}
	
	public static Span setSpan(String parentId, String traceId, String invokerName, String serviceName){
		 RpcContext rpcContext =  RpcContext.getContext();
		 Span trace = new Span();
	     trace.setParentId(parentId);
	     trace.setTraceId(traceId);
	     trace.setInvokerName(invokerName);
	     trace.setServiceName(serviceName);
	     trace.setStartTime(new Date());
	     rpcContext.set("invokeObj", trace);
	     return trace;
	}
	
	public static void main(String[] args) throws IOException {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo-provider.xml"});    
	     context.start();
	     Runtime.getRuntime().addShutdownHook(new Thread() {
             public void run() {}
         });
	     Main.setApplicationContext(context);
	     //
	     RpcContext rpcContext = RpcContext.getContext();
		 rpcContext.setAttachment("Main", "Main");
	     Span spanA = Main.setSpan(null, "Main", "Head", "Main");
	     //
	     AService as = (AService) context.getBean("aServiceDubbo");
	     as.sayHi("sun");
	     //
	     rpcContext = RpcContext.getContext();
	     rpcContext.setAttachment("Main", "Main");
	     rpcContext.set("invokeObj", spanA);
	     //
	     Main.setSpan(null, "Main", "Head", "Main");
	     BService bs = (BService) context.getBean("bServiceDubbo");
	     bs.sayHi("sun");
	     //
	     spanA.setFinishTime(new Date());
	     System.err.println(JSON.json(spanA));
	     System.in.read();
	}

}
