package com.genov.dress_me_shop.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genov.dress_me_shop.domain.OrderItem;
import com.genov.dress_me_shop.dto.order.OrderItemCreateDTO;
import com.genov.dress_me_shop.dto.order.OrderItemDTO;
import com.genov.dress_me_shop.dto.user.ChangePasswordRequest;
import com.genov.dress_me_shop.dto.user.LoginRequest;
import com.genov.dress_me_shop.dto.user.RegisterRequest;
import com.genov.dress_me_shop.dto.user.UserDTO;
import com.genov.dress_me_shop.service.UserService;
import com.genov.dress_me_shop.utils.Mapper;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private Mapper mapper;

	public UserController(UserService userService, Mapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}

	@PostMapping("/register")
	public UserDTO register(@RequestBody RegisterRequest registerRequest) {
		return this.userService.register(registerRequest);
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public UserDTO login(@RequestBody LoginRequest loginRequest) {
		return this.userService.login(loginRequest);
	}
	
	@PostMapping("/change-password")
	public void chnagePassword(@RequestBody ChangePasswordRequest changePasswordRequest, HttpServletRequest req) {
		String username = req.getUserPrincipal().getName();
		this.userService.changePassword(username, changePasswordRequest);
	}
	
	@PostMapping("/reset-password")
	public void resetPassword(HttpServletRequest req) {
		String username = req.getUserPrincipal().getName();
		this.userService.resetPassword(username);
	}
	
	@PostMapping("/add-order-item-to-cart")
	public void addOrderToCart(@RequestBody OrderItemCreateDTO orderItemCreateDTO, HttpServletRequest req) {
		String username = req.getUserPrincipal().getName();
		this.userService.addOrderItem(username, orderItemCreateDTO);
	}
	
	@DeleteMapping("/remove-order-item-from-cart/{id}")
	public void removeOrderItemFromCart(@PathVariable("id") Long orderItemId, HttpServletRequest req) {
		String username = req.getUserPrincipal().getName();
		this.userService.removeOrderItem(username, orderItemId);
	}
	
	@GetMapping("/get-cart-items")
	public OrderItemDTO[] getCartItems(HttpServletRequest req) {
		String username = req.getUserPrincipal().getName();
		
		Set<OrderItem> cartItems = this.userService.getCartItems(username);
		
		return this.mapper.map(cartItems, OrderItemDTO[].class);
	}
}
