package com.genov.dress_me_shop.dto.size;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SizeQuantityCreateDTO {
	private String size;
	private Integer quantity;

	@NotNull
	public String getSize() {
		return size;
	}

	@NotNull
	@Min(1)
	public Integer getQuantity() {
		return quantity;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
