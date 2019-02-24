package com.genov.dress_me_shop.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class PasswordGeneratorImpl implements PasswordGenerator{
	private SecureRandom random = new SecureRandom();
	private final String LOWERCASE = "LOWERCASE";
	private final String UPPERCASE = "UPPERCASE";
	private final String NUMERIC = "NUMERIC";
	private final String SPECIAL_SYMBOL = "SPECIAL_SYMBOL";
	private final String[] CHAR_CATEGORIES = new String[] {LOWERCASE, UPPERCASE, NUMERIC, SPECIAL_SYMBOL};
	private final int LOWERCASE_START = 97;
	private final int LOWERCASE_END = 122;
	private final int UPPERCASE_START = 65;
	private final int UPPERCASE_END = 90;
	private final int NUMERICS_START = 48;
	private final int NUMERICS_END = 57;
	private final int SPECIAL_SYMBOLS_START = 33;
	private final int SPECIAL_SYMBOLS_END = 47;
	
	@Override
	public String generate(int length) {
		StringBuilder password = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			password.append(this.getRandomSymbol());
		}
		
		return password.toString();
	}

	private char getRandomSymbol() {
		String category = this.CHAR_CATEGORIES[this.random.nextInt(CHAR_CATEGORIES.length)];
		
		switch (category) {
		case LOWERCASE:
			return this.getRandomChar(LOWERCASE_START, LOWERCASE_END);
		case UPPERCASE:
			return this.getRandomChar(UPPERCASE_START, UPPERCASE_END);
		case NUMERIC:
			return this.getRandomChar(NUMERICS_START, NUMERICS_END);
		case SPECIAL_SYMBOL:
			return this.getRandomChar(SPECIAL_SYMBOLS_START, SPECIAL_SYMBOLS_END);
		default:
			throw new RuntimeException("Invalid char category.");
		}
	}

	private char getRandomChar(int start, int end) {
		int randomChar = this.random.nextInt(end - start + 1) + start;
		return (char) randomChar;
	}
}
