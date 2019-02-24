package com.genov.dress_me_shop.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "brands")
public class Brand extends BaseEntity {
	private String name;
	private String description;
	private Image image;
	private Set<Product> products;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	@Column(length = 1000)
	public String getDescription() {
		return description;
	}

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	public Image getImage() {
		return image;
	}

	@OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	public Set<Product> getProducts() {
		return products;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
