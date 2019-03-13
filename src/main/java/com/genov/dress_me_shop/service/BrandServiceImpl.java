package com.genov.dress_me_shop.service;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Brand;
import com.genov.dress_me_shop.domain.Image;
import com.genov.dress_me_shop.dto.brand.BrandCreateDTO;
import com.genov.dress_me_shop.dto.brand.BrandUpdateDTO;
import com.genov.dress_me_shop.repository.BrandRepository;
import com.genov.dress_me_shop.utils.Mapper;
import com.genov.dress_me_shop.utils.validation.ValidationUtil;

@Service
public class BrandServiceImpl implements BrandService {

	private final String UPLOAD_DIR = "/shop/brands/";
	
	private BrandRepository brandRepository;
	private ValidationUtil validationUtil;
	private ImageService imageService;
	private Mapper mapper;

	public BrandServiceImpl(BrandRepository brandRepository,
			ValidationUtil validationUtil, 
			ImageService imageService,
			Mapper mapper) {
		this.brandRepository = brandRepository;
		this.validationUtil = validationUtil;
		this.imageService = imageService;
		this.mapper = mapper;
	}

	@Override
	public Brand create(BrandCreateDTO brandCreateDTO, MultipartFile file) {
		if (!this.validationUtil.isValid(brandCreateDTO)) 
			throw new ConstraintViolationException(this.validationUtil.getViolations(brandCreateDTO));

		Brand brand = this.mapper.map(brandCreateDTO, Brand.class);

		if (file != null) {
			Image image = this.imageService.create(file, UPLOAD_DIR);
			brand.setImage(image);
		}

		return this.brandRepository.saveAndFlush(brand);
	}

	@Override
	public Brand update(BrandUpdateDTO brandUpdateDTO, MultipartFile file) {
		if (!this.validationUtil.isValid(brandUpdateDTO))
			throw new ConstraintViolationException(this.validationUtil.getViolations(brandUpdateDTO));

		Brand oldBrand = this.getById(brandUpdateDTO.getId());
		Brand newBrand = this.mapper.map(brandUpdateDTO, Brand.class);
		
		if (file != null) {
			Image newImage = this.imageService.update(oldBrand.getImage(), file, UPLOAD_DIR);
			newBrand.setImage(newImage);
		} else {
			newBrand.setImage(oldBrand.getImage());
		}
		
		return this.brandRepository.saveAndFlush(newBrand);	
	}
	
	@Override
	public void delete(Long id) {
		Brand brand = this.getById(id);

		this.imageService.delete(brand.getImage());

		brand.getProducts()
			.forEach(product -> product.getImages()
				.forEach(image -> imageService.delete(image)));
		
		this.brandRepository.delete(brand);
	}

	@Override
	public Brand getById(Long id) {
		return this.brandRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Brand with id: \"" + id + "\" does not exist."));
	}

	@Override
	public Brand getByName(String name) {
		return this.brandRepository.findByName(name)
				.orElseThrow(() -> new NoSuchElementException("Brand '" + name + "' does not exist."));
	}

	@Override
	public Page<Brand> getPage(Pageable pageRequest) {
		return this.brandRepository.findAll(pageRequest);
	}
}
