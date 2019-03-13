package com.genov.dress_me_shop.service;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Category;
import com.genov.dress_me_shop.domain.Image;
import com.genov.dress_me_shop.dto.category.CategoryCreateDTO;
import com.genov.dress_me_shop.dto.category.CategoryUpdateDTO;
import com.genov.dress_me_shop.repository.CategoryRepository;
import com.genov.dress_me_shop.utils.Mapper;
import com.genov.dress_me_shop.utils.validation.ValidationUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private Mapper mapper;
	private ImageService imageService;
	private ValidationUtil validationUtil;

	public CategoryServiceImpl(
			CategoryRepository categoryRepository,
			ValidationUtil validationUtil,
			Mapper mapper,
			ImageService imageService) {
				this.categoryRepository = categoryRepository;
				this.validationUtil = validationUtil;
				this.mapper = mapper;
				this.imageService = imageService;
	}

	@Override
	public Category create(CategoryCreateDTO categoryCreateDTO, MultipartFile file) {
		if (!this.validationUtil.isValid(categoryCreateDTO))
			throw new ConstraintViolationException(this.validationUtil.getViolations(categoryCreateDTO));
		
		Category category = this.mapper.map(categoryCreateDTO, Category.class);
		
		if (file != null) {
			Image image = this.imageService.create(file, "/shop/categories/");
			category.setImage(image);
		}
		
		return this.categoryRepository.saveAndFlush(category);
	}

	@Override
	public Category update(CategoryUpdateDTO categoryUpdateDTO, MultipartFile newFile) {
		Category oldCategory = this.getById(categoryUpdateDTO.getId());
		
		//ToDo check unique
		Category newCategory = this.mapper.map(categoryUpdateDTO, Category.class);
		
		if (newFile != null) {
			Image newImage = this.imageService.update(oldCategory.getImage(), newFile, "/shop/categories/");
			newCategory.setImage(newImage);
		}
		
		return this.categoryRepository.saveAndFlush(newCategory);
	}
	
	@Override
	public void delete(Long id) {
		Category category = this.getById(id);
		
		this.imageService.delete(category.getImage());
		
		this.categoryRepository.delete(category);
	}

	@Override
	public Category getById(Long id) {
		return this.categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category with id: \"" + id + "\" does not exist."));
	}
	
	@Override
	public Category getByName(String name) {
		return this.categoryRepository.findByName(name)
				.orElseThrow(()-> new NoSuchElementException("Category '" + name + "' does not exist."));
	}
	
	@Override
	public Page<Category> getPage(Pageable pageRequest) {
		return this.categoryRepository.findAll(pageRequest);
	}
}
