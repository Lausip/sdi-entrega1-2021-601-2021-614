package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.repositories.MessagesRepository;

@Service
public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;
	
	public void addMessage(Message message) {
		messagesRepository.save(message);
	}
	
	public List<Message> getMessagesByChat(Chat chat) {
		return messagesRepository.findAllByChat(chat);
	}
	
}
