package com.genov.dress_me_shop.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.genov.dress_me_shop.domain.AppUser;

public interface JwtUtil {
	/**
	 * Test
	 * @return test
	 */
	String generate(AppUser user);
	DecodedJWT decode(String token);
	boolean verify(String token) throws Exception;
	String getUsername(String token);
}
