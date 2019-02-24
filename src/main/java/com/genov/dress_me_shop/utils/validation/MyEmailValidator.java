package com.genov.dress_me_shop.utils.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.genov.dress_me_shop.annotations.Email;

public class MyEmailValidator implements ConstraintValidator<Email, String> {
	private final String EMAIL_REGEX = "^[a-zA-Z0-9][\\w.-]*@[a-zA-z-]+\\.[a-zA-Z-.]*[a-zA-Z]$";
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Pattern.matches(EMAIL_REGEX, value);
	}
}
