package com.genov.dress_me_shop.dto.product;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.genov.dress_me_shop.dto.size.SizeQuantityCreateDTO;

public class ProductCreateDTO {
	private String model;
	private String description;
	private BigDecimal price;
	private String brand;
	private String[] categories;
	private List<SizeQuantityCreateDTO> sizes;
	private String color;

	public String getColor() {
		return color;
	}

	@NotNull
	@Size(min = 2, max = 40)
	public String getModel() {
		return model;
	}

	@NotNull
	@Size(max = 1000)
	public String getDescription() {
		return description;
	}

	@NotNull
	@Digits(integer = 20, fraction = 2)
	@Min(0)
	public BigDecimal getPrice() {
		return price;
	}

	@NotNull
	@Size(min = 2, max = 40)
	public String getBrand() {
		return brand;
	}

	@NotNull
	@Size(min = 1)
	public String[] getCategories() {
		return categories;
	}

	public List<SizeQuantityCreateDTO> getSizes() {
		return sizes;
	}

	public void setModel(String model) {
		this.model = model.trim().toLowerCase();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setBrand(String brand) {
		this.brand = brand.trim().toLowerCase();
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public void setSizes(List<SizeQuantityCreateDTO> sizes) {
		this.sizes = sizes;
	}

}
