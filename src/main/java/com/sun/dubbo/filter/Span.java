package com.sun.dubbo.filter;

import java.util.Date;

/**
 * Span: 一次方法调用, 一个程序块的调用, 或者一次RPC/数据库访问
 * @author sun
 * 2017年4月24日 下午4:46:30
 *
 */
public class Span {

	private String parentId;
	
	private String traceId;
	
	//调用者名称
	private String invokerName;
	
	//执行服务名称
	private String serviceName;
	
	private Date startTime;
	
	private Date finishTime;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
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
