package com.sun.dubbo.filter;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;

public class TraceFilter implements MethodInterceptor{
	
	private static Logger log = Logger.getLogger(TraceFilter.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//
		RpcContext rpcContext = RpcContext.getContext();
		Span parentSpan = (Span) rpcContext.get("invokeObj");
		
		String serviceName = invocation.getStaticPart().toString();
		serviceName = serviceName.substring(serviceName.lastIndexOf(".service.") + 9);
		serviceName = serviceName.substring(0, serviceName.indexOf("."));
		//
		Span span = Main.setSpan(parentSpan.getTraceId(), serviceName, parentSpan.getServiceName(), serviceName);
		
		Object obj = invocation.proceed();
		//
		span.setFinishTime(new Date());
		if(!span.getParentId().equals(span.getTraceId())){
			System.err.println(JSON.json(span));
		}
		rpcContext = RpcContext.getContext();
		return obj;
	}

}
