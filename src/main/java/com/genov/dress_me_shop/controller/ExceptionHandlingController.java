package com.genov.dress_me_shop.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.genov.dress_me_shop.dto.ApiError;
import com.genov.dress_me_shop.exceptions.UsernameTakenException;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiError invalidData(HttpServletRequest req, ConstraintViolationException ex) {
		ApiError error = new ApiError();
		String message = String.join(System.lineSeparator(), ex.getConstraintViolations()
		.stream().map(v -> v.getPropertyPath() + " - " + v.getMessage()).collect(Collectors.toSet()));
		error.setStatus(400);
		error.setMessage(message);
		error.setError(ex.getMessage());
		return error;
	}
	
	@ExceptionHandler(value = AuthenticationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiError invalidCredentials(HttpServletRequest req, AuthenticationException ex) {
		ApiError error = new ApiError();
		error.setStatus(400);
		error.setMessage(ex.getMessage());
		error.setError(ex.getMessage());
		return error;
	}
	
	@ExceptionHandler(value = {UsernameTakenException.class, UsernameNotFoundException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiError handleUsernameExceptions(HttpServletRequest req, Exception ex) {
		ApiError error = new ApiError();
		error.setStatus(400);
		error.setMessage(ex.getMessage());
		error.setError(ex.getMessage());
		return error;
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiError invalidData1(HttpServletRequest req, Exception ex) {
		ex.printStackTrace();
		ApiError error = new ApiError();
		error.setStatus(500);
		error.setError(ex.getMessage());
		error.setMessage("Internal server error.");
		return error;
	}
}
