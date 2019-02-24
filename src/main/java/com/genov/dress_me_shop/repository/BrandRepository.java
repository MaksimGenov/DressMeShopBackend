package com.genov.dress_me_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genov.dress_me_shop.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Optional<Brand> findByName(String name);

}
