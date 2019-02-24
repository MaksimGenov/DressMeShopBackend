package com.genov.dress_me_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genov.dress_me_shop.domain.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{
	Optional<AppUser> findByUsername(String name);
}
