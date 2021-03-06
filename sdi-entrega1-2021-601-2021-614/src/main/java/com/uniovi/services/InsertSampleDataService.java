package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;
	
	@PostConstruct
	public void init() {
		
	}
	
}
