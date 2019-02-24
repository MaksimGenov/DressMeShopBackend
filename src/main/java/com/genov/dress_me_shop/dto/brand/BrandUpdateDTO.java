package com.genov.dress_me_shop.dto.brand;

import javax.validation.constraints.NotNull;

public class BrandUpdateDTO {
	private Long id;
	private String name;
	private String description;

	@NotNull
	public Long getId() {
		return id;
	}

	@NotNull
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
