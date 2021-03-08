package com.uniovi.validators;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Component
public class AddOfferFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Offer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");
		
		if (offer.getDescription().length() < 15) {
			errors.rejectValue("titulo", "Error.offer.add.titulo.length");
		}
		if (offer.getDescription().length() < 5 || offer.getDescription().length() > 24) {
			errors.rejectValue("description", "Error.offer.add.description.length");
		}
		if (offer.getPrice() < 0)
			errors.rejectValue("price", "Error.offer.add.price");
	}

}
