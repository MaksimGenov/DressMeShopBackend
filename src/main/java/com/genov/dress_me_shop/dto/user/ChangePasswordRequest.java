package com.genov.dress_me_shop.dto.user;

public class ChangePasswordRequest {
	private String password;
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
