package com.flightApplication.gatway;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.flightApplication.gatway.util.innerclass;

@SpringBootTest
class GatwayApplicationTests {

	@Test
	void contextLoads() {
		innerclass.innertest test = new innerclass.innertest();
		Method[] declaredMethods = test.getClass().getDeclaredMethods();
		System.out.println(declaredMethods);
	}

}
