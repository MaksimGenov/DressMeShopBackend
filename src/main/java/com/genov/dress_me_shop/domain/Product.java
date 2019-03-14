package com.genov.dress_me_shop.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "products")
public class Product extends BaseEntity {
	private String model;
	private String description;
	private BigDecimal price;
	private Date creationDate;
	private Brand brand;
	private Set<Category> categories;
	private List<SizeQuantity> sizes;
	private Set<Image> images;

	@Column(nullable = false)
	public String getModel() {
		return model;
	}

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	@Column(nullable = false)
	public BigDecimal getPrice() {
		return price;
	}
	
	@Column(name = "creation_date", nullable = false)
	public Date getCreationDate() {
		return creationDate;
	}

	@ManyToOne
	@JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
	public Brand getBrand() {
		return brand;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "products_categories",
		joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	public Set<Category> getCategories() {
		return categories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
	public List<SizeQuantity> getSizes() {
		return sizes;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "products_images", 
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
	public Set<Image> getImages() {
		return images;
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

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setSizes(List<SizeQuantity> sizes) {
		this.sizes = sizes;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public void addImage(Image image) {
		this.getImages().add(image);
	}
	
	public void removeImage(Image image ) {
		this.getImages().remove(image);
	}

	public void addSize(SizeQuantity size) {
		this.sizes.add(size);
	}
	
	public void removeSize(SizeQuantity size) {
		this.sizes.remove(size);
	}

}
