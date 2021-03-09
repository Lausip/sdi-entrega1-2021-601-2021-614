package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.uniovi.tests.util.SeleniumUtils;



public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	public static void login(WebDriver driver, String dni, String password) {
		// Vamos al formulario de logueo.
		clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, dni, password);
		// Cpmprobamos que entramos en la pagina privada
		checkElement(driver, "text", dni);
		SeleniumUtils.esperarSegundos(driver, 1);
	}
	public static void logout(WebDriver driver) {
		clickOption(driver, "logout", "text", "Identifícate");
	}
	
	public static void fillFormAddUser(WebDriver driver, String dniP, String nameP, String surnameP, String categoriaP) {
		SeleniumUtils.esperarSegundos(driver, 5);		
		WebElement dni = driver.findElement(By.name("dni"));
		dni.clear();
		dni.sendKeys(dniP);
		WebElement name = driver.findElement(By.name("nombre"));
		name.clear();
		name.sendKeys(nameP);
		WebElement surname = driver.findElement(By.name("apellido"));
		surname.clear();
		surname.sendKeys(surnameP);
		WebElement categoria = driver.findElement(By.name("categoria"));
		categoria.clear();
		categoria.sendKeys(categoriaP);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}




}
