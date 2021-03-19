package com.uniovi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {
	
	@Autowired
	private OffersRepository offersRepository;
	
	public void addOffer(Offer offer) {
		offer.setDate(new Date(new java.util.Date().getTime()));
		offersRepository.save(offer);
	}

	public Page<Offer> findAllByUser(Pageable pageable,User user) {
		return offersRepository.findAllByUser(pageable,user);
	}
	
	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}
	
	public Offer getOfferById(Long id) {
		return offersRepository.findById(id).get();
	}
	
	public Page<Offer> findAllPurchasedByUser(Pageable pageable, User user) {
		return offersRepository.findAllPurchasedByUser(pageable, user);
	}


	public Page<Offer> findAllExceptUser(Pageable pageable, User user) {
		return offersRepository.findAllExceptUser(pageable,user);
	}

	public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText, User user) {
			return offersRepository.earchOffersByTitle(pageable,user,searchText);
	}

	public void addOfferUser(Offer offer, User user) {
		offer.setUser(user);
		offer.setDate(new Date(new java.util.Date().getTime()));
		offersRepository.save(offer);
		
	}

}
