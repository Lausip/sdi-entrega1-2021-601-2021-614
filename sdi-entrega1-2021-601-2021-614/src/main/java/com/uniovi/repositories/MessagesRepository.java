package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;

public interface MessagesRepository extends CrudRepository<Message, Long>  {

	@Query("SELECT m FROM Message m WHERE m.chat = ?1 ORDER BY m.date ASC")
	List<Message> findAllByChat(Chat chat);
	
}
