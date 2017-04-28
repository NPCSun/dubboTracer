package com.sun.controller.filter;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.common.json.JSON;
import com.sun.dubbo.trace.Span;
import com.sun.dubbo.trace.Tracer;
import com.xiaoleilu.hutool.crypto.SecureUtil;

public class ControllerFilter implements MethodInterceptor{
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		//
		String traceId = request.getSession().getId()+System.currentTimeMillis();
		traceId = SecureUtil.md5(traceId);
	    //String parentId, String traceId, String invokerName, String serviceName
	    Span spanA = Tracer.buildAndSetSpan(traceId, 0, 1, "Client", "Request");
		
		Object obj = invocation.proceed();
		//
		spanA.setFinishTime(new Date());
		try{
			System.err.println(JSON.json(spanA));
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			response.getOutputStream().write(JSON.json(spanA).getBytes("utf-8"));
			response.getOutputStream().write("\r\n".getBytes("utf-8"));
			response.getOutputStream().flush();
		}catch(Exception e){
			
		}
		return obj;
	}

}
