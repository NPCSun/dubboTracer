package com.sun.dubbo.trace;

import java.util.Date;

import com.alibaba.dubbo.rpc.RpcContext;

public class Tracer {
	
	public static Span buildAndSetSpan(String parentId, String traceId, String invokerName, String serviceName){
		 RpcContext rpcContext =  RpcContext.getContext();
		 Span span = new Span();
	     span.setParentId(parentId);
	     span.setTraceId(traceId);
	     span.setInvokerName(invokerName);
	     span.setServiceName(serviceName);
	     span.setStartTime(new Date());
	     rpcContext.set("parentSpan", span);
	     return span;
	}
}
