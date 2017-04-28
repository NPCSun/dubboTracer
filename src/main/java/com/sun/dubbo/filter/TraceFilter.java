package com.sun.dubbo.filter;

import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;
import com.sun.dubbo.trace.Span;
import com.sun.dubbo.trace.Tracer;

public class TraceFilter implements MethodInterceptor{
	
	private static Logger log = Logger.getLogger(TraceFilter.class);
	
	private ServletOutputStream out = null;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//
		RpcContext rpcContext = RpcContext.getContext();
		Span parentSpan = (Span) rpcContext.get("parentSpan");
		if(parentSpan==null){
			parentSpan = (Span) JSON.parse(rpcContext.getAttachment("parentSpan"), Span.class);
		}
		Object obj = null;
		if(parentSpan!=null){
			String serviceName = invocation.getStaticPart().toString();
			serviceName = serviceName.substring(serviceName.lastIndexOf(".service.") + 9);
			serviceName = serviceName.substring(0, serviceName.indexOf("."));
			//String traceId, String parentId, String spanId, String invokerName, String serviceName
			int spanId = parentSpan.getCounter().intValue();
			if(!parentSpan.getServiceName().equals(serviceName)){
				spanId = parentSpan.getCounter().incrementAndGet();
			}
			Span span = Tracer.buildAndSetSpan(parentSpan.getTraceId(), 
												parentSpan.getSpanId(), 
												spanId, 
												parentSpan.getServiceName(), serviceName);
			//
			rpcContext.setAttachment("parentSpan", JSON.json(span));
			obj = invocation.proceed();
			//
			span.setFinishTime(new Date());
			if(!span.getInvokerName().equals(span.getServiceName())){
				System.err.println(JSON.json(span));
				/*if(out==null){
					HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
					out = response.getOutputStream();
				}
				out.write(JSON.json(span).getBytes("utf-8"));
				out.write("\r\n".getBytes("utf-8"));
				out.flush();*/
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
