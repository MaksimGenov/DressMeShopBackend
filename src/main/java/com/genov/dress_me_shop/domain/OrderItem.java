package com.genov.dress_me_shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "order_items")
public class OrderItem extends BaseEntity {
	private Product product;
	private Size size;
	private AppUser user;
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
	public Product getProduct() {
		return product;
	}

	@ManyToOne
	@JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
	public Size getSize() {
		return size;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	public AppUser getUser() {
		return user;
	}

	@Column(nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

}
