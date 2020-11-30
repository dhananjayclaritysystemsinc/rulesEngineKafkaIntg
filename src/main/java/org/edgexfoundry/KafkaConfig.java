package org.edgexfoundry;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
@Configuration
public class KafkaConfig {

	@KafkaListener(topics = "epcisClient", groupId = "consumerGroupEPCIS", containerFactory = "epcisClientKafkaListenerContainerFactory")
    public void listenGroupEpcisClient(String message) {
        System.out.println("Received Message in group 'consumerGroupEPCIS': " + message);
    }
}
