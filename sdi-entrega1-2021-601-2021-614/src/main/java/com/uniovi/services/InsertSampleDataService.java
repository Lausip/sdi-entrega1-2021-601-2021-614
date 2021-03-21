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
		user3.setMoney(100.0);
		usersService.addUser(user3);
		
		User user4 = new User("juana@email.com", "Juana", "Fernández");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		user4.setMoney(20.0);
		usersService.addUser(user4);
		
		User user5 = new User("alberto@email.com", "Alberto", "Rodríguez Álvarez");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		user5.setMoney(0.0);
		usersService.addUser(user5);
		
		User user6 = new User("susana@email.com", "Susana", "González Almonte");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		user6.setMoney(80.0);
		usersService.addUser(user6);
		
		User user7 = new User("javier@email.com", "Javier", "de la Fuente");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user7);
		
		User user8 = new User("fernando@email.com", "Fernando Manuel", "García del Monte");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);
		user8.setMoney(10.0);
		usersService.addUser(user8);
		
		User user9 = new User("pepita@email.com", "Pepita", "Alonso de la Torre");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user9);
		
		
		// Añadir ofertas
		
		// Ofertas user3
		Offer offer = new Offer("Juguete", "Juguete infantil", 10.0, user3,false);
		offersService.addOffer(offer);
		Chat chat = new Chat(user3, user5, offer);
		chatsService.addChat(chat);
		Message message = new Message("Hola, soy Alberto, el precio es negociable?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, cuánto me ofreces?", user3, chat);
		messagesService.addMessage(message);
		message = new Message("8€, qué te parece?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Mucho mejor, creo que me lo voy a quedar, me lo pensaré", user3, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Patinete", "Patinete eléctrico", 280.0, user3,false);
		offersService.addOffer(offer);
		chat = new Chat(user3, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, soy Alberto, cuánta potencia tiene el patinete?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Buenas tardes, no recuerdo cuánta es, pero va muy bien", user3, chat);
		messagesService.addMessage(message);
		message = new Message("No sé...", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Sube muy bien las cuestas incluso", user3, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user4);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Rotuladores", "Rotuladores de colores", 12.0, user3,false);
		offersService.addOffer(offer);
		chat = new Chat(user3, user8, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, cuántos rotuladores vienen?", user8, chat);
		messagesService.addMessage(message);
		message = new Message("Buenas, vienen 12", user3, chat);
		messagesService.addMessage(message);
		message = new Message("No parecen muy buenos, son de marca?", user8, chat);
		messagesService.addMessage(message);
		message = new Message("Son de marca blanca, pero pintan muy bien", user3, chat);
		messagesService.addMessage(message);
		
		
		// Ofertas user4
		offer = new Offer("Raqueta", "Raqueta de tenis", 20.0, user4,false);
		offersService.addOffer(offer);
		chat = new Chat(user4, user8, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, de qué tipo es la raqueta?", user8, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, es profesional", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Ah, pues entonces no me interesa, estoy empezando", user8, chat);
		messagesService.addMessage(message);
		message = new Message("Sin problema", user4, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user8);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Saco", "Saco de dormir", 11.0, user4,false);
		offersService.addOffer(offer);
		chat = new Chat(user4, user6, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, me interesa el saco, pero me preocupa que no sea lo suficientemente cálido", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Buenas tardes, es para 12 grados", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Ah, pues me viene prefecto", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Genial", user4, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user6);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Balón", "Balón de baloncesto", 10.0, user4,false);
		offersService.addOffer(offer);
		chat = new Chat(user4, user6, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, viene inflado?", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Ahora mismo no lo está, pero puedo hacerlo...", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Es que no tengo máquina y mi hijo quiere jugar ya", user6, chat);
		messagesService.addMessage(message);
		message = new Message("No sería un problema :)", user4, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user6);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		
		// Ofertas user5
		offer = new Offer("Pato", "Pato de goma", 3.0, user5,true);
		offersService.addOffer(offer);
		chat = new Chat(user5, user3, offer);
		chatsService.addChat(chat);
		message = new Message("Hola!", user3, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Hola! Dígame", user5, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("El pato es amarillo?", user3, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Sí", user5, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user3);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Vestido", "Vestido azul", 20.0, user5,false);
		offersService.addOffer(offer);
		chat = new Chat(user5, user4, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, me interesa el vestido", user4, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Y qué necesitas saber?", user5, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Qué tono tiene el vestido?", user4, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Marino", user5, chat);
		messagesService.addMessage(message);
		
		offer = new Offer("Tienda", "Tienda de campaña", 80.0, user5,false);
		offersService.addOffer(offer);
		chat = new Chat(user5, user8, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, qué tamaño tiene?", user8, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Es de 4 x 5", user5, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Ah, así que es espaciosa", user8, chat);
		messagesService.addMessage(message);
		chatsService.addChat(chat);
		message = new Message("Exactamente, y de muy buena calidad", user5, chat);
		messagesService.addMessage(message);
		
		
		// Ofertas user 6
		offer = new Offer("Caja", "Caja de cartón azul", 5.0, user6,false);
		offersService.addOffer(offer);
		chat = new Chat(user6, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Buenos días, cuál es la dimensión de la caja?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, mide 30 x 30 x 30 cm", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Ah, vaya, es más grande de lo que me esperaba, no me interesa, disculpe", user5, chat);
		messagesService.addMessage(message);
		message = new Message("No se preocupe", user6, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user8);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Bicicleta", "Bicicleta infantil", 70.0, user6,false);
		offersService.addOffer(offer);
		chat = new Chat(user6, user4, offer);
		chatsService.addChat(chat);
		message = new Message("Buenas tardes, tengo cierto interés en la bici", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, tienes alguna duda?", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Parece que le falta la pintura en algunos sitios", user4, chat);
		messagesService.addMessage(message);
		message = new Message("Una poquita sí, pero por lo demás está como nueva", user6, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user4);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Folios", "Paquete de 500 folios", 4.0, user6,false);
		offersService.addOffer(offer);
		chat = new Chat(user6, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, el paquete está completo?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, le faltan algunos, pero muy pocos", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Cuántos son muy pocos?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Entorno a 20...", user6, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user5);
		offer.setPurchased(true);
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
		message = new Message("Los zapatos parecen arañados", user3, chat);
		messagesService.addMessage(message);
		message = new Message("Para nada, es su impresión, están en perfecto estado", user8, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user3);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Sandalias", "Sandalias de plataforma", 15.0, user8,false);
		offersService.addOffer(offer);
		chat = new Chat(user8, user5, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, cuánto tacón tienen?", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, son unos 9 cm", user8, chat);
		messagesService.addMessage(message);
		message = new Message("Uf, son muy altos", user5, chat);
		messagesService.addMessage(message);
		message = new Message("Pero muy cómodos, te los recomiendo ;)", user8, chat);
		messagesService.addMessage(message);
		offer.setPurchaser(user5);
		offer.setPurchased(true);
		offersService.addOffer(offer);
		
		offer = new Offer("Boli", "Boli azul", 2.0, user8,false);
		offersService.addOffer(offer);
		chat = new Chat(user8, user6, offer);
		chatsService.addChat(chat);
		message = new Message("Hola, en la foto parece que no tiene tinta", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Hola, sí que tiene, lo que pasa es que es de tinta líquida", user8, chat);
		messagesService.addMessage(message);
		message = new Message("No sé, no me fío, lo siento", user6, chat);
		messagesService.addMessage(message);
		message = new Message("Pues entonces para qué me escribes?", user8, chat);
		messagesService.addMessage(message);
		
	}

}
