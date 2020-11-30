/*******************************************************************************
 * Copyright 2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * @microservice: support-rulesengine
 * @author: Jim White, Dell
 * @version: 1.0.0
 *******************************************************************************/

package org.edgexfoundry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.edgexfoundry.engine.RuleEngine;
import org.edgexfoundry.kafka.ConsumerCreator;
import org.edgexfoundry.kafka.IKafkaConstants;
import org.edgexfoundry.kafka.KafkaConsumer;
import org.edgexfoundry.messaging.EPCISEventSubscriber;
import org.edgexfoundry.rule.domain.AggregationEvent;
import org.edgexfoundry.rule.domain.CommonEventData;
import org.edgexfoundry.rule.domain.ContainerData;
import org.edgexfoundry.rule.domain.CreationEvent;
import org.edgexfoundry.rule.domain.EPCISEventPojo;
import org.edgexfoundry.rule.domain.ProductData;
import org.edgexfoundry.rule.domain.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

@SpringBootApplication(scanBasePackages = {"org.edgexfoundry", "org.springframework.cloud.client"})
@EnableAsync
@EnableDiscoveryClient
public class Application {

	@Autowired
	static ReadContext context;
	
//	@Autowired
//	static RuleEngine engine;
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		String templateName = "counterCheck.drl";
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		String welcomeMsg = ctx.getEnvironment().getProperty("app.open.msg");
		System.out.println("welcome message : "+welcomeMsg);
		
//		MessageListener listener = ctx.getBean(MessageListener.class);
//		String path = runConsumer();
//		System.out.println("Path : "+path);
		String productJson = getProductData();
		String containerJson = getContainerData();
		ObjectMapper mapper = new ObjectMapper();
		AggregationEvent aggregationEvent = mapper.readValue(containerJson.toString(), AggregationEvent.class);
		System.out.println("aggregationEvent : "+aggregationEvent.getEpcisBody().getEventList().get(0).getIsA());
		
		
		CreationEvent creationEvent = mapper.readValue(productJson.toString(), CreationEvent.class);
		System.out.println("creationEvent : "+creationEvent.epcisBody.getEventList().get(0).getIsA());
		
		//EPCISEventPojo contains either aggregation or creation event
//		EPCISEventPojo epcisEventPojo = new EPCISEventPojo();
//		epcisEventPojo.setAggregationEvent(aggregationEvent);
		
//		epcisEventPojo.setRecordId("test record");
//		ZeroMQEventSubscriber sub = ctx.getBean(ZeroMQEventSubscriber.class);
		
//		CreationEvent evnt = epcisEventPojo.getCreationEvent();
//		System.out.println("evnt : "+evnt);
		
		
		
		ReadContext readContextProduct = JsonPath.parse(productJson);
		System.out.println("readContextProduct : "+readContextProduct.read("$"));
		

		CommonEventData commonEventData = mapper.readValue(containerJson.toString(), CommonEventData.class);
		System.out.println("commonEventData : "+commonEventData.getEpcisBody().getEventList().get(0).getIsA());
		
		ReadContext readContext = JsonPath.parse(containerJson);
		System.out.println("readContextContainer : "+readContext.read("$"));
		EPCISEventSubscriber epcisEventSubscriber = ctx.getBean(EPCISEventSubscriber.class);
		RuleEngine engine = epcisEventSubscriber.getRuleEngine();
		commonEventData.setReadContext(readContext);
		engine.execute(commonEventData);
//		epcisEventSubscriber.receive();
//		RuleEngineControllerImpl reimpl = new RuleEngineControllerImpl();
//		Rule rule = new Rule();
//		reimpl.addRule(rule);
		
		//todo - in pojo .chec
		
		String recordId = getRecordId("");
		
//		sub.
//		System.out.println(">>>>>>>>>>>>>>>>>>>>..." + sub);
//		System.out.println(welcomeMsg);
//		sub.receive();
		
