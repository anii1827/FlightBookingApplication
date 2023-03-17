package com.flightApplication.Admin.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public abstract class KafkaMessager {
		
		@Autowired
		KafkaTemplate<String, String> kafka;
	
		protected void send(String topic, String Flight_id) {
			int id = (int)(Math.floor(Math.random()*100));
			System.out.println("send method called");
			System.out.println("topic "+topic);
			System.out.println("id "+Flight_id);
			kafka.send(topic, Flight_id);
		}
	
		public abstract void sendMessageToConsumer(String Topic, String id);
	
}
