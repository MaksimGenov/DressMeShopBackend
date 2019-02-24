package com.genov.dress_me_shop.service;

import com.genov.dress_me_shop.domain.SizeQuantity;
import com.genov.dress_me_shop.dto.size.SizeQuantityCreateDTO;

public interface SizeQuantityService {
	SizeQuantity create(SizeQuantityCreateDTO sizeQuantityCreateDTO);
}
