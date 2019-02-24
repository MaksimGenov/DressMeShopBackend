package com.genov.dress_me_shop.dto;

public class ApiError {

	private Integer status;
	private String message;
	private String error;

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setError(String error) {
		this.error = error;
	}

}
