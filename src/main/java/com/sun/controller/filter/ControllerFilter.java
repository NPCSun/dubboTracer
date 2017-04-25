package com.sun.controller.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;
import com.sun.dubbo.trace.Span;
import com.sun.dubbo.trace.Tracer;

public class ControllerFilter implements MethodInterceptor{
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//
		String traceId = request.getSession().getId()+System.currentTimeMillis();
	    //String parentId, String traceId, String invokerName, String serviceName
	    Span spanA = Tracer.buildAndSetSpan(null, traceId, "Client", "Request");
		
		Object obj = invocation.proceed();
		//
		spanA.setFinishTime(new Date());
		try{
			System.err.println(JSON.json(spanA));
		}catch(Exception e){
			
		}
		return obj;
	}

}
