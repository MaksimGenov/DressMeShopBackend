package com.genov.dress_me_shop.dto.size;

import javax.validation.constraints.NotNull;

public class SizeCreateDTO {
	private String name;

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
