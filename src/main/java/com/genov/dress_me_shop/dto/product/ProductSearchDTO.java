package com.genov.dress_me_shop.dto.product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProductSearchDTO {
	private String model;
	private String brand;
	private Integer page;
	private Integer pageSize;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private Set<String> categories;
	private Set<String> sizes;

	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public Set<String> getSizes() {
		return sizes;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setCategories(Set<String> categories) {
		this.categories = new HashSet<>();
		
		if  (categories == null || categories.isEmpty())
			this.categories.add(null);
		else
			this.categories.addAll(categories);
	}

	public void setSizes(Set<String> sizes) {
		this.sizes = new HashSet<>();
		
		if (sizes == null || sizes.isEmpty())
			this.sizes.add(null);
		else
			this.sizes.addAll(sizes);
	}

}
