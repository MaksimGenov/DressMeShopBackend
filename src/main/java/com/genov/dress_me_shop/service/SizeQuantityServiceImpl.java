package com.genov.dress_me_shop.service;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import com.genov.dress_me_shop.domain.Size;
import com.genov.dress_me_shop.domain.SizeQuantity;
import com.genov.dress_me_shop.dto.size.SizeQuantityCreateDTO;
import com.genov.dress_me_shop.repository.SizeQuantityRepository;
import com.genov.dress_me_shop.utils.validation.ValidationUtil;

@Service
public class SizeQuantityServiceImpl implements SizeQuantityService{

	private SizeQuantityRepository sizeQuantityRepository;
	private ValidationUtil validationUtil;
	private SizeService sizeService;

	public SizeQuantityServiceImpl(
			SizeQuantityRepository sizeQuantityRepository,
			ValidationUtil validationUtil,
			SizeService sizeService) {
		this.sizeQuantityRepository = sizeQuantityRepository;
		this.validationUtil = validationUtil;
		this.sizeService = sizeService;
	}

	@Override
	public SizeQuantity create(SizeQuantityCreateDTO sizeQuantityCreateDTO) {
		if (!this.validationUtil.isValid(sizeQuantityCreateDTO))
			throw new ConstraintViolationException(this.validationUtil.getViolations(sizeQuantityCreateDTO));
		
		Size size = this.sizeService.getByName(sizeQuantityCreateDTO.getSize());
		
		SizeQuantity sizeQuantity = new SizeQuantity();
		sizeQuantity.setSize(size);
		sizeQuantity.setQuantity(sizeQuantityCreateDTO.getQuantity());
		
		return this.sizeQuantityRepository.saveAndFlush(sizeQuantity);
	}

}
