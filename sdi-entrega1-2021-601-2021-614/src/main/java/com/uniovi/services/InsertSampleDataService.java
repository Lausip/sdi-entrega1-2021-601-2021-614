package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offersService;
	
	@Autowired
	private ChatsService chatsService;
	
	@Autowired
	private MessagesService messagesService;

	@PostConstruct
	public void init() {
		
		// Añadir usuarios
		
		User user1 = new User("admin@email.com", "Admin", "Admin");
		user1.setPassword("admin");
		user1.setRole(rolesService.getRoles()[1]);
		usersService.addUser(user1);
		
		User user2 = new User("Javi@gmail.com", "Javi", "Lopez de Juan");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user2);
		
		User user3 = new User("pepe@email.com", "Pepe", "Álvarez Pérez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user3);
		
		User user4 = new User("juana@email.com", "Juana", "Fernández");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user4);
		
		User user5 = new User("alberto@email.com", "Alberto", "Rodríguez Álvarez");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user5);
		
		User user6 = new User("susana@email.com", "Susana", "González Almonte");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user6);
		
		User user7 = new User("javier@email.com", "Javier", "de la Fuente");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user7);
		
		User user8 = new User("fernando@email.com", "Fernando Manuel", "García del Monte");
		user8.setMoney(0);
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user8);
		
		User user9 = new User("pepita@email.com", "Pepita", "Alonso de la Torre");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user9);
		
		
		// Añadir ofertas
		
		// ofertas user3
		Offer offer = new Offer("Pelota", "Pelota de playa", 10.0, user3,false);
		offersService.addOffer(offer);
		
		offer = new Offer("Juguete", "Juguete infantil", 10.0, user3,false);
		offersService.addOffer(offer);
		Chat chat = new Chat(user3, user5, offer);
		chatsService.addChat(chat);
		Message message = new Message("Hola, soy Alberto, el precio es negociable?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, cuánto me ofreces?", user3, chat);
		messagesService.addMessage(message);
		message = new Message("8€, qué te parece?", user5, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Caja", "Caja de cartón azul", 5.0, user3,false);
		offersService.addOffer(offer);
		
		offer = new Offer("Papel", "Papel de regalo", 1.0, user3,false);
		offersService.addOffer(offer);
		
		offer = new Offer("Folios", "Paquete de 500 folios", 4.0, user3,false);
		offersService.addOffer(offer);
		
		offer = new Offer("Bolígrafo", "Bolígrafo de tinta líquida", 2.0, user3,false);
		offersService.addOffer(offer);
		
		offer = new Offer("Bicicleta", "Bicicleta infantil", 70.0, user3,false);
		offersService.addOffer(offer);
		
		offer = new Offer("Patinete", "Patinete eléctrico", 280.0, user3,false);
		offersService.addOffer(offer);
		chat = new Chat(user3, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, soy Alberto, cuánta potencia tiene el patinete?", user5, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Rotuladores", "Rotuladores de colores", 12.0, user3,false);
		offersService.addOffer(offer);
		chat = new Chat(user3, user8, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, cuántos rotuladores vienen?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Buenas, vienen 12", user3, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Esterilla", "Esterilla de yoga", 10.0, user3,false);
		offersService.addOffer(offer);
		
		// Ofertas user5
		offer = new Offer("Pato", "Pato de goma", 3.0, user5,true);
		offersService.addOffer(offer);
		chat = new Chat(user5, user3, offer);
		chatsService.addChat(chat);
		message = new Message("Hola!", user3, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Vestido", "Vestido azul", 20.0, user5,false);
		offersService.addOffer(offer);
		System.out.println("Vestido: " + offer.getId());
		
		offer = new Offer("Tienda", "Tienda de campaña", 80.0, user5,false);
		offersService.addOffer(offer);
		System.out.println("Tienda: " + offer.getId());
		
		offer = new Offer("Saco", "Saco de dormir", 11.0, user5,false);
		offersService.addOffer(offer);
		
		// Ofertas user 8
		offer = new Offer("Zapatos", "Zapatos de baile", 11.0, user8,false);
		offersService.addOffer(offer);
		chat = new Chat(user8, user3, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, estoy interesado", user3, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, cuál es tu duda?", user8, chat);
		messagesService.addMessage(message);
	}

}
