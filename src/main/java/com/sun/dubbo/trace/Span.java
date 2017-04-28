package com.sun.dubbo.trace;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Span: 一次方法调用, 一个程序块的调用, 或者一次RPC/数据库访问
 * @author sun
 * 2017年4月24日 下午4:46:30
 *
 */
public class Span {
	
	/**是一个全局的跟踪ID，是跟踪的入口点，根据需求来决定在哪生成traceId。
	比如一个http请求，首先入口是web应用，一般看完整的调用链这里自然是traceId生成的起点，结束点在web请求返回点。*/
	
	private String traceId;

	private int parentId;
	
	private AtomicInteger counter = new AtomicInteger(1);
	
	private int spanId;
	
	//调用者名称
	private String invokerName;
	
	//执行服务名称
	private String serviceName;
	
	private Date startTime;
	
	private Date finishTime;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public AtomicInteger getCounter() {
		return counter;
	}

	public void setCounter(AtomicInteger counter) {
		this.counter = counter;
	}

	public int getSpanId() {
		return spanId;
	}

	public void setSpanId(int spanId) {
		this.spanId = spanId;
	}

	public String getInvokerName() {
		return invokerName;
	}

	public void setInvokerName(String invokerName) {
		this.invokerName = invokerName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	
}
