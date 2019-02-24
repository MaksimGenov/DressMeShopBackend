package com.genov.dress_me_shop.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = 7776286813676988840L;

	public CustomAuthenticationException(String msg) {
		super(msg);
	}

}
