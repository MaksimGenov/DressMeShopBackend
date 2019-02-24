package com.genov.dress_me_shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genov.dress_me_shop.domain.Size;
import com.genov.dress_me_shop.dto.size.SizeCreateDTO;
import com.genov.dress_me_shop.dto.size.SizeDTO;
import com.genov.dress_me_shop.service.SizeService;
import com.genov.dress_me_shop.utils.Mapper;

@RestController
@RequestMapping("/sizes")
public class SizeController {
	
	private SizeService sizeService;
	private Mapper mapper;

	public SizeController(SizeService sizeService, Mapper mapper) {
		this.sizeService = sizeService;
		this.mapper = mapper;
	}

	@PostMapping("/create")
	public Size create(@RequestBody SizeCreateDTO sizeCreateDTO) {
		
		return this.sizeService.create(sizeCreateDTO);
	}
	
	@GetMapping("/all")
	public SizeDTO[] getAll() {
		return this.mapper.map(this.sizeService.getAll(), SizeDTO[].class);
	}
}
