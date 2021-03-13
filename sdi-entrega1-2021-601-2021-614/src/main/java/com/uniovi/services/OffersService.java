package com.uniovi.services;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;


@Service
public class OffersService {
	@Autowired
	private OffersRepository offersRepository;
	
	@Autowired
	private UsersRepository usersRepository;

	public void addOffer(Offer offer, User user) {
		offer.setUser(user);
		offer.setDate(new Date(new java.util.Date().getTime()));
		user.getOffers().add(offer);
		offersRepository.save(offer);
		usersRepository.save(user);
	}

	public Page<Offer> findAllByUser(Pageable pageable,User user) {
		//return offersRepository.findAllByUser(pageable,user);
		return null;
	}

}
