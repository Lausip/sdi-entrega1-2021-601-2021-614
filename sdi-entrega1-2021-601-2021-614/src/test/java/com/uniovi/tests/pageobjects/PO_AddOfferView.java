package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AddOfferView extends PO_View {

	static public void fillForm(WebDriver driver, String titlep, String descriptionp, String pricep,boolean highlight) {
		WebElement title = driver.findElement(By.name("titulo"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		WebElement description = driver.findElement(By.name("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		WebElement price = driver.findElement(By.name("price"));
		price.click();
		price.clear();
		price.sendKeys(pricep);
		if(highlight) {
		WebElement h = driver.findElement(By.name("highlight"));
		h.click();
		}
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}


}