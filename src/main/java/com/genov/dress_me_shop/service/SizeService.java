package com.genov.dress_me_shop.service;

import java.util.List;

import com.genov.dress_me_shop.domain.Size;
import com.genov.dress_me_shop.dto.size.SizeCreateDTO;

public interface SizeService {
	Size create(SizeCreateDTO sizeCreateDTO);
	Size getByName(String name);
	Size getById(Long id);
	List<Size> getAll();
	void delete(Long id);
}
