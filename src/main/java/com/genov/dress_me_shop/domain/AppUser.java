package com.genov.dress_me_shop.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "users")
public class AppUser extends BaseEntity {
	private String username;
	private String password;
	private String email;
	private Integer age;
	private Set<Role> roles;
	private Set<OrderItem> cart;

	public AppUser() {
		this.setRoles(new HashSet<>());
	}

	@Column(nullable = false)
	public String getUsername() {
		return username;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	@Column
	public Integer getAge() {
		return age;
	}

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false))
	public Set<Role> getRoles() {
		return roles;
	}
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderItem> getCart() {
		return cart;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setCart(Set<OrderItem> orderItems) {
		this.cart = orderItems;
	}

	public void addRole(Role role) {
		this.getRoles().add(role);
	}

	public void removeRole(Role role) {
		this.getRoles().remove(role);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		this.cart.add(orderItem);
	}
	
	public void removeOrderItem(OrderItem orderItem) {
		this.cart.remove(orderItem);
	}
}
