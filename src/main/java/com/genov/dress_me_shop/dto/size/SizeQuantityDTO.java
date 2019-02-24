package com.genov.dress_me_shop.dto.size;

public class SizeQuantityDTO {
	private SizeDTO size;
	private Integer quantity;

	public SizeDTO getSize() {
		return size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setSize(SizeDTO size) {
		this.size = size;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
