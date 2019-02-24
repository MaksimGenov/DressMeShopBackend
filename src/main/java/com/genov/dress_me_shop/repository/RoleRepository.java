package com.genov.dress_me_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genov.dress_me_shop.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
