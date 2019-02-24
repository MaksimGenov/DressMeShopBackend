package com.genov.dress_me_shop.utils.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.genov.dress_me_shop.annotations.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	private int minLength;
	private int maxLength;
	private boolean conatinsDigit;
	private boolean containsUppercase;
	private boolean containsLowercase;
	private boolean containsSpecialSymbol;
	private String pattern;

	@Override
	public void initialize(Password passwordAnot) {
		this.minLength = passwordAnot.minLength();
		this.maxLength = passwordAnot.maxLength();
		this.conatinsDigit = passwordAnot.conatinsDigit();
		this.containsUppercase = passwordAnot.containsUppercase();
		this.containsLowercase = passwordAnot.containsLowercase();
		this.containsSpecialSymbol = passwordAnot.containsSpecialSymbol();
		this.pattern = this.createPattern();
	}

	private String createPattern() {
		StringBuilder pattern = new StringBuilder();
		StringBuilder allowedSymbols = new StringBuilder();
		allowedSymbols.append("[");
		pattern.append("^");
		if (this.containsLowercase) {
			pattern.append("(?=.*[a-z])");
			allowedSymbols.append("a-z");
		}
		if (this.containsUppercase) {
			pattern.append("(?=.*[A-Z])");
			allowedSymbols.append("A-Z");
		}
		if (this.conatinsDigit) {
			pattern.append("(?=.*\\d)");
			allowedSymbols.append("\\d");
		}
		if (this.containsSpecialSymbol) {
			pattern.append("(?=.*[@$!%*?&])");
			allowedSymbols.append("@$!%*?&");
		}

		allowedSymbols.append("]");
		pattern.append(allowedSymbols);
		pattern.append(String.format("{%d,%d}$", this.minLength, this.maxLength));

		return pattern.toString();
		// "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$"
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Pattern.matches(this.pattern, value);
	}

}
