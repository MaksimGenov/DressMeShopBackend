package com.genov.dress_me_shop.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.genov.dress_me_shop.domain.AppUser;
import com.genov.dress_me_shop.domain.OrderItem;
import com.genov.dress_me_shop.domain.Role;
import com.genov.dress_me_shop.dto.order.OrderItemCreateDTO;
import com.genov.dress_me_shop.dto.user.ChangePasswordRequest;
import com.genov.dress_me_shop.dto.user.LoginRequest;
import com.genov.dress_me_shop.dto.user.RegisterRequest;
import com.genov.dress_me_shop.dto.user.UserDTO;
import com.genov.dress_me_shop.exceptions.CustomAuthenticationException;
import com.genov.dress_me_shop.exceptions.UsernameTakenException;
import com.genov.dress_me_shop.repository.RoleRepository;
import com.genov.dress_me_shop.repository.UserRepository;
import com.genov.dress_me_shop.utils.JwtUtil;
import com.genov.dress_me_shop.utils.MailSender;
import com.genov.dress_me_shop.utils.Mapper;
import com.genov.dress_me_shop.utils.PasswordGenerator;
import com.genov.dress_me_shop.utils.validation.ValidationUtil;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtUtil jwtUtil;
	private RoleRepository roleRepository;
	private ValidationUtil validationUtil;
	private MailSender mailSender;
	private PasswordGenerator passwordGenerator;
	private OrderItemService orderItemService;

	public UserServiceImpl(
			UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder,
			JwtUtil jwtUtil,
			ValidationUtil validationUtil,
			MailSender mailSender,
			PasswordGenerator passwordGenerator,
			Mapper mapper,
			OrderItemService orderItemService
			) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		this.validationUtil = validationUtil;
		this.mailSender = mailSender;
		this.passwordGenerator = passwordGenerator;
		this.orderItemService = orderItemService;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = this.userRepository.findByUsername(username).orElse(null);
		
		if (user == null)
			throw new UsernameNotFoundException("User with name " + username + " does not exist.");
		
		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getName()))
				.collect(Collectors.toSet());
		
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
		return userDetails;
	}

	@Override
	public UserDTO login(LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		AppUser user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' does not exist."));
		
		if (!this.passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
			throw new CustomAuthenticationException("Invalid credentials!");
		
		String jwt = this.jwtUtil.generate(user);
		Set<String> roles = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet());
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setRoles(roles);
		userDTO.setToken("Bearer " + jwt);
		
		return userDTO;
	}

	@Override
	public UserDTO register(RegisterRequest registerRequest) {
		AppUser user = this.userRepository.findByUsername(registerRequest.getUsername()).orElse(null);
		
		if (user != null)
			throw new UsernameTakenException("Username '" + registerRequest.getUsername() + "' is taken.");
		
		if (!this.validationUtil.isValid(registerRequest)) 
			throw new ConstraintViolationException("Invalid data!", this.validationUtil.getViolations(registerRequest));
			
		user = new AppUser();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setAge(registerRequest.getAge());

		Role role = this.roleRepository.findByName("user");
		user.addRole(role);
		
		user = this.userRepository.saveAndFlush(user);
		
		String jwt = this.jwtUtil.generate(user);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(registerRequest.getUsername());
		userDTO.setToken(jwt);
		userDTO.addRole(role.getName());
		return userDTO;
	}

	@Override
	public void changePassword(String username, ChangePasswordRequest changePasswordRequest) {
		
		if (!changePasswordRequest.getPassword().equals(changePasswordRequest.getConfirmPassword()))
			throw new RuntimeException("Passwords do not match.");
		
		AppUser user = this.userRepository.findByUsername(username).orElse(null);
		user.setPassword(this.passwordEncoder.encode(changePasswordRequest.getPassword()));
		
		this.userRepository.saveAndFlush(user);
	}

	@Override
	public void resetPassword(String username) {
		AppUser user = this.userRepository.findByUsername(username).orElse(null);
		String oldPassword = user.getPassword();
		String newPassword = this.passwordGenerator.generate(20);
		
		user.setPassword(this.passwordEncoder.encode(newPassword));
		
		this.userRepository.save(user);
		
		String reciver = user.getEmail();
		String text = "Your new password is: " + newPassword;
		
		try {
			this.mailSender.send(reciver, "Password reset", text);
		} catch (Exception e) {
			user.setPassword(oldPassword);
			this.userRepository.save(user);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addOrderItem(String username, OrderItemCreateDTO orderItemCreateDTO) {
		AppUser user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' does not exist."));
		
		Set<OrderItem> cart = user.getCart();
		
		OrderItem duplicateItem = cart.stream()
				.filter(orderItem -> {
					boolean isProductMatch = orderItem.getProduct().getId() == orderItemCreateDTO.getProductId();
					boolean isSizeMatch = orderItem.getSize().getName().equals(orderItemCreateDTO.getSize());
					return isProductMatch && isSizeMatch;			
				})
				.findFirst().orElse(null);
		
		if (duplicateItem != null)
			duplicateItem.setQuantity(duplicateItem.getQuantity() + orderItemCreateDTO.getQuantity());
		else {
			OrderItem orderItem = this.orderItemService.create(orderItemCreateDTO);
			orderItem.setUser(user);
			user.addOrderItem(orderItem);			
		}
		
		this.userRepository.save(user);
	}

	@Override
	public void removeOrderItem(String username, Long orderItemId) {
		AppUser user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' does not exist."));
		OrderItem orderItem = this.orderItemService.getById(orderItemId);
		
		user.removeOrderItem(orderItem);
		
		this.userRepository.save(user);
	}

	@Override
	public Set<OrderItem> getCartItems(String username) {
		AppUser user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' does not exist."));
		return user.getCart();
	}
}