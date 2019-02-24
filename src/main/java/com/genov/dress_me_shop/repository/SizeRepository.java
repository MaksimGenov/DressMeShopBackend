package com.genov.dress_me_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genov.dress_me_shop.domain.Size;

public interface SizeRepository extends JpaRepository<Size, Long>{

	Optional<Size> findByName(String name);

}
