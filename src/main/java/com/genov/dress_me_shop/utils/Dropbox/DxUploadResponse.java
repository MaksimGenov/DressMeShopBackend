package com.genov.dress_me_shop.utils.Dropbox;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.genov.dress_me_shop.interfaces.FileUploadResponse;

public class DxUploadResponse implements FileUploadResponse {
	private String name;
	private String path;
	private String url;
	private Long size;

	public String getName() {
		return name;
	}

	@JsonGetter("path")
	public String getPath() {
		return path;
	}

	public Long getSize() {
		return size;
	}

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
