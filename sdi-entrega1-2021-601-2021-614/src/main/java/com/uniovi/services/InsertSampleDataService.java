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
		
		User user3 = new User("pepe@email.com", "Pepe", "Álvarez Pérez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user3);
		
		User user4 = new User("juana@email.com", "Juana", "Fernández");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user4);
		
		User user5 = new User("alberto@email.com", "Alberto", "Rodríguez Álvarez");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user5);
		
		User user6 = new User("susana@email.com", "Susana", "González Almonte");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user6);
		
		User user7 = new User("javier@email.com", "Javier", "de la Fuente");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user7);
		
		User user8 = new User("fernando@email.com", "Fernando Manuel", "García del Monte");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user8);
		
		User user9 = new User("pepita@email.com", "Pepita", "Alonso de la Torre");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user9);
	}

}
