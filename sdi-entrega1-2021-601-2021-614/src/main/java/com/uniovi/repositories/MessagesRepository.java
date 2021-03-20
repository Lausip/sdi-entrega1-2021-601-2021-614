package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;

public interface MessagesRepository extends CrudRepository<Message, Long>  {

}
