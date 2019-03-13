package com.genov.dress_me_shop.dto;

import java.util.List;

public class PageRequestDTO {
	private Integer page;
	private Integer size;
	private List<SortDTO> sort;

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public List<SortDTO> getSort() {
		return sort;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setSort(List<SortDTO> sort) {
		this.sort = sort;
	}

}
