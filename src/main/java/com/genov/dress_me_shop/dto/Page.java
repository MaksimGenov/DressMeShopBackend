package com.genov.dress_me_shop.dto;

import java.util.List;

public class Page<T> {
	private List<T> content;
	private Integer page;
	private Integer totalPages;

	public List<T> getContent() {
		return content;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
