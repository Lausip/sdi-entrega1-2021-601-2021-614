package com.uniovi.controllers;


import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddOfferFormValidator;
import com.uniovi.validators.PurchaseOfferValidator;

@Controller
public class OffersController {
	
	private Logger logger = LoggerFactory.getLogger(OffersController.class);
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private AddOfferFormValidator addOfferFormValidator;
	
	@Autowired
	private PurchaseOfferValidator purchaseOfferValidator;
	
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido añadir ofertas");
		model.addAttribute("offer", new Offer());
		model.addAttribute("userAuthenticated", usersService.getUserAuthenticated());
		return "offer/add";
	}
	
	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(Model model, Pageable pageable, @Validated Offer offer, BindingResult result) {
		User user=usersService.getUserAuthenticated();
		offersService.addOfferUser(offer,user);
		addOfferFormValidator.validate(offer, result);
		
		if (result.hasErrors()) {
			logger.error(usersService.getUserAuthenticated().getEmail()+": Error al añadir");
			offersService.removeOfferUser(offer,user);
			model.addAttribute("userAuthenticated", usersService.getUserAuthenticated());
			return "offer/add";
		}

		logger.info(String.format("%s añadió la oferta %s",user.getEmail(), offer.getTitulo()));
		
		model.addAttribute("myOffers", offersService.findAllByUser(pageable, usersService.getUserAuthenticated()));
		return "redirect:/offer/mylist";
	}
	
	@RequestMapping(value = "/offer/mylist")
	public String getOffersForUser(Model model, Pageable pageable) {
		User user = usersService.getUserAuthenticated();
		
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido a listar sus ofertas");
		
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.findAllByUser(pageable, user);
		model.addAttribute("myOffers", offers.getContent());
		model.addAttribute("page", offers);
		model.addAttribute("userAuthenticated", usersService.getUserAuthenticated());
		return "offer/mylist";
	}
	
	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {	
		Offer offer = offersService.getOfferById(id);
		Long offerUserId = offersService.getOfferById(id).getUser().getId();
		Long authenticatedUserId = usersService.getUserAuthenticated().getId();
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido a eliminar oferta"+offer.getTitulo());
		if (offerUserId == authenticatedUserId) {
			logger.info(usersService.getUserAuthenticated().getEmail()+" Error al eliminar oferta");
			offersService.removeOfferUser(offer,usersService.getUserAuthenticated());
		}
		return "redirect:/offer/mylist";
	}
	
	@RequestMapping("/offer/purchase/{id}")
	public String purchaseOffer(@PathVariable Long id, @Validated Offer offer, BindingResult result) {
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido a comprar oferta");
		offer = offersService.getOfferById(id);
		User user = usersService.getUserAuthenticated();
		offer.setPurchaser(user);
		
		purchaseOfferValidator.validate(offer, result);
		if (result.hasErrors()) {
			logger.error(user.getEmail()+"Error:comprar oferta");
			offer.setPurchaser(null);
			return "redirect:/offer/list";
		}
		logger.info(String.format("%s compró la oferta %s",user.getEmail(), offer.getTitulo()));
		user.decreaseMoney(offer.getPrice());
		offer.setPurchaser(user);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		return "redirect:/offer/purchasedlist";
	}
	
	@RequestMapping("/offer/purchasedlist")
	public String getPurchasedOffersForUser(Model model, Pageable pageable) {
		User purchaser = usersService.getUserAuthenticated();
		logger.info(purchaser.getEmail()+" ha accedido al listado de ofertas compradas");
		Page<Offer> purchasedOffers = offersService.findAllPurchasedByUser(pageable, purchaser);
		model.addAttribute("purchasedOffers", purchasedOffers.getContent());
		model.addAttribute("page", purchasedOffers);
		model.addAttribute("userAuthenticated", purchaser);
		return "offer/purchasedlist";
	}
	
	@RequestMapping(value = "/offer/list")
	public String getOffers(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(value = "", required = false) String searchText) {
		User user = usersService.getUserAuthenticated();
		logger.info(user.getEmail()+" ha accedido al listado de ofertas");
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			searchText = "%"+searchText+"%";
			offers =offersService.searchOffersByTitle(pageable, searchText, user);
		} else {
			offers = offersService.findAllExceptUser(pageable,user);
		}
		model.addAttribute("userAuthenticated", user);
		model.addAttribute("offers", offers.getContent());
		model.addAttribute("dinero", user.getMoney());
		model.addAttribute("page", offers);
		return "offer/list";
	}
	
	@RequestMapping("/offer/destacada/{id}")
	public String highlighter(@PathVariable Long id,HttpSession session) {

		Offer offer = offersService.getOfferById(id);
		User user = usersService.getUserAuthenticated();

		if(offer == null || user == null) {
			throw new IllegalStateException("Illegal");
		}
		if(user.getMoney() < 20) {
			logger.error("No suficiente dinero");
			session.setAttribute("highlight", true);
			return "redirect:/offer/mylist";
		}
		else {
		offersService.setHighlight(offer, user);
		session.setAttribute("highlight", false);
		logger.info(String.format("%s ha destacado la oferta %s", user.getEmail(),offer.getTitulo()));
		}

		return "redirect:/offer/mylist";
	}
	
	@RequestMapping("/offer/normal/{id}")
	public String nohighlighter(@PathVariable Long id,HttpSession session) {
		Offer offer = offersService.getOfferById(id);
		User user = usersService.getUserAuthenticated();
		offersService.setNoHighlight(offer, user);
		if(user.getMoney() >= 20) {
			session.setAttribute("highlight", false);
		}
		logger.info(String.format("%s ha normalizado la oferta %s", user.getEmail(), offer.getTitulo()));

		return "redirect:/offer/mylist";
	}
	
	@RequestMapping("/offer/mylist/update")
	public String updateMyList(Model model, Pageable pageable) {
		User user = usersService.getUserAuthenticated();
		Page<Offer> offers = offersService.findAllByUser(pageable, user);
		model.addAttribute("myOffers", offers.getContent());
		return "offer/mylist :: myTableOffers";
	}
}
