package com.genov.dress_me_shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "images")
public class Image extends BaseEntity {
	private String name;
	private String url;
	private String path;
	private Long size;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	@Column(nullable = false)
	public String getUrl() {
		return url;
	}

	@Column(nullable = false)
	public String getPath() {
		return path;
	}

	@Column(nullable = false)
	public Long getSize() {
		return size;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
