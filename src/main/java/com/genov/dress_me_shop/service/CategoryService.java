package com.genov.dress_me_shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Category;
import com.genov.dress_me_shop.dto.category.CategoryCreateDTO;
import com.genov.dress_me_shop.dto.category.CategoryUpdateDTO;

public interface CategoryService {
	Category create(CategoryCreateDTO categoryCreateDTO, MultipartFile file);
	Category update(CategoryUpdateDTO categoryUpdateDTO, MultipartFile newFile);
	void delete(Long id);
	Category getById(Long id);
	Category getByName(String name);
	Page<Category> getPage(Pageable pageRequest);
}
