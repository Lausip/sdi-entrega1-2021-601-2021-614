package com.uniovi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AddOfferView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WallapopTest {

	// Parámetros de Laura
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\laura\\Escritorio\\Uni\\3-Uni\\2Semestre\\SDI\\LAB\\Sesion05\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	
	// Parámetros de Rut
	//static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	//static String Geckdriver024 = "C:\\Users\\rualg\\OneDrive\\Escritorio\\SDI\\Práctica5\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	} /* Resto del código de la clase */

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before()
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
	
	// Registro de usuario con datos válidos.
	@Test
	public void PR01() {
		
		//Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "prueba1@example.com", "Juan", "Pérez", "123456", "123456");
		
		//Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "prueba1@example.com");
		
	}
	
	// Registro de usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
	@Test
	public void PR02() {
		
		//Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		
		//Comprobamos el error de email vacío.
		PO_RegisterView.checkKey(driver, "Error.empty.email", PO_Properties.getSPANISH());
		
		//Comprobamos el error de nombre vacío.
		PO_RegisterView.checkKey(driver, "Error.empty.name", PO_Properties.getSPANISH());
		
		//Comprobamos el error de apellidos vacío.
		PO_RegisterView.checkKey(driver, "Error.empty.lastName", PO_Properties.getSPANISH());
		
	}
	
	// Registro de usuario con datos inválidos (repetición de contraseña inválida).
	@Test
	public void PR03() {
		
		//Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "prueba3@example.com", "Pepe", "Álvarez", "123456", "123457");
		
		//Comprobamos el error de repetición de contraseña inválida.
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
		
	}
	
	// Registro de usuario con datos inválidos (email existente).
	@Test
	public void PR04() {
		
		//Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "prueba1@example.com", "Francisco", "López", "123456", "123456");
		
		//Comprobamos el error de repetición de contraseña inválida.
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
		
	}

	// Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR05() {
		PO_PrivateView.login(driver, "admin@email.com", "admin");
		SeleniumUtils.textoPresentePagina(driver, "Usuario autenticado como");
	}

	// Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR06() {
		PO_PrivateView.login(driver, "alberto@email.com", "123456");
		SeleniumUtils.textoPresentePagina(driver, "Su saldo es de");
		
	}

	// Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos)
	@Test
	public void PR07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "123456");
		SeleniumUtils.textoPresentePagina(driver, "Identifícate");
		PO_LoginView.fillForm(driver, "Jose@gmail.com", " ");
		SeleniumUtils.textoPresentePagina(driver, "Identifícate");
		PO_LoginView.fillForm(driver, " ", " ");
		SeleniumUtils.textoPresentePagina(driver, "Identifícate");
	}

	// Inicio de sesión con datos válidos (usuario estándar, email existente, pero
	// contraseña incorrecta).
	@Test
	public void PR08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "Jose@gmail.com", "123");
		PO_RegisterView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
	}

	// Inicio de sesión con datos inválidos (usuario estándar, email no existente en
	// la aplicación).
	@Test
	public void PR09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "Joselito@gmail.com", "123456");
		PO_RegisterView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
	}
	
	// Hacer click en la opción de salir de sesión y comprobar que se redirige a la página de inicio
	// de sesión (Login).
	@Test
	public void PR10() {
		PO_PrivateView.login(driver, "Javi@gmail.com", "123456");
		PO_PrivateView.logout(driver);
		SeleniumUtils.textoPresentePagina(driver, "Identifícate");
	}
	// Comprobar que el botón cerrar sesión no está visible si el usuario no está
	// autenticado.
	@Test
	public void PR11() {
		PO_PrivateView.login(driver, "Javi@gmail.com", "123456");
		PO_PrivateView.logout(driver);
		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
	}
	
	// Mostrar el listado de usuarios y comprobar que se muestran todos los que
	// existen en el sistema.
	@Test
	public void PR12() {
		PO_PrivateView.login(driver, "admin@email.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", "/user/list", PO_View.getTimeout());
		elementos.get(0).click();
		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos2.size() ==10);

	}
	
	/* Ir a la lista de usuarios, borrar el primer usuario de la lista, comprobar
	 * que la lista se actualiza y que el usuario desaparece.
	 */
	@Test
	public void PR13() {
		
		// Iniciamos sesión como el usuario administrador.
		PO_PrivateView.login(driver, "admin@email.com", "admin");
		
		// Pinchamos en la opción de gestión de usuarios del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestre hasta el último usuario.
		elementos = PO_View.checkElement(driver, "text", "Alonso de la Torre");
		
		// Seleccionamos el checkbox del primero los usuarios de la lista.
		WebElement checkbox = driver.findElement(By.id("deleteUsersCheckbox2"));
		checkbox.click();
		
		// Hacemos click en el botón de Eliminar.
		WebElement button = driver.findElement(By.id("deleteButton"));
		button.click();
		
		// Esperamos a que vuelva a cargar la página.
		elementos = PO_View.checkElement(driver, "text", "Alonso de la Torre");
		
		// Comprobamos que el usuario ya no está en la lista.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Javi@gmail.com", PO_View.getTimeout());
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
	}
	
	/*
	 * Ir a la lista de usuarios, borrar el último usuario de la lista, comprobar
	 * que la lista se actualiza y que el usuario desaparece.
	 */
	@Test
	public void PR14() {
		
		// Iniciamos sesión como el usuario administrador.
		PO_PrivateView.login(driver, "admin@email.com", "admin");
		
		// Pinchamos en la opción de gestión de usuarios del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestre hasta el último usuario.
		elementos = PO_View.checkElement(driver, "text", "Alonso de la Torre");
		
		// Seleccionamos el checkbox del último los usuarios de la lista.
		WebElement checkbox = driver.findElement(By.id("deleteUsersCheckbox9"));
		checkbox.click();
		
		// Hacemos click en el botón de Eliminar.
		WebElement button = driver.findElement(By.id("deleteButton"));
		button.click();
		
		// Esperamos a que vuelva a cargar la página.
		elementos = PO_View.checkElement(driver, "text", "García del Monte");
		
		// Comprobamos que el usuario ya no está en la lista.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "pepita@email.com", PO_View.getTimeout());
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
	}
	
	/*
	 * Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se
	 * actualiza y que los usuarios desaparecen.
	 */
	@Test
	public void PR15() {
		
		// Iniciamos sesión como el usuario administrador.
		PO_PrivateView.login(driver, "admin@email.com", "admin");
		
		// Pinchamos en la opción de gestión de usuarios del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestre hasta el último usuario.
		elementos = PO_View.checkElement(driver, "text", "García del Monte");
		
		// Seleccionamos el checkbox de los usuarios de la lista con ids 4, 6 y 7.
		WebElement checkbox = driver.findElement(By.id("deleteUsersCheckbox4"));
		checkbox.click();
		
		checkbox = driver.findElement(By.id("deleteUsersCheckbox6"));
		checkbox.click();
		
		checkbox = driver.findElement(By.id("deleteUsersCheckbox7"));
		checkbox.click();
		
		// Hacemos click en el botón de Eliminar.
		WebElement button = driver.findElement(By.id("deleteButton"));
		button.click();
		
		// Esperamos a que vuelva a cargar la página.
		elementos = PO_View.checkElement(driver, "text", "García del Monte");
		
		// Comprobamos que los usuarios ya no está en la lista.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "juana@email.com", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "susana@email.com", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "javier@email.com", PO_View.getTimeout());
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
	}
	
	//Ir al formulario de alta de oferta, rellenarla con datos válidos y pulsar el botón Submit. 
	//Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void PR16() {
		PO_PrivateView.login(driver, "alberto@email.com", "123456");
		PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/add");
		PO_AddOfferView.fillForm(driver, "Hola", "Hola esto es una prueba", "15.0",false);
		PO_HomeView.checkElement(driver, "text", "Hola");
	}
	
	//Ir al formulario de alta de oferta, rellenarla con datos inválidos (campo título vacío) y pulsar 
	//el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio
	@Test
	public void PR17() {
		PO_PrivateView.login(driver, "alberto@email.com", "123456");
		PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/add");
		PO_AddOfferView.fillForm(driver, "", "Hola esto es una prueba", "15.0",false);
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		PO_AddOfferView.fillForm(driver, "Hola", " ", "46.0",false);
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		PO_AddOfferView.fillForm(driver, "Hola", "Hola esto es una prueba", " ",false);
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	//	Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran todas los que
	// existen para este usuario.
	@Test
	public void PR18() {
		PO_PrivateView.login(driver, "alberto@email.com", "123456");
		PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/mylist");
		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos2.size() ==5);
	
	}
	
	/* 
	 * Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar
	 * que la lista se actualiza y que la oferta desaparece.
	 */
	@Test
	public void PR19() {
		
		// Iniciamos sesión como el usuario pepe.
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		
		// Pinchamos en la opción de gestión de ofertas del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de ofertas del usuario.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/mylist')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestren los botones de paginación.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Vamos a la primera página.
		elementos.get(1).click();
		
		// Esperamos a que aparezca el Juguete y pinchamos en su enlace de borrado.
		elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Juguete')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();
		
		// Esperamos a que vuelva a cargar la página.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Vamos a la primera página.
		elementos.get(1).click();
		
		// Comprobamos que la oferta ya no está en la lista.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Juguete", PO_View.getTimeout());
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
	}
	
	/* 
	 * Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar
	 * que la lista se actualiza y que la oferta desaparece.
	 */
	@Test
	public void PR20() {
		
		// Iniciamos sesión como el usuario pepe.
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		
		// Pinchamos en la opción de gestión de ofertas del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de ofertas del usuario.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/mylist')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestren los botones de paginación.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Comprobamos que estamos en la segunda página.
		elementos.get(2).click();
		
		// Esperamos a que aparezca el Rotuladores y pinchamos en su enlace de borrado.
		elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Rotuladores')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();
		
		// Esperamos a que vuelva a cargar la página.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Comprobamos que estamos en la primera página.
		elementos.get(2).click();
		
		// Comprobamos que la oferta ya no está en la lista.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Rotuladores", PO_View.getTimeout());
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
	}
	
	//	Hacer una búsqueda con el campo vacío y comprobar que se muestra la página que
	//	corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void PR21() {
		PO_PrivateView.login(driver, "alberto@email.com", "123456");
		PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/list");
		WebElement buscar = driver.findElement(By.name("searchText"));
		buscar.click();
		buscar.clear();
		buscar.sendKeys("");
		buscar.click();
		List<WebElement> elementos;
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		driver.findElements(By.xpath("//a[contains(@class, 'page-link')]")).get(2).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 1);

	}
	//Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar que se 
	//muestra la página que corresponde, con la lista de ofertas vacía.
	@Test
	public void PR22() {
		PO_PrivateView.login(driver, "alberto@email.com", "123456");
		PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/list");
		WebElement buscar = driver.findElement(By.name("searchText"));
		buscar.click();
		buscar.clear();
		buscar.sendKeys("hello");
		driver.findElement(By.className("btn")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).size() == 0);

	}
	
	/*
	 * Sobre una búsqueda determinada (a elección del desarrollador), comprar una
	 * oferta que deja un saldo positivo en el contador del comprador. Comprobar
	 * que el contador se actualiza correctamente en la vista del comprador.
	 */
	@Test
	public void PR23() {
		
		// Iniciamos sesión como el usuario pepe.
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		
		// Pinchamos en la opción de gestión de ofertas del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/list')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestren los botones de paginación.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Buscamos el texto "vest" en el buscador.
		PO_PrivateView.searchOffer(driver, "vest");
		
		// Comprobamos que estamos en la primera página.
		elementos.get(1).click();
		
		// Esperamos a que aparezca el Vestido y pinchamos en su enlace de Comprar.
		elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Vestido')]/following-sibling::*/a[contains(@href, 'offer/purchase')]");
		elementos.get(0).click();
		
		// Esperamos a que cargue la página de ofertas compradas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Comprobamos que estamos en la primera página.
		elementos.get(1).click();
		
		// Comprobamos que la oferta ahora está en la lista de ofertas compradas.
		SeleniumUtils.textoPresentePagina(driver, "Vestido");
		SeleniumUtils.textoPresentePagina(driver, "Vestido azul");
		SeleniumUtils.textoPresentePagina(driver, "alberto@email.com");
		SeleniumUtils.textoPresentePagina(driver, "80.0");
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
		
	}
	
	/*
	 * Sobre una búsqueda determinada (a elección del desarrollador), comprar una
	 * oferta que deja un saldo 0 en el contador del comprador. Comprobar que el
	 * contador se actualiza correctamente en la vista del comprador.
	 */
	@Test
	public void PR24() {
		
		// Iniciamos sesión como el usuario pepe.
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		
		// Pinchamos en la opción de gestión de ofertas del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/list')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestren los botones de paginación.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Buscamos el texto "tiend" en el buscador.
		PO_PrivateView.searchOffer(driver, "tiend");
		
		// Comprobamos que estamos en la primera página.
		elementos.get(1).click();
		
		// Esperamos a que aparezca la Tienda y pinchamos en su enlace de Comprar.
		elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Tienda')]/following-sibling::*/a[contains(@href, 'offer/purchase')]");
		elementos.get(0).click();
		
		// Esperamos a que cargue la página de ofertas compradas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Comprobamos que estamos en la primera página.
		elementos.get(1).click();
		
		// Comprobamos que la oferta ahora está en la lista de ofertas compradas.
		SeleniumUtils.textoPresentePagina(driver, "Tienda");
		SeleniumUtils.textoPresentePagina(driver, "Tienda de campaña");
		SeleniumUtils.textoPresentePagina(driver, "alberto@email.com");
		SeleniumUtils.textoPresentePagina(driver, "0.0");
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
		
	}
	
	/*
	 * Sobre una búsqueda determinada (a elección del desarrollador), intentar
	 * comprar una oferta que esté por encima de saldo disponible del comprador.
	 * Y comprobar que se muestra el mensaje de saldo no suficiente.
	 */
	@Test
	public void PR25() {
		
		// Iniciamos sesión como el usuario pepe.
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		
		// Pinchamos en la opción de gestión de ofertas del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/list')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestren los botones de paginación.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Buscamos el texto "pat" en el buscador.
		PO_PrivateView.searchOffer(driver, "pat");
		
		// Comprobamos que estamos en la primera página.
		elementos.get(1).click();
		
		// Esperamos a que aparezca el Pato y pinchamos en su enlace de Comprar.
		elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Pato')]/following-sibling::*/a[contains(@href, 'offer/purchase')]");
		elementos.get(0).click();
		
		// Comprobamos que sale un mensaje de error dieciendo que el usuario no tiene saldo suficiente.
		SeleniumUtils.textoPresentePagina(driver, "La compra no se puede realizar, ya que no dispone del saldo necesario.");
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
		
	}
	
	/*
	 * Ir a la opción de ofertas compradas del usuario y mostrar la lista.
	 * Comprobar que aparecen las ofertas que deben aparecer.
	 */
	@Test
	public void PR26() {
		
		// Iniciamos sesión como el usuario pepe.
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		
		// Pinchamos en la opción de gestión de ofertas del menú.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de ofertas compradas por el usuario.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/purchasedlist')]");
		elementos.get(0).click();
		
		// Esperamos a que se muestre los botones de paginación.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		
		// Comprobamos que estamos en la primera página.
		elementos.get(1).click();
		
		// Comprobamos que las ofertas compradas están en la lista.
		SeleniumUtils.textoPresentePagina(driver, "Vestido");
		SeleniumUtils.textoPresentePagina(driver, "Vestido azul");
		SeleniumUtils.textoPresentePagina(driver, "Tienda");
		SeleniumUtils.textoPresentePagina(driver, "Tienda de campaña");
		SeleniumUtils.textoPresentePagina(driver, "alberto@email.com");
		
		// Finalmente, nos desconectamos.
		PO_PrivateView.logout(driver);
		
	}
	
	//	Intentar acceder sin estar autenticado a la opción de listado de usuarios del administrador. Se
	//	deberá volver al formulario de login
	@Test
	public void PR28() {
		driver.get(URL + "/user/list");
		PO_View.checkElement(driver, "text", "Identifícate");
	}
	
	//	Intentar acceder sin estar autenticado a la opción de listado de ofertas propias de un usuario
	//	estándar. Se deberá volver al formulario de login.
	@Test
	public void PR29() {
		driver.get(URL + "/offer/mylist");
		PO_View.checkElement(driver, "text", "Identifícate");
	}
	
	//	Estando autenticado como usuario estándar intentar acceder a la opción de listado de
	//	usuarios del administrador. Se deberá indicar un mensaje de acción prohibida.
	@Test
	public void PR30() {
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		driver.get(URL + "/user/list");
		SeleniumUtils.textoPresentePagina(driver, "HTTP Status 403 – Forbidden");
	}
	
	//Al crear una oferta marcar dicha oferta como destacada y a continuación comprobar: i) que
	//aparece en el listado de ofertas destacadas para los usuarios y que el saldo del usuario se actualiza
	//adecuadamente en la vista del ofertante (-20).
	@Test
	public void PR36() {
		PO_PrivateView.login(driver, "pepe@email.com", "123456");
		String money = driver.findElement(By.id("money")).getText();
		PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/add");
		PO_AddOfferView.fillForm(driver, "Hola", "Hola esto es una prueba", "15.0",true);
		String newMoney =  driver.findElement(By.id("money")).getText();
		assertEquals(Double.parseDouble(money) - 20, Double.parseDouble(newMoney), 0.1);
		driver.get(URL + "/home");
		PO_PrivateView.logout(driver);
		PO_PrivateView.login(driver, "fernando@email.com", "123456");
		PO_HomeView.checkElement(driver, "text", "Hola");
		}
	
		//Sobre el listado de ofertas de un usuario con mas de 20 euros de saldo, pinchar en el
		//enlace Destacada y a continuación comprobar: i) que aparece en el listado de ofertas destacadas para los
		//usuarios y que el saldo del usuario se actualiza adecuadamente en la vista del ofertante (-20).
		@Test
		public void PR37() {
			PO_PrivateView.login(driver, "pepe@email.com", "123456");
			String money = driver.findElement(By.id("money")).getText();
			driver.get(URL + "/home");
			PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/mylist");
			List<WebElement> hh = driver.findElements(By.id("highlightBtn"));
			hh.get(0).click();
			String newmoney = driver.findElement(By.id("money")).getText();
			assertEquals(Double.parseDouble(money) - 20,Double.parseDouble(newmoney), 0.1);
			driver.get(URL + "/home");
			PO_PrivateView.logout(driver);
			PO_PrivateView.login(driver, "fernando@email.com", "123456");
			PO_HomeView.checkElement(driver, "text", "Caja");
		}
		
		//Sobre el listado de ofertas de un usuario con menos de 20 euros de saldo, pinchar en el
		//enlace Destacada y a continuación comprobar que se muestra el mensaje de saldo no suficiente.
		@Test
		public void PR38() {
			PO_PrivateView.login(driver, "fernando@email.com", "123456");
			PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/add");
			PO_AddOfferView.fillForm(driver, "Hola", "Hola esto es una prueba", "15.0",false);
			driver.get(URL + "/home");
			PO_NavView.clickDropdownMenuOption(driver, "offers-dropdown", "offers-menu", "offer/mylist");
			List<WebElement> hh = driver.findElements(By.id("highlightBtn"));
			hh.get(0).click();
			PO_RegisterView.checkKey(driver, "Error.highlight.insuficient", PO_Properties.getSPANISH());
		}

}
