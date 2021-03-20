package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.ChatsService;
import com.uniovi.services.UsersService;

@Controller
public class ChatsController {

	@Autowired
	private ChatsService chatsService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/chat/list")
	public String getList(Model model) {
		User user = usersService.getUserAuthenticated();
		model.addAttribute("chatsAsSellerList", chatsService.getChatsAsSellerByUser(user));
		model.addAttribute("chatsAsInterestedList", chatsService.getChatsAsInterestedByUser(user));
		return "chat/list";
	}
	
}
