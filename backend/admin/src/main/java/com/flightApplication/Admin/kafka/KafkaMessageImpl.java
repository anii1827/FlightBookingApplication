package com.flightApplication.Admin.kafka;

public class KafkaMessageImpl extends KafkaMessager {
	@Override
	public void sendMessageToConsumer(String Topic, String flight_id) {
				send(Topic, flight_id);
	}
}
