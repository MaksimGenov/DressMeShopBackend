package com.genov.dress_me_shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Category;
import com.genov.dress_me_shop.dto.category.CategoryCreateDTO;
import com.genov.dress_me_shop.dto.category.CategoryDetailsDTO;
import com.genov.dress_me_shop.service.CategoryService;
import com.genov.dress_me_shop.utils.Mapper;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	private CategoryService categoryService;
	private Mapper mapper;

	public CategoryController(CategoryService categoryService, Mapper mapper) {
		this.categoryService = categoryService;
		this.mapper = mapper;
	}

	@PostMapping("/create")
	public CategoryDetailsDTO create(CategoryCreateDTO categoryCreateDTO, MultipartFile image) {
		Category category = this.categoryService.create(categoryCreateDTO, image);
		
		return this.mapper.map(category, CategoryDetailsDTO.class);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id")Long id) {
		this.categoryService.delete(id);
	}
	
	@GetMapping("/{id}")
	public CategoryDetailsDTO getOne(@PathVariable("id")Long id) {
		Category category =  this.categoryService.getById(id);
		
		return this.mapper.map(category, CategoryDetailsDTO.class);
	}
	
	@GetMapping
	public Page<CategoryDetailsDTO> getPage(Pageable pageRequest) {
		return this.categoryService.getPage(pageRequest)
				.map(category -> this.mapper.map(category, CategoryDetailsDTO.class));
	}
}
