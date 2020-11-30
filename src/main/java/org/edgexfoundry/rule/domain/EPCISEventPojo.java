package org.edgexfoundry.rule.domain;

import com.jayway.jsonpath.ReadContext;

public class EPCISEventPojo {
	
	private AggregationEvent aggregationEvent;
	private CreationEvent creationEvent;
	private static ReadContext readContext;
	private String recordId;
	private int counterCount;
	private String productCode;
	public void showEvent(String msg) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>> : "+msg);
	}
	
	public AggregationEvent getAggregationEvent() {
		return aggregationEvent;
	}
	public void setAggregationEvent(AggregationEvent aggregationEvent) {
		this.aggregationEvent = aggregationEvent;
	}
	public CreationEvent getCreationEvent() {
		return creationEvent;
	}
	public void setCreationEvent(CreationEvent creationEvent) {
		this.creationEvent = creationEvent;
	}
	public static ReadContext getReadContext() {
		return readContext;
	}
	public static void setReadContext(ReadContext readContext) {
		EPCISEventPojo.readContext = readContext;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public int getCounterCount() {
		return counterCount;
	}
	public void setCounterCount(int counterCount) {
		this.counterCount = counterCount;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
