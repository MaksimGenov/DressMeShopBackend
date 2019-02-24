package com.genov.dress_me_shop.exceptions;

public class UsernameTakenException extends RuntimeException {
	private static final long serialVersionUID = -3775915831528110085L;

	public UsernameTakenException(String message) {
		super(message);
	}

}
