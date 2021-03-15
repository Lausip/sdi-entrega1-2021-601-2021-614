package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;

@Component
public class PurchaseOfferValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Offer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		
		if (offer.getUser().getId() == offer.getPurchaser().getId()) {
			errors.rejectValue("comprar", "Error.offer.pruchase.user");
		}
		if (offer.getPrice() > offer.getPurchaser().getMoney()) {
			errors.rejectValue("comprar", "Error.offer.pruchase.money");
		}
	}

}
