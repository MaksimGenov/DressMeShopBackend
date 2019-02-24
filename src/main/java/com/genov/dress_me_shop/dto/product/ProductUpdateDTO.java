package com.genov.dress_me_shop.dto.product;

import javax.validation.constraints.NotNull;

public class ProductUpdateDTO extends ProductCreateDTO {
	private Long id;

	@NotNull
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
