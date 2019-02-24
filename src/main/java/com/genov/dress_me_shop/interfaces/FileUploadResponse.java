package com.genov.dress_me_shop.interfaces;

public interface FileUploadResponse {
	String getName();

	Long getSize();

	String getPath();

	String getUrl();

	void setName(String name);

	void setSize(Long size);

	void setPath(String path);

	void setUrl(String url);
}
