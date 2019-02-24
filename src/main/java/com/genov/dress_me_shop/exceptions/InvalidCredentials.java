package com.genov.dress_me_shop.exceptions;

public class InvalidCredentials extends RuntimeException {

	private static final long serialVersionUID = -208059157390148433L;

	public InvalidCredentials(String message) {
		super(message);
	}
}
