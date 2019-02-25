package com.genov.dress_me_shop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.genov.dress_me_shop.domain.Product;
import com.genov.dress_me_shop.dto.product.ProductSearchDTO;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByModel(String model);

	@Query(value =
	        "SELECT p.id, p.description, p.model, p.price, p.brand_id "
	        + "FROM products AS p "
	        + "JOIN brands AS b "
	        + "ON p.brand_id = b.id "
	        + "JOIN products_categories AS pc "
	        + "ON pc.product_id = p.id "
	        + "JOIN categories AS c "
	        + "ON pc.category_id = c.id "
	        + "JOIN size_quantity as sq "
	        + "ON sq.product_id = p.id "
	        + "JOIN sizes as s "
	        + "ON s.id = sq.size_id "
	        + "WHERE (:#{#example.model} IS NULL OR p.model = :#{#example.model}) "
	        + "AND (:#{#example.brand} IS NULL OR b.name = :#{#example.brand}) "
	        + "AND (COALESCE(:#{#example.categories}) IS NULL OR c.name IN :#{#example.categories}) "
	        + "AND (COALESCE(:#{#example.sizes}) IS NULL OR s.name IN :#{#example.sizes}) "
	        + "AND (p.price BETWEEN "
	        	+ "IF(:#{#example.minPrice} IS NULL, 0, :#{#example.minPrice}) "
	        	+ "AND "
	        	+ "IF(:#{#example.maxPrice} IS NULL, 1000000000, :#{#example.maxPrice})) "
	        + "GROUP BY p.id"
			,nativeQuery = true
			)
	Page<Product> search(@Param("example")ProductSearchDTO example, Pageable pageable);
}
