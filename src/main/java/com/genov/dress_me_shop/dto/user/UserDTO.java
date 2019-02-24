package com.genov.dress_me_shop.dto.user;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

	private String username;
	private Set<String> roles;
	private String token;

	public UserDTO() {
		this.setRoles(new HashSet<>());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	public Boolean addRole(String role) {
		return this.getRoles().add(role);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
