package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private RolesService rolesService;
	@Autowired
	private UsersService usersService;


	@PostConstruct
	public void init() {
		User user1 = new User("admin@email.com", "Admin", "Admin");
		user1.setPassword("admin");
		user1.setRole(rolesService.getRoles()[1]);
		usersService.addUser(user1);
		
		User user2 = new User("Javi@gmail.com", "Javi", "Lopez de Juan");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user2);
		
	}

}
