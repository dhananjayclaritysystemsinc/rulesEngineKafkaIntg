package org.edgexfoundry.rule.domain;

public class AggregationEvent extends CommonEventData {
   
    public EpcisBody epcisBody;
    
    
	public EpcisBody getEpcisBody() {
		return epcisBody;
	}
	public void setEpcisBody(EpcisBody epcisBody) {
		this.epcisBody = epcisBody;
	}
    
    
    
}