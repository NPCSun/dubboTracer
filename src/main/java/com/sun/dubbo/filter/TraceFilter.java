package com.sun.dubbo.filter;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;
import com.sun.dubbo.trace.Span;
import com.sun.dubbo.trace.Tracer;

public class TraceFilter implements MethodInterceptor{
	
	private static Logger log = Logger.getLogger(TraceFilter.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//
		RpcContext rpcContext = RpcContext.getContext();
		Span parentSpan = (Span) rpcContext.get("parentSpan");
		Object obj = null;
		if(parentSpan!=null){
			String serviceName = invocation.getStaticPart().toString();
			serviceName = serviceName.substring(serviceName.lastIndexOf(".service.") + 9);
			serviceName = serviceName.substring(0, serviceName.indexOf("."));
			//String parentId, String traceId, String invokerName, String serviceName
			Span span = Tracer.buildAndSetSpan(parentSpan.getTraceId(), serviceName, parentSpan.getServiceName(), serviceName);
			
			obj = invocation.proceed();
			//
			span.setFinishTime(new Date());
			if(!span.getParentId().equals(span.getTraceId())){
				System.err.println(JSON.json(span));
			}
		}else{
			obj = invocation.proceed();
		}
		
		if(parentSpan!=null && parentSpan.getFinishTime()==null){
			 rpcContext = RpcContext.getContext();
			 rpcContext.set("parentSpan", parentSpan);
		}
		
		return obj;
	}

}
