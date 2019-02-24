package com.genov.dress_me_shop.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.genov.dress_me_shop.domain.AppUser;
import com.genov.dress_me_shop.exceptions.CustomAuthenticationException;

@Component
public class JwtUtilImpl implements JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expirationTime}")
	private Long expirationTime;
	
	@Override
	public String generate(AppUser user) {
		String[] roles = user.getRoles().stream()
				.map(r -> r.getName())
				.toArray(String[]::new);
		return JWT.create()
				.withSubject(user.getUsername())
				.withArrayClaim("roles", roles)
				.withExpiresAt(getExpirationDate())
				.sign(Algorithm.HMAC512(this.secret));
	}

	@Override
	public DecodedJWT decode(String token) throws JWTDecodeException {
		return JWT.decode(token);
	}

	@Override
	public boolean verify(String token) throws Exception {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC512(this.secret)).build();
			verifier.verify(token);
			return true;
		} catch (TokenExpiredException tee) {
			throw new CustomAuthenticationException("Token expired!");
		} catch (Exception e) {
			throw new CustomAuthenticationException("Invalid token!");
		}
	}

	@Override
	public String getUsername(String token) {
		DecodedJWT jwt = this.decode(token);
		return jwt.getSubject();
	}

	private Date getExpirationDate() {
		return new Date(new Date().getTime() + this.expirationTime);
	}
}
