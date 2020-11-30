package org.edgexfoundry.rule.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jayway.jsonpath.ReadContext;

@Entity
@Table(name = "tb_event_data")
public class CommonEventData {
	//timestamp, productcode, bizlocation,disposition
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id")
	public Integer eventId;
	
	@Column(name = "id")
	public String id;
	
	@Column(name = "is_a")
    public String isA;
	
	@Column(name = "schema_version")
    public String schemaVersion;
	
	@Column(name = "creation_date")
    public String creationDate;
	
	@Column(name = "format")
    public String format;
	
	@Column(name = "timestamp")
	public Timestamp timestamp;
	
	@Column(name = "productcode")
	public String productcode;
	
	@Column(name = "bizlocation")
	public String bizlocation;
	
	@Column(name = "disposition")
	public String disposition;
	
	@Transient
    public ReadContext readContext;
    
	@Column(name = "counter_count")
	public int counterCount;
	
	@Transient
	public boolean getGeoFenceCheck;
	
    @Transient
    public EpcisBody epcisBody;
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
	public String getSchemaVersion() {
		return schemaVersion;
	}
	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public ReadContext getReadContext() {
		return readContext;
	}
	public void setReadContext(ReadContext readContext) {
		this.readContext = readContext;
	}
	public EpcisBody getEpcisBody() {
		return epcisBody;
	}
	public void setEpcisBody(EpcisBody epcisBody) {
		this.epcisBody = epcisBody;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Timestamp getTimestamp() {
		String timeStr = this.getEpcisBody().getEventList().get(0).getEventTime();
		timestamp = java.sql.Timestamp.valueOf(timeStr);
		System.out.println("timestamp : " + timestamp);
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getBizlocation() {
		return bizlocation;
	}
	public void setBizlocation(String bizlocation) {
		this.bizlocation = bizlocation;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public int getCounterCount() {
		//get from readcontext , from container or product data
		return 11;
	}
	public void setCounterCount(int counterCount) {
		this.counterCount = counterCount;
	}
	public boolean isGetGeoFenceCheck() {
		return true;
	}
	public void setGetGeoFenceCheck(boolean getGeoFenceCheck) {
		this.getGeoFenceCheck = getGeoFenceCheck;
	}
	
    
}
