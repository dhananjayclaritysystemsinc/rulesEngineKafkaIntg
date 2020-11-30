package org.edgexfoundry.messaging;

import org.edgexfoundry.domain.core.Event;
import org.edgexfoundry.engine.RuleEngine;
import org.edgexfoundry.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.ReadContext;

@Component
public class EPCISEventSubscriber {

//	@Autowired
//	KafkaConsumer kafkaConsumer;
	
	@Autowired
	public RuleEngine engine;

	public RuleEngine getRuleEngine() {
		return this.engine;
	}
	
	private void executeOnEvent(Event event) {
		engine.execute(event);
		System.out.println(">>>>>>>>>>>>"+event.getCreated());
//		logger.info("Event sent to rules engine for device id:  " + event.getDevice());
	}



}
