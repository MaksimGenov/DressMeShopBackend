package com.genov.dress_me_shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Brand;
import com.genov.dress_me_shop.dto.brand.BrandCreateDTO;
import com.genov.dress_me_shop.dto.brand.BrandDetailsDTO;
import com.genov.dress_me_shop.dto.brand.BrandUpdateDTO;
import com.genov.dress_me_shop.service.BrandService;
import com.genov.dress_me_shop.utils.Mapper;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
	private BrandService brandService;
	private Mapper mapper;

	public BrandController(BrandService brandService, Mapper mapper) {
		this.brandService = brandService;
		this.mapper = mapper;
	}

	@PostMapping("/create")
	public BrandDetailsDTO create(BrandCreateDTO brandCreateDTO, MultipartFile image) {
		Brand brand =  this.brandService.create(brandCreateDTO, image);
		
		return this.mapper.map(brand, BrandDetailsDTO.class);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id")Long id)  {
		this.brandService.delete(id);
	}
	
	@PutMapping("/edit")
	public BrandDetailsDTO update(BrandUpdateDTO brandUpdateDTO, MultipartFile image) {
		Brand brand = this.brandService.update(brandUpdateDTO, image);
		
		return this.mapper.map(brand, BrandDetailsDTO.class);
	}
	
	@GetMapping("/{id}")
	public BrandDetailsDTO get(@PathVariable("id")Long id) {
		Brand brand = this.brandService.getById(id);
		
		return this.mapper.map(brand, BrandDetailsDTO.class);
	}
	
	@GetMapping
	public Page<BrandDetailsDTO> getPage(Pageable pageRequest) {
		Page<Brand> page = this.brandService.getPage(pageRequest);
		
		return page.map(brand -> this.mapper.map(brand, BrandDetailsDTO.class));
	}
		
}
