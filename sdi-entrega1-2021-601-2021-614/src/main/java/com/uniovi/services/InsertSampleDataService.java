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
		
		User user2 = new User("pepe@email.com", "Pepe", "Álvarez Pérez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		user2.setMoney(100.0);
		usersService.addUser(user2);
		
		User user3 = new User("juana@email.com", "Juana", "Fernández");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		user3.setMoney(20.0);
		usersService.addUser(user3);
		
		User user4 = new User("alberto@email.com", "Alberto", "Rodríguez Álvarez");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		user4.setMoney(0.0);
		usersService.addUser(user4);
		
		User user5 = new User("susana@email.com", "Susana", "González Almonte");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		user5.setMoney(80.0);
		usersService.addUser(user5);
		
		User user6 = new User("fernando@email.com", "Fernando Manuel", "García del Monte");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		user6.setMoney(10.0);
		usersService.addUser(user6);
		
		
		// Añadir ofertas
		
		// Ofertas user2
		Offer offer = new Offer("Juguete", "Juguete infantil", 10.0, user2,false);
		offersService.addOffer(offer);
		Chat chat = new Chat(user2, user4, offer);
		chatsService.addChat(chat);
		Message message = new Message("Hola, soy Alberto, el precio es negociable?", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, cuánto me ofreces?", user2, chat);
		messagesService.addMessage(message);
		message = new Message("8€, qué te parece?", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Mucho mejor, creo que me lo voy a quedar, me lo pensaré", user2, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Patinete", "Patinete eléctrico", 280.0, user2,false);
		offersService.addOffer(offer);
		chat = new Chat(user2, user4, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, soy Alberto, cuánta potencia tiene el patinete?", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Buenas tardes, no recuerdo cuánta es, pero va muy bien", user2, chat);
		messagesService.addMessage(message);
		message = new Message("No sé...", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Sube muy bien las cuestas incluso", user2, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user3);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Rotuladores", "Rotuladores de colores", 12.0, user2,false);
		offersService.addOffer(offer);
		chat = new Chat(user2, user6, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, cuántos rotuladores vienen?", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Buenas, vienen 12", user2, chat);
		messagesService.addMessage(message);
		message = new Message("No parecen muy buenos, son de marca?", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Son de marca blanca, pero pintan muy bien", user2, chat);
		messagesService.addMessage(message);
		
		
		// Ofertas user3
		offer = new Offer("Raqueta", "Raqueta de tenis", 20.0, user3,false);
		offersService.addOffer(offer);
		chat = new Chat(user3, user6, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, de qué tipo es la raqueta?", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, es profesional", user3, chat);
		messagesService.addMessage(message);
		message = new Message("Ah, pues entonces no me interesa, estoy empezando", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Sin problema", user3, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user6);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Saco", "Saco de dormir", 11.0, user3,false);
		offersService.addOffer(offer);
		chat = new Chat(user3, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, me interesa el saco, pero me preocupa que no sea lo suficientemente cálido", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Buenas tardes, es para 12 grados", user3, chat);
		messagesService.addMessage(message);
		message = new Message("Ah, pues me viene prefecto", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Genial", user3, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user5);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Balón", "Balón de baloncesto", 10.0, user3,false);
		offersService.addOffer(offer);
		chat = new Chat(user3, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, viene inflado?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Ahora mismo no lo está, pero puedo hacerlo...", user3, chat);
		messagesService.addMessage(message);
		message = new Message("Es que no tengo máquina y mi hijo quiere jugar ya", user5, chat);
		messagesService.addMessage(message);
		message = new Message("No sería un problema :)", user3, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user5);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		
		// Ofertas user4
		offer = new Offer("Pato", "Pato de goma", 3.0, user4,true);
		offersService.addOffer(offer);
		chat = new Chat(user4, user2, offer);
		chatsService.addChat(chat);
		message = new Message("Hola!", user2, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Hola! Dígame", user4, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("El pato es amarillo?", user2, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Sí", user4, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user2);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Vestido", "Vestido azul", 20.0, user4,false);
		offersService.addOffer(offer);
		chat = new Chat(user4, user3, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, me interesa el vestido", user3, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Y qué necesitas saber?", user4, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Qué tono tiene el vestido?", user3, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Marino", user4, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Tienda", "Tienda de campaña", 80.0, user4,false);
		offersService.addOffer(offer);
		chat = new Chat(user4, user6, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, qué tamaño tiene?", user6, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Es de 4 x 5", user4, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Ah, así que es espaciosa", user6, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Exactamente, y de muy buena calidad", user4, chat);
		messagesService.addMessage(message);
		
		
		// Ofertas user5
		offer = new Offer("Caja", "Caja de cartón azul", 5.0, user5,false);
		offersService.addOffer(offer);
		chat = new Chat(user5, user4, offer);
		chatsService.addChat(chat);
		message = new Message("Buenos días, cuál es la dimensión de la caja?", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, mide 30 x 30 x 30 cm", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Ah, vaya, es más grande de lo que me esperaba, no me interesa, disculpe", user4, chat);
		messagesService.addMessage(message);
		message = new Message("No se preocupe", user5, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user6);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Bicicleta", "Bicicleta infantil", 70.0, user5,false);
		offersService.addOffer(offer);
		chat = new Chat(user5, user3, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, tengo cierto interés en la bici", user3, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, tienes alguna duda?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Parece que le falta la pintura en algunos sitios", user3, chat);
		messagesService.addMessage(message);
		message = new Message("Una poquita sí, pero por lo demás está como nueva", user5, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user3);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Folios", "Paquete de 500 folios", 4.0, user5,false);
		offersService.addOffer(offer);
		chat = new Chat(user5, user4, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, el paquete está completo?", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, le faltan algunos, pero muy pocos", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Cuántos son muy pocos?", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Entorno a 20...", user5, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user4);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		
		// Ofertas user6
		offer = new Offer("Zapatos", "Zapatos de baile", 11.0, user6,false);
		offersService.addOffer(offer);
		chat = new Chat(user6, user2, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, estoy interesado", user2, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, cuál es tu duda?", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Los zapatos parecen arañados", user2, chat);
		messagesService.addMessage(message);
		message = new Message("Para nada, es su impresión, están en perfecto estado", user6, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user2);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Sandalias", "Sandalias de plataforma", 15.0, user6,false);
		offersService.addOffer(offer);
		chat = new Chat(user6, user4, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, cuánto tacón tienen?", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, son unos 9 cm", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Uf, son muy altos", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Pero muy cómodos, te los recomiendo ;)", user6, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user4);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Boli", "Boli azul", 2.0, user6,false);
		offersService.addOffer(offer);
		chat = new Chat(user6, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, en la foto parece que no tiene tinta", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, sí que tiene, lo que pasa es que es de tinta líquida", user6, chat);
		messagesService.addMessage(message);
		message = new Message("No sé, no me fío, lo siento", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Pues entonces para qué me escribes?", user6, chat);
		messagesService.addMessage(message);
		
	}

}
