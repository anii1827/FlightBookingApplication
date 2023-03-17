package com.flightApplication.pdfGenerator;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Admin1ApplicationTests {

	@Autowired
	UserRepo repo;
	
	@Autowired
	GroupRepo grepo;
	

	
	@Test
	void check() {
		
		User user1 = new User().setUsername("anil");
		User user2 = new User().setUsername("damini");
		UserGroup g = new UserGroup();
		g.setGroupName("UserGroup");
		
		/*for one to many relationshop*/
//		Set<User> list = new HashSet<>();
//		list.add(user1);  list.add(user2);
//		g.setUser(list);
		grepo.save(g);
		user1.setGroup(g); user2.setGroup(g);
		repo.save(user1);repo.save(user2);
		
	}
	
	@Transactional
	@Test
	void check2() {
//		repo.findAll().stream().forEach(System.out::println);
		System.out.println();
		grepo.findAll().stream().forEach(System.out::println);
		System.out.println();
	}

}
