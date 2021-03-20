package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.uniovi.services.MessagesService;

@Controller
public class MessagesController {

	@Autowired
	private MessagesService messagesService;
	
	
	
}
