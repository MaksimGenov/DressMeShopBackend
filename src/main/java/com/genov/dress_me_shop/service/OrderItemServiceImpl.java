package com.genov.dress_me_shop.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.genov.dress_me_shop.domain.OrderItem;
import com.genov.dress_me_shop.domain.Product;
import com.genov.dress_me_shop.domain.Size;
import com.genov.dress_me_shop.dto.order.OrderItemCreateDTO;
import com.genov.dress_me_shop.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemRepository orderItemRepository;
	private ProductService productService;
	private SizeService sizeService;

	public OrderItemServiceImpl(
			OrderItemRepository orderItemRepository,
			ProductService productService,
			SizeService sizeService) {
		this.orderItemRepository = orderItemRepository;
		this.productService = productService;
		this.sizeService = sizeService;
	}

	@Override
	public OrderItem create(OrderItemCreateDTO orderItemCreateDTO) {
		Product product = this.productService.getById(orderItemCreateDTO.getProductId());
		Size size = this.sizeService.getByName(orderItemCreateDTO.getSize());
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setSize(size);
		orderItem.setQuantity(orderItemCreateDTO.getQuantity());
		return orderItem;
	}

	@Override
	public OrderItem getById(Long id) {
		return this.orderItemRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("OrderItem with id: " + id + " does not exist!"));
	}

}
