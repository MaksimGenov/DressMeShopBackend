package com.genov.dress_me_shop.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import com.genov.dress_me_shop.domain.Size;
import com.genov.dress_me_shop.dto.size.SizeCreateDTO;
import com.genov.dress_me_shop.repository.SizeRepository;
import com.genov.dress_me_shop.utils.Mapper;
import com.genov.dress_me_shop.utils.validation.ValidationUtil;

@Service
public class SizeServiceImpl implements SizeService {

	private SizeRepository sizeRepository;
	private Mapper mapper;
	private ValidationUtil validationUtil;

	public SizeServiceImpl(
			SizeRepository sizeRepository,
			Mapper mapper,
			ValidationUtil validationUtil) {
		this.sizeRepository = sizeRepository;
		this.mapper = mapper;
		this.validationUtil = validationUtil;
	}

	@Override
	public Size create(SizeCreateDTO sizeCreateDTO) {
		if (!this.validationUtil.isValid(sizeCreateDTO))
			throw new ConstraintViolationException(this.validationUtil.getViolations(sizeCreateDTO));
		
		Size size = this.mapper.map(sizeCreateDTO, Size.class);
		
		return this.sizeRepository.saveAndFlush(size);
	}

	@Override
	public Size getByName(String name) {
		Size size = this.sizeRepository.findByName(name).orElse(null);
		
		if (size == null)
			throw new NoSuchElementException("Size \"" + name + "\" does not exist.");
		
		return size;
	}

	@Override
	public Size getById(Long id) {
		Size size = this.sizeRepository.findById(id).orElse(null);
		
		if (size == null)
			throw new NoSuchElementException("Size with id: \"" + id + "\" does not exist.");
		
		return size;
	}

	@Override
	public List<Size> getAll() {
		return this.sizeRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		this.sizeRepository.deleteById(id);
	}

}
