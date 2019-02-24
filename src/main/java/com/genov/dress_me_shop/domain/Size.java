package com.genov.dress_me_shop.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity(name = "sizes")
public class Size extends BaseEntity {
	private String name;
	private Set<SizeQuantity> sizeQuantities;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "size")
	public Set<SizeQuantity> getSizeQuantities() {
		return sizeQuantities;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSizeQuantities(Set<SizeQuantity> sizeQuantities) {
		this.sizeQuantities = sizeQuantities;
	}

}
