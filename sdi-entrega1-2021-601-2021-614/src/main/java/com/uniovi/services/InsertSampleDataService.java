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
		User user1 = new User("Jose@gmail.com", "Jose", "Arturo");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user1);
	}

}
