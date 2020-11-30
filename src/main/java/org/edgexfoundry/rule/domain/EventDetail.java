package org.edgexfoundry.rule.domain;

import java.sql.Timestamp;
import java.util.List;

public class EventDetail {

	private String id;
	private String isA;
	private String eventTime;
	private String eventTimeZoneOffset;
	private String parentID;
	private String recordId;
	private List<String> childEPCs;
	private List<String> epcList;
//	private String epcList;
	private String action;
	private String bizStep;
	private String disposition;
	private ReadPoint readPoint;
	private BizLocation bizLocation;
	private List<childQuantity> childQuantityList;
	private String productType;
	private int subProductCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsA() {
		return isA;
	}
	public void setIsA(String isA) {
		this.isA = isA;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventTimeZoneOffset() {
		return eventTimeZoneOffset;
	}
	public void setEventTimeZoneOffset(String eventTimeZoneOffset) {
		this.eventTimeZoneOffset = eventTimeZoneOffset;
	}
	
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	public List<String> getChildEPCs() {
		return childEPCs;
	}
	public void setChildEPCs(List<String> childEPCs) {
		this.childEPCs = childEPCs;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getBizStep() {
		return bizStep;
	}
	public void setBizStep(String bizStep) {
		this.bizStep = bizStep;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public ReadPoint getReadPoint() {
		return readPoint;
	}
	public void setReadPoint(ReadPoint readPoint) {
		this.readPoint = readPoint;
	}
	public BizLocation getBizLocation() {
		return bizLocation;
	}
	public void setBizLocation(BizLocation bizLocation) {
		this.bizLocation = bizLocation;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getSubProductCount() {
		return subProductCount;
	}
	public void setSubProductCount(int subProductCount) {
		this.subProductCount = subProductCount;
	}

	public List<childQuantity> getChildQuantityList() {
		return childQuantityList;
	}
	public void setChildQuantityList(List<childQuantity> childQuantityList) {
		this.childQuantityList = childQuantityList;
	}
	public List<String> getEpcList() {
		return epcList;
	}
	public void setEpcList(List<String> epcList) {
		this.epcList = epcList;
	}
	
	
	
}

