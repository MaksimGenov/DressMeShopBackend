package com.genov.dress_me_shop.dto.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import com.genov.dress_me_shop.dto.brand.BrandDTO;
import com.genov.dress_me_shop.dto.category.CategoryDetailsDTO;
import com.genov.dress_me_shop.dto.file.ImageDTO;
import com.genov.dress_me_shop.dto.size.SizeQuantityDTO;

public class ProductDetailsDTO {
	private Long id;
	private String model;
	private String description;
	private BigDecimal price;
	private BrandDTO brand;
	private Set<CategoryDetailsDTO> categories;
	private Set<ImageDTO> images;
	private List<SizeQuantityDTO> sizes;

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

	public Set<CategoryDetailsDTO> getCategories() {
		return categories;
	}

	public Set<ImageDTO> getImages() {
		return images;
	}

	public List<SizeQuantityDTO> getSizes() {
		return sizes;
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

	public void setCategories(Set<CategoryDetailsDTO> categories) {
		this.categories = categories;
	}

	public void setImages(Set<ImageDTO> images) {
		this.images = images;
	}

	public void setSizes(List<SizeQuantityDTO> sizes) {
		this.sizes = sizes;
	}

}
