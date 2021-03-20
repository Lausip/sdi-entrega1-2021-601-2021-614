package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Chat;
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
	
	@RequestMapping("/chat/details/{id}")
	public String getMessages(Model model, @PathVariable Long id) {
		Chat chat = chatsService.getChatById(id);
		User user = usersService.getUserAuthenticated();
		
		if (user.getId() == chat.getSeller().getId()) {
			model.addAttribute("theOtherUser", chat.getInterested());
		} else {
			model.addAttribute("theOtherUser", chat.getSeller());
		}
		
		model.addAttribute("offer", chat.getOffer());
		model.addAttribute("messagesList", chat.getMessages());
		model.addAttribute("chat", chat);
		
		return "chat/details";
	}
	
}
