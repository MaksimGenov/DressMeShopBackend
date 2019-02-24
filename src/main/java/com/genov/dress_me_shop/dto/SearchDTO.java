package com.genov.dress_me_shop.dto;

import org.springframework.data.domain.Pageable;

public class SearchDTO<T> {
	private T example;
	private Pageable pageable;

	public T getExample() {
		return example;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setExample(T example) {
		this.example = example;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

}
