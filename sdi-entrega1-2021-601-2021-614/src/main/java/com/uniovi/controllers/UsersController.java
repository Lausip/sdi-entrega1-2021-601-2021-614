package com.uniovi.controllers;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;
	

	@Autowired
	private OffersService offersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	private Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			logger.error("Error al registrase");
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		logger.info(usersService.getUserAuthenticated().getEmail()+" Se ha registrado");
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
		logger.info("Se ha accedido al login");
        return "login";
    }
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model, Pageable pageable) {
		model.addAttribute("userAuthenticated", usersService.getUserAuthenticated());
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido a su home privado");
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.findAllHighlight(pageable);
		model.addAttribute("offersHihglight", offers.getContent());
		model.addAttribute("page", offers);
		
		return "home";
	}
	
	@RequestMapping("/user/list")
	public String getListado(Model model) {
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido al listado de usuarios");
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}
	
	@RequestMapping("/user/delete")
	public String deleteUsers(Model model, @RequestParam List<Long> userIds) {
		logger.info(usersService.getUserAuthenticated().getEmail()+" ha accedido a eliminar usuarios");
		for (Long id : userIds) {
			User user = usersService.getUserById(id);
			// Si el usuario tiene ROLE_STANDARD borrar.
			if (user.getRole().equals(rolesService.getRoles()[0])) {
				logger.info("Usuarios eliminados");
				usersService.deleteUser(id);
			}
		}
		return "redirect:/user/list";
	}
}
