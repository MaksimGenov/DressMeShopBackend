package com.genov.dress_me_shop.service;

import com.genov.dress_me_shop.domain.OrderItem;
import com.genov.dress_me_shop.dto.order.OrderItemCreateDTO;

public interface OrderItemService {
	OrderItem create(OrderItemCreateDTO orderItemCreateDTO);
	OrderItem getById(Long id);
}
