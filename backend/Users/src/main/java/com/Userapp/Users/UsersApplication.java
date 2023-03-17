package com.Userapp.Users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.Userapp.Users.RestAPI.FeignErrorDecoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}
	
	 	@Bean
	    public FeignErrorDecoder errorDecoder() {
	        return new FeignErrorDecoder();
	    }

}	