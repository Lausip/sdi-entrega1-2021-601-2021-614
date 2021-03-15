package com.uniovi.controllers;

import java.util.Date;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
		model.addAttribute("userAuthenticated", usersService.getUserAuthenticated());
		return "offer/add";
	}
	
	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(Model model, Pageable pageable, @Validated Offer offer, BindingResult result) {
		addOfferFormValidator.validate(offer, result);
		if (result.hasErrors()) {
			return "/offer/add";
		}
		offer.setUser(usersService.getUserAuthenticated());
		offer.setDate(new Date(new java.util.Date().getTime()));
		offersService.addOffer(offer);
		logger.info(String.format("Add item %s", offer.getTitulo()));
		model.addAttribute("myOffers", offersService.findAllByUser(pageable, usersService.getUserAuthenticated()));
		return "redirect:/offer/mylist";
	}
	
	@RequestMapping(value = "/offer/mylist")
	public String getOffersForUser(Model model, Pageable pageable) {
		User user = usersService.getUserAuthenticated();
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.findAllByUser(pageable, user);
		model.addAttribute("myOffers", offers.getContent());
		model.addAttribute("page", offers);
		model.addAttribute("userAuthenticated", usersService.getUserAuthenticated());
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
	
	@RequestMapping("/offer/purchase/{id}")
	public String purchaseOffer(@PathVariable Long id) {
		Offer offer = offersService.getOfferById(id);
		User user = usersService.getUserAuthenticated();
		if (offer.getUser().getId() == user.getId()) {
			//Error.offer.pruchase.user
			return "redirect:/offer/list";
		}
		Double price = offer.getPrice();
		if (user.getMoney() < price) {
			//Error.offer.pruchase.money
			return "redirect:/offer/list";
		}
		user.decreaseMoney(offer.getPrice());
		offer.setPurchaser(user);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		return "redirect:/offer/purchasedlist";
	}
	
	@RequestMapping("/offer/purchasedlist")
	public String getPurchasedOffersForUser(Model model, Pageable pageable) {
		User purchaser = usersService.getUserAuthenticated();
		Page<Offer> purchasedOffers = offersService.findAllPurchasedByUser(pageable, purchaser);
		model.addAttribute("purchasedOffers", purchasedOffers.getContent());
		model.addAttribute("page", purchasedOffers);
		model.addAttribute("userAuthenticated", purchaser);
		return "offer/purchasedlist";
	}
	
	@RequestMapping(value = "/offer/list")
	public String getOffers(Model model, @PageableDefault(size = 5) Pageable pageable) {
		User user = usersService.getUserAuthenticated();
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.findAllExceptUser(pageable,user);
		model.addAttribute("userAuthenticated", user);
		model.addAttribute("offers", offers.getContent());
		model.addAttribute("dinero", user.getMoney());
		model.addAttribute("page", offers);
		return "offer/list";
	}
	
}
