package com.genov.dress_me_shop.dto.user;

import javax.validation.constraints.Pattern;

import com.genov.dress_me_shop.annotations.Email;
import com.genov.dress_me_shop.annotations.Password;

public class RegisterRequest {
	private String username;
	private String password;
	private String confirmedPassword;
	private String email;
	private Integer age;

	@Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", 
			message = "Username must be with length from 3 to 20 and contain only alphanumeric characters.")
	public String getUsername() {
		return username;
	}

	@Password(conatinsDigit = true, containsLowercase = true, containsUppercase = true,minLength = 6, maxLength = 20,
			message = "Password must be between 6 and 20 symbols long, contain digit and lowercase!")
	public String getPassword() {
		return password;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	@Email(message = "Invalid email.")
	public String getEmail() {
		return email;
	}

	//@Min(1)
	public Integer getAge() {
		return age;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
