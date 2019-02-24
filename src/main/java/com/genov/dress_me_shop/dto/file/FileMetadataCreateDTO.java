package com.genov.dress_me_shop.dto.file;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class FileMetadataCreateDTO {
	private String name;
	private String path;
	private String url;
	private Long size;

	@NotNull
	public String getName() {
		return name;
	}

	@NotNull
	@JsonGetter("path")
	public String getPath() {
		return path;
	}

	@NotNull
	public Long getSize() {
		return size;
	}

	@NotNull
	public String getUrl() {
		return url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@JsonSetter("path_lower")
	public void setPath(String path) {
		this.path = path;
	}

	public void setUrl(String url) {
		this.url = url.replace("dl=0", "raw=1");
	}
}
