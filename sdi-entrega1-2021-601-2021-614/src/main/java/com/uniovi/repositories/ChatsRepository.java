package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Chat;
import com.uniovi.entities.User;

public interface ChatsRepository extends CrudRepository<Chat, Long> {

	@Query("SELECT c FROM Chat c WHERE c.seller = ?1 ORDER BY c.id ASC")
	List<Chat> findAllByUserAsSeller(User seller);
	
	@Query("SELECT c FROM Chat c WHERE c.interested = ?1 ORDER BY c.id ASC")
	List<Chat> findAllByUserAsInterested(User interested);
	
}
