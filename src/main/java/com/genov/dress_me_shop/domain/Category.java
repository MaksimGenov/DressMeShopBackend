package com.genov.dress_me_shop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "categories")
public class Category extends BaseEntity {
	private String name;
	private Image image;

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	public Image getImage() {
		return image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
