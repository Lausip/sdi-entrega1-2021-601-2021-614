package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User seller;
	
	@ManyToOne
	@JoinColumn(name = "interested_id")
	private User interested;
	
	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	
	@OneToMany(mappedBy = "chat",  cascade=CascadeType.ALL)
	private Set<Message> messages = new HashSet<Message>();
	
	
	public Chat(User seller, User interested, Offer offer) {
		super();
		this.seller = seller;
		this.interested = interested;
		this.offer = offer;
	}

	public Chat() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getInterested() {
		return interested;
	}

	public void setInterested(User interested) {
		this.interested = interested;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
}
