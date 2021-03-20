package com.uniovi.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offer {
	
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String description;
	private Date date;
	private Double price;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "purchaser_id")
	private User purchaser;
	
	private boolean purchased;
	
	@OneToMany(mappedBy = "offer",  cascade=CascadeType.ALL)
	private Set<Chat> chats = new HashSet<Chat>();
	
	
	public Offer(Long id, String titulo, Date date, String description, Double price,User user) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.date = date;
		this.titulo = titulo;
		this.user=user;
	}
	
	public Offer(String titulo, String description, Double price, User user) {
		super();
		this.description = description;
		this.price = price;
		this.date = new Date(new java.util.Date().getTime());
		this.titulo = titulo;
		this.user = user;
		this.purchased = false;
	}
	
	public Offer() {
		
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String title) {
		this.titulo = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(User purchaser) {
		this.purchaser = purchaser;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}
	
}