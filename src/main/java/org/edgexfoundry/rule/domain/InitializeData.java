package org.edgexfoundry.rule.domain;

public class InitializeData {

	private String gsprefix;
	private String itemRef;
	private String timezone;
	private String withConnectionTo;
	private int withConnectionToPort;
	private String withSyncopeConnection;
	private int withSyncopeConnectionPort;
	private String kafkaBrokerIp;
	private int kafkaBrokerPort;
	private String clientId;

	public String getGsprefix() {
		return gsprefix;
	}

	public void setGsprefix(String gsprefix) {
		this.gsprefix = gsprefix;
	}

	public String getItemRef() {
		return itemRef;
	}

	public void setItemRef(String itemRef) {
		this.itemRef = itemRef;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getWithConnectionTo() {
		return withConnectionTo;
	}

	public void setWithConnectionTo(String withConnectionTo) {
		this.withConnectionTo = withConnectionTo;
	}

	public int getWithConnectionToPort() {
		return withConnectionToPort;
	}

	public void setWithConnectionToPort(int withConnectionToPort) {
		this.withConnectionToPort = withConnectionToPort;
	}

	public String getWithSyncopeConnection() {
		return withSyncopeConnection;
	}

	public void setWithSyncopeConnection(String withSyncopeConnection) {
		this.withSyncopeConnection = withSyncopeConnection;
	}

	public int getWithSyncopeConnectionPort() {
		return withSyncopeConnectionPort;
	}

	public void setWithSyncopeConnectionPort(int withSyncopeConnectionPort) {
		this.withSyncopeConnectionPort = withSyncopeConnectionPort;
	}

	public String getKafkaBrokerIp() {
		return kafkaBrokerIp;
	}

	public void setKafkaBrokerIp(String kafkaBrokerIp) {
		this.kafkaBrokerIp = kafkaBrokerIp;
	}

	public int getKafkaBrokerPort() {
		return kafkaBrokerPort;
	}

	public void setKafkaBrokerPort(int kafkaBrokerPort) {
		this.kafkaBrokerPort = kafkaBrokerPort;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
