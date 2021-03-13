package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddOfferFormValidator;

@Controller
public class OffersController {
	
	private static final Logger logger = LogManager.getLogger(OffersController.class);
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private AddOfferFormValidator addOfferFormValidator;
	
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		model.addAttribute("offer", new Offer());
		return "offer/add";
	}
	
	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(Model model, @Validated Offer offer, BindingResult result, Principal principal) {
		addOfferFormValidator.validate(offer, result);
		if (result.hasErrors()) {
			return "/offer/add";
		}
		User user = usersService.getUserAuthenticated();
		offersService.addOffer(offer, user);
		logger.info(String.format("Add item %s", offer.getTitulo()));
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/offer/mylist")
	public String getOffersForUser(Model model, Pageable pageable, Principal principal) {
		User user = usersService.getUserAuthenticated();
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.findAllByUser(pageable, user);
		model.addAttribute("myOffers", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/mylist";
	}
	
	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {
		Long offerUserId = offersService.getOfferById(id).getUser().getId();
		Long authenticatedUserId = usersService.getUserAuthenticated().getId();
		if (offerUserId == authenticatedUserId) {
			offersService.deleteOffer(id);
		}
		return "redirect:/offer/mylist";
	}
	
}
