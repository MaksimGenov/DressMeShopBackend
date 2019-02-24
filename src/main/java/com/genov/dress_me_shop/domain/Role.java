package com.genov.dress_me_shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "roles")
public class Role extends BaseEntity {
	private String name;

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
