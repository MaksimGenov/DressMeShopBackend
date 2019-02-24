package com.genov.dress_me_shop.dto.order;

import com.genov.dress_me_shop.dto.product.ProductDetailsDTO;
import com.genov.dress_me_shop.dto.size.SizeDTO;

public class OrderItemDTO {
	private Long id;
	private ProductDetailsDTO product;
	private SizeDTO size;
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public ProductDetailsDTO getProduct() {
		return product;
	}

	public SizeDTO getSize() {
		return size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduct(ProductDetailsDTO product) {
		this.product = product;
	}

	public void setSize(SizeDTO size) {
		this.size = size;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
