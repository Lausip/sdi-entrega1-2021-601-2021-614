package com.uniovi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.ChatsService;
import com.uniovi.services.MessagesService;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class ChatsController {

	@Autowired
	private ChatsService chatsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private MessagesService messagesService;
	
	private Logger logger = LoggerFactory.getLogger(ChatsController.class);
	
	@RequestMapping("/chat/list")
	public String getList(Model model) {

		User user = usersService.getUserAuthenticated();
		logger.info(user.getEmail()+" ha accedido al listado de sus chats");
		model.addAttribute("chatsAsSellerList", chatsService.getChatsAsSellerByUser(user));
		model.addAttribute("chatsAsInterestedList", chatsService.getChatsAsInterestedByUser(user));
		return "chat/list";
	}
	
	@RequestMapping("/chat/details/{id}")
	public String getMessages(Model model, @PathVariable Long id) {
		Chat chat = chatsService.getChatById(id);
		User user = usersService.getUserAuthenticated();
		logger.info(user.getEmail()+" ha accedido a la conversacion del chat"+chat.getId());
		
		if (user.getId() == chat.getSeller().getId()) {
			model.addAttribute("theOtherUser", chat.getInterested());
		} else {
			model.addAttribute("theOtherUser", chat.getSeller());
		}
		
		model.addAttribute("offer", chat.getOffer());
		model.addAttribute("messagesList", messagesService.getMessagesByChat(chat));
		model.addAttribute("chat", chat);
		model.addAttribute("message", new Message());
		return "chat/details";
	}
	
	@RequestMapping(value = "/chat/delete/{id}")
	public String remove(@PathVariable Long id) {
		Chat chat = chatsService.getChat(id);
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido a eliminar un chat"+chat.getId());
		chatsService.deleteChat(chat);
		logger.info(String.format("Chat eliminado %d", id));
		return "redirect:/chat/list";
	}
	
	@RequestMapping(value = "/chat/add/{id}")
	public String setChat(@PathVariable Long id) {
		Offer offer = offersService.getOfferById(id);
		User user = usersService.getUserAuthenticated();
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido a añadir un chat");
		Chat chat = chatsService.getChatByOfferAndUserInterested(offer, user);
		if (chat == null) {
			chat = new Chat(offer.getUser(), usersService.getUserAuthenticated(), offer);
			logger.info(String.format("Chat añadido %d", id));
			chatsService.addChat(chat);
		}
		return "redirect:/chat/details/" + chat.getId();
	}
}
