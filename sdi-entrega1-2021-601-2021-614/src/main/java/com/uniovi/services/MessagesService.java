package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.repositories.MessagesRepository;

@Service
public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;
	
}
