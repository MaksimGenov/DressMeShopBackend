package com.genov.dress_me_shop.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.genov.dress_me_shop.exceptions.CustomAuthenticationException;
import com.genov.dress_me_shop.utils.JwtUtil;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	private JwtUtil jwtUtil;
	
	public JwtAuthorizationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = this.getJwtToken(req);
			
			this.jwtUtil.verify(jwt);

			UsernamePasswordAuthenticationToken authenticationToken = this.getAuthToken(jwt);
					
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		} catch (Exception e) {
			throw new CustomAuthenticationException(e.getMessage());
		}
		
		filterChain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthToken(String jwt) {
		DecodedJWT decodedJWT = this.jwtUtil.decode(jwt);
		String username = decodedJWT.getSubject();
		String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
		Set<GrantedAuthority> authorities = Arrays.stream(roles)
				.map(r -> new SimpleGrantedAuthority(r))
				.collect(Collectors.toSet());
		
		return new UsernamePasswordAuthenticationToken(username, null, authorities);
	}

	private String getJwtToken(HttpServletRequest request) throws RuntimeException {
		String bearerToken = request.getHeader("Authorization");
		
		if (bearerToken == null) 
			throw new RuntimeException("Missisng authorization token.");
		
		if (!bearerToken.startsWith("Bearer ")) 
			throw new RuntimeException("Invalid token type.");

		return bearerToken.substring(7, bearerToken.length());
	}
	
}
