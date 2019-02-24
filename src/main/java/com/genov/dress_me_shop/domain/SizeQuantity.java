package com.genov.dress_me_shop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "size_quantity")
public class SizeQuantity extends BaseEntity {
	private Size size;
	private Integer quantity;
	private Product product;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "size_id", referencedColumnName = "id")
	public Size getSize() {
		return size;
	}

	@Column(nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	public Product getProduct() {
		return product;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
