package com.genov.dress_me_shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Product;
import com.genov.dress_me_shop.dto.product.ProductCreateDTO;
import com.genov.dress_me_shop.dto.product.ProductSearchDTO;
import com.genov.dress_me_shop.dto.product.ProductUpdateDTO;

public interface ProductService {
	Product create(ProductCreateDTO productCreateDTO, MultipartFile[] files);
	Product edit(ProductUpdateDTO productUpdateDTO, MultipartFile[] images);
	void delete(Long id);
	Product getById(Long id);
	Product getByName(String model);
	Page<Product> getPage(Pageable pageRequest);
	Page<Product> search(ProductSearchDTO searchDTO);
}
