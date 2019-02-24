package com.genov.dress_me_shop.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

@MappedSuperclass
public class BaseAppFile {
	private Long appId;
	private String name;
	private String path;
	private Long size;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAppId() {
		return appId;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	@Column(nullable = false)
	@JsonGetter("path")
	public String getPath() {
		return path;
	}

	@Column(nullable = false)
	public Long getSize() {
		return size;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSetter("path_lower")
	public void setPath(String path) {
		this.path = path;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
