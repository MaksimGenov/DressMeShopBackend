package com.genov.dress_me_shop.dto.brand;

import com.genov.dress_me_shop.dto.file.ImageDTO;

public class BrandDetailsDTO {
	private Long id;
	private String name;
	private String description;
	private ImageDTO image;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

}
