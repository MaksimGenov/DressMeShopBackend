package com.genov.dress_me_shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Brand;
import com.genov.dress_me_shop.dto.brand.BrandCreateDTO;
import com.genov.dress_me_shop.dto.brand.BrandUpdateDTO;

public interface BrandService {
	Brand create(BrandCreateDTO brandCreateDTO, MultipartFile image);
	Brand update(BrandUpdateDTO brandUpdateDTO, MultipartFile file);
	void delete(Long id);
	Brand getById(Long id);
	Brand getByName(String name);
	Page<Brand> getPage(Pageable pageRequest);
}
