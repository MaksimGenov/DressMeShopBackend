package com.genov.dress_me_shop.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.genov.dress_me_shop.domain.OrderItem;
import com.genov.dress_me_shop.dto.order.OrderItemCreateDTO;
import com.genov.dress_me_shop.dto.user.ChangePasswordRequest;
import com.genov.dress_me_shop.dto.user.LoginRequest;
import com.genov.dress_me_shop.dto.user.RegisterRequest;
import com.genov.dress_me_shop.dto.user.UserDTO;
import com.genov.dress_me_shop.exceptions.InvalidCredentials;

public interface UserService extends UserDetailsService {
	UserDTO login(LoginRequest loginDTO) throws InvalidCredentials;

	UserDTO register(RegisterRequest userRegisterDTO);

	void changePassword(String username, ChangePasswordRequest changePassswordRequest);

	void resetPassword(String username);

	void addOrderItem(String username, OrderItemCreateDTO orderItemCreateDTO);

	void removeOrderItem(String username, Long orderItemId);

	Set<OrderItem> getCartItems(String username);
}
