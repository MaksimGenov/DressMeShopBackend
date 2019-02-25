package com.genov.dress_me_shop.dto.product;

import java.math.BigDecimal;
import java.util.List;

import com.genov.dress_me_shop.dto.brand.BrandDTO;
import com.genov.dress_me_shop.dto.file.ImageDTO;

public class ProductDTO {
	private Long id;
	private String model;
	private String description;
	private BigDecimal price;
	private BrandDTO brand;
	private List<ImageDTO> images;

	public Long getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BrandDTO getBrand() {
		return brand;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

}
