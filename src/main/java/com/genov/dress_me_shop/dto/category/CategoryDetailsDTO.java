package com.genov.dress_me_shop.dto.category;

import com.genov.dress_me_shop.dto.file.ImageDTO;

public class CategoryDetailsDTO {
	private Long id;
	private String name;
	private ImageDTO image;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

}
