package com.Userapp.Users.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.Userapp.Users.Services.UserService;

@Service
public class CustomeKafkaListener {

	
	@Autowired
	UserService userService;
	
	@KafkaListener(topics = "Flight-Block", groupId="group_id", containerFactory = "consumerKafkaFactory")
	public void consumeJson(String flight_id) {
		System.out.println(flight_id);
		userService.sendBlockEmail(flight_id);
	}
	
}
