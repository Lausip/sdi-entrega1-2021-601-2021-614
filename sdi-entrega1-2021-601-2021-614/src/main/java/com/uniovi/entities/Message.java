package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	
	private Date date;
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "chat_id")
	private Chat chat;

	
	public Message(String text, User author, Chat chat) {
		super();
		this.text = text;
		this.author = author;
		this.chat = chat;
		this.date = new Date(new java.util.Date().getTime());
	}

	public Message() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
}
