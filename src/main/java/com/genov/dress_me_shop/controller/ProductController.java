package com.genov.dress_me_shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Product;
import com.genov.dress_me_shop.dto.product.ProductCreateDTO;
import com.genov.dress_me_shop.dto.product.ProductDTO;
import com.genov.dress_me_shop.dto.product.ProductDetailsDTO;
import com.genov.dress_me_shop.dto.product.ProductSearchDTO;
import com.genov.dress_me_shop.dto.product.ProductUpdateDTO;
import com.genov.dress_me_shop.service.ProductService;
import com.genov.dress_me_shop.utils.Mapper;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	private Mapper mapper;

	public ProductController(ProductService productService, Mapper mapper) {
		this.productService = productService;
		this.mapper = mapper;
	}

	@PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProductDetailsDTO create(@RequestPart("product")ProductCreateDTO productCreateDTO,
									@RequestPart("images") MultipartFile[] images) {
		Product product = this.productService.create(productCreateDTO, images);

		return this.mapper.map(product, ProductDetailsDTO.class);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.productService.delete(id);
	}

	@GetMapping("/{id}")
	public ProductDetailsDTO get(@PathVariable("id") Long id) {
		Product product = this.productService.getById(id);

		return this.mapper.map(product, ProductDetailsDTO.class);
	}
	
	@GetMapping
	public Page<ProductDTO> getPage(Pageable pageRequest) {
		return this.productService.getPage(pageRequest)
				.map(product -> this.mapper.map(product, ProductDTO.class));
	}
	
	@PostMapping("/search")
	public Page<ProductDTO> search(@RequestBody ProductSearchDTO searchDTO) {
		return this.productService.search(searchDTO)
				.map(product -> this.mapper.map(product, ProductDTO.class));
	}
	
	@PutMapping("/edit")
	public ProductDetailsDTO edit(ProductUpdateDTO productUpdateDTO, MultipartFile[] images) {
		Product product = this.productService.edit(productUpdateDTO, images);
		
		return this.mapper.map(product, ProductDetailsDTO.class);
	}
	
	@PostMapping("/{id}/delete-images")
	public void deleteImages(@PathVariable("id")Long productId, @RequestBody()Long[] imagesId) {
		int a = 5;
	}
	
}
