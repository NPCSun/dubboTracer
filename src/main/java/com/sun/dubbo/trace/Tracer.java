package com.sun.dubbo.trace;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.dubbo.rpc.RpcContext;

public class Tracer {
	
	public static Span buildAndSetSpan(String traceId, int parentId, int spanId, String invokerName, String serviceName){
		 RpcContext rpcContext =  RpcContext.getContext();
		 Span span = new Span();
		 span.setCounter(new AtomicInteger(spanId));
		 span.setTraceId(traceId);
	     span.setParentId(parentId);
	     span.setSpanId(spanId);
	     span.setInvokerName(invokerName);
	     span.setServiceName(serviceName);
	     span.setStartTime(new Date());
	     rpcContext.set("parentSpan", span);
	     return span;
	}
}
