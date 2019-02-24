package com.genov.dress_me_shop.dto.order;

public class OrderItemCreateDTO {
	private Long productId;
	private String size;
	private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public String getSize() {
		return size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