		String dataStr = getRowContainer();
		System.out.println("data : "+dataStr);
	}

	private static String getRowContainer() {
		return "{\n" + "	\"recordId\": \"c08091652\",\n" 
				     + "	\"counterCount\": 11,\n" 
					 + "	\"subProducts\": [{\n" + "		\"id\": \"p08091652\"\n"
					 + "	}, {\n" + "		\"id\": \"p08091653\"\n" + "	}],\n" + "	\"checkin\": [{\n"
				+ "		\"user\": \"INORBIT VADODARA\",\n" + "		\"timestamp\": 1599569832956\n" + "	}, {\n"
				+ "		\"user\": \"GK-M\",\n" + "		\"timestamp\": 1599579982817\n" + "	}, {\n"
				+ "		\"user\": \"GK-M\",\n" + "		\"timestamp\": 1599582620830\n" + "	}],\n"
				+ "	\"createdDate\": 1599564670803,\n" + "	\"createdBy\": \"Gurgaon\",\n"
				+ "	\"ProductCode\": \"c08091652\",\n" + "	\"recordType\": \"containerv2\",\n"
				+ "	\"statusUpdatedAt\": 1599582658945,\n" + "	\"checkout\": [{\n"
				+ "		\"checkoutTo\": \"INORBIT VADODARA\",\n" + "		\"user\": \"Gurgaon\",\n"
				+ "		\"timestamp\": 1599565965719\n" + "	}, {\n" + "		\"checkoutTo\": \"GK-M\",\n"
				+ "		\"user\": \"INORBIT VADODARA\",\n" + "		\"timestamp\": 1599579840371\n" + "	}, {\n"
				+ "		\"checkoutTo\": \"End User\",\n" + "		\"user\": \"GK-M\",\n"
				+ "		\"timestamp\": 1599582658945\n" + "	}],\n" + "	\"checkin\": [{\n"
				+ "		\"user\": \"Gurgaon\",\n" + "		\"timestamp\": 1599565965719\n" + "	}, {\n"
				+ "		\"user\": \"INORBIT VADODARA\",\n" + "		\"timestamp\": 1599579840371\n" + "	}, {\n"
				+ "		\"user\": \"GK-M\",\n" + "		\"timestamp\": 1599582658945\n" + "	}],\n"
				+ "	\"status\": \"Sold\",\n" + "	\"producttype\": \"ayurvedicitemv2\"\n" + "}\n" + "";
	}
	//
	public static String getRecordId(String path) {
//		  String str = context.read(path);
		Configuration conf = Configuration.defaultConfiguration();

		//
		String productJson = getProductData();// getContainerData();

		DocumentContext jsonContext = JsonPath.parse(productJson);
		Object epcList = jsonContext.read("$['epcisBody'].['eventList'].[0]['epcList']");
		String[] ary = epcList.toString().split("\\.");
		System.out.println("obj : " + ary[2].replace("\"]", ""));
		
		String containerJson = getContainerData();
		DocumentContext jsonContext1 = JsonPath.parse(containerJson);
		Object parentIdObj = jsonContext1.read("$['epcisBody'].['eventList'].[0]['parentID']");
		System.out.println("parentIdObj : "+parentIdObj.toString().split("\\.")[1]);
		ProductData productData = new ProductData();
		ContainerData containerData = new ContainerData();

//		  String recordId = productData.getRecordId();
//		 recordId = recordId.entrySet().toString();//containerData.getRecordId();

		return "";// recordId.toString();//str;
	}
	private static String getProductData() {
		
		
		return "{\n"
				+ "  \"id\": \"document1\",\n"
				+ "  \"isA\": \"EPCISDocument\",\n"
				+ "  \"schemaVersion\":\"2.0\",\n"
				+ "  \"creationDate\":\"Nov 17, 2020, 3:16:36 PM\",\n"
				+ "  \"format\":\"application/ld+json\",\n"
				+ "  \"epcisBody\": {\n"
				+ "   \"eventList\": [\n"
				+ "     {\n"
				+ "		  \"id\":\"5a2dfd07-e0e3-4644-b197-ac97473bc331\",\n"
				+ "		  \"isA\":\"ObjectEvent\",\n"
				+ "		  \"action\":\"OBSERVE\",\n"
				+ "		  \"bizStep\":\"urn:epcglobal:cbv:bizstep:departing\",\n"
				+ "		  \"disposition\":\"urn:epcglobal:cbv:disp: in_transit\",\n"
				+ "		  \"epcList\": [\"urn:epc:id:sgtin:67890.8908.p08091653\"],\n"
				+ "		  \"eventTime\":\"Sep 8, 2020, 10:00:58 PM\",\n"
				+ "		  \"eventTimeZoneOffset\":\"+5:30\",\n"
				+ "		  \"readPoint\": {\"id\": \"urn:epc:id:sgln:gurgaon17@mdh.com\"},\n"
				+ "		  \"bizLocation\": {\"id\": \"urn:epc:id:sgln:28.4594965:77.0266383\"}\n"
				+ "		  \n"
				+ "    }\n"
				+ "   ]\n"
				+ "  }\n"
				+ "}";
	}
	private static String getContainerData() {
		
		return "{\n"
				+ "  \"id\": \"_:document1\",\n"
				+ "  \"isA\": \"EPCISDocument\",\n"
				+ "  \"schemaVersion\":2.0,\n"
				+ "  \"creationDate\":\"Nov 17, 2020, 3:31:00 PM\",\n"
				+ "  \"format\":\"application/ld+json\",\n"
				+ "  \"epcisBody\": {\n"
				+ "   \"eventList\": [\n"
				+ "     {\n"
				+ "		  \"id\": \"8f2e7af8-69d4-43f0-9196-dd6b0088e1e5\",\n"
				+ "		  \"isA\":\"AggregationEvent\",\n"
				+ "		  \"eventTime\":\"Sep 8, 2020, 10:00:58 PM\",\n"
				+ "		  \"eventTimeZoneOffset\":\"+5:30\",\n"
				+ "		  \"parentID\":\"urn:epc:id:sscc:67890.c08091652\",\n"
				+ "		  \"childEPCs\":[\"urn:epc:id:sgtin:67890.8908.p08091652,urn:epc:id:sgtin:67890.8908.p08091653\"],\n"
				+ "		  \"action\": \"Observe\",\n"
				+ "		  \"bizStep\": \"urn:epcglobal:cbv:bizstep:departing\",\n"
				+ "		  \"disposition\": \"urn:epcglobal:cbv:disp:in_transit\",\n"
				+ "		  \"readPoint\": {\"id\": \"urn:epc:id:sgln:gurgaon17@mdh.com\"},\n"
				+ "		  \"bizLocation\": {\"id\": \"urn:epc:id:sgln:28.4594965:77.0266383]\"},\n"
				+ "		  \n"
				+ "		  \"childQuantityList\": [\n"
				+ "		  	{\"epcClass\":\"urn:epc:idpat:sgtin:producttype.*,quantity:2\"}\n"
				+ "		  	]\n"
				+ "    }\n"
				+ "   ]\n"
				+ "  }\n"
				+ "}";
	}
//	static String runConsumer() {
//		String consumeRecord="";
//		
//		
//		Consumer<Long, String> consumer = ConsumerCreator.createConsumer("epcisClient");
//
//		int noMessageToFetch = 0;
//		while (true) {
//			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(100);
//			if (consumerRecords.count() == 0) {
//				noMessageToFetch++;
//				if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
//					break;
//				else
//					continue;
//			}
//
//			consumerRecords.forEach(record -> {
//				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//				System.out.println("Record Key " + record.key());
//				System.out.println("Record value " + record.value());
//				System.out.println("Record partition " + record.partition());
//				System.out.println("Record offset " + record.offset());
//			});
//			consumer.commitAsync();
//		}
//		consumer.close();
//		return consumeRecord;
//	}
	
	private Map<String, Object> createMap(Rule rule) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("rulename", rule.getName());
	    map.put("conddeviceid", rule.getCondition().getDevice());
	    map.put("valuechecks", rule.getCondition().getChecks());
	    map.put("actiondeviceid", rule.getAction().getDevice());
	    map.put("actioncommandid", rule.getAction().getCommand());
	    map.put("commandbody", rule.getAction().getBody());
	    map.put("log", rule.getLog());
	    return map;
	  }
}
