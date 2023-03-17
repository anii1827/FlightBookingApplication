package com.flightApplication.Admin.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
@EnableKafka
public class KafkaProducerConfig {
	
	@Bean
	public ProducerFactory<String, String> producerFactory(){
		System.out.println("producer factory method called");
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, String>(config);
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(){
		System.out.println("kafkaTemplate method called");
		return new KafkaTemplate<>(producerFactory());
	}
	
	@Bean
	public KafkaMessager getKafka() {
		System.out.println("getkafka method called");
		return new KafkaMessageImpl();
	}
}
