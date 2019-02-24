package com.genov.dress_me_shop.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Brand;
import com.genov.dress_me_shop.domain.Category;
import com.genov.dress_me_shop.domain.Image;
import com.genov.dress_me_shop.domain.Product;
import com.genov.dress_me_shop.domain.SizeQuantity;
import com.genov.dress_me_shop.dto.product.ProductCreateDTO;
import com.genov.dress_me_shop.dto.product.ProductSearchDTO;
import com.genov.dress_me_shop.dto.product.ProductUpdateDTO;
import com.genov.dress_me_shop.repository.ProductRepository;
import com.genov.dress_me_shop.utils.Mapper;
import com.genov.dress_me_shop.utils.validation.ValidationUtil;

@Service
public class ProductServiceImpl implements ProductService {

	private final String UPLOAD_DIR = "/shop/products/";
	
	private ProductRepository productRepository;
	private ValidationUtil validationUtil;
	private Mapper mapper;
	private BrandService brandService;
	private CategoryService categoryService;
	private ImageService imageService;
	private SizeQuantityService sizeQuantityService;
	
	public ProductServiceImpl(
			ProductRepository productRepository,
			BrandService brandService,
			CategoryService categoryService,
			ImageService imageService,
			SizeQuantityService sizeQuantityService,
			ValidationUtil validationUtil,
			Mapper mapper) {
				this.productRepository = productRepository;
				this.brandService = brandService;
				this.categoryService = categoryService;
				this.imageService = imageService;
				this.sizeQuantityService = sizeQuantityService;
				this.validationUtil = validationUtil;
				this.mapper = mapper;
	}

	@Override
	public Product create(ProductCreateDTO productCreateDTO, MultipartFile[] files) {
		if (!this.validationUtil.isValid(productCreateDTO))
			throw new ConstraintViolationException(this.validationUtil.getViolations(productCreateDTO));
		
		Product product = this.mapper.map(productCreateDTO, Product.class);
		Brand brand = this.brandService.getByName(productCreateDTO.getBrand());
	
		Set<Category> categories = Arrays.stream(productCreateDTO.getCategories())
				.map(categoryName -> this.categoryService.getByName(categoryName))
				.collect(Collectors.toSet());
		
		Set<Image> images = Arrays.stream(files)
				.map(file -> this.imageService.create(file, UPLOAD_DIR))
				.collect(Collectors.toSet());
		
		List<SizeQuantity> sizes = productCreateDTO.getSizes().stream()
				.map(s -> this.sizeQuantityService.create(s))
				.collect(Collectors.toList());
		
		
		product.setBrand(brand);
		product.setCategories(categories);
		product.setImages(images);
		
		product = this.productRepository.saveAndFlush(product);
		
		for (SizeQuantity size : sizes) 
			size.setProduct(product);
		
		product.setSizes(sizes);
		
		return this.productRepository.saveAndFlush(product);
	}

	@Override
	public Product getById(Long id) {
		return this.productRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Product with id: '" + id + "' does not exist."));
	}

	@Override
	public Product getByName(String model) {
		return this.productRepository.findByModel(model)
				.orElseThrow(() -> new NoSuchElementException("Product '" + model + "' does not exist."));
	}

	@Override
	public Page<Product> getPage(Pageable pageRequest) {
		return this.productRepository.findAll(pageRequest);
	}

	@Override
	public void delete(Long id) {
		Product product = this.getById(id);
		Set<Image> images = product.getImages();
		
		images.forEach(image -> this.imageService.delete(image));
		
		this.productRepository.delete(product);
	}

	@Override
	public Page<Product> search(ProductSearchDTO searchDTO) {
		return this.productRepository
				.search(searchDTO, PageRequest.of(searchDTO.getPage() - 1, searchDTO.getPageSize()));
	}

	@Override
	public Product edit(ProductUpdateDTO productUpdateDTO, MultipartFile[] images) {
		if (!this.validationUtil.isValid(productUpdateDTO))
			throw new ConstraintViolationException(this.validationUtil.getViolations(productUpdateDTO));
		Product product = this.getById(productUpdateDTO.getId());
		
		Brand brand = this.brandService.getByName(productUpdateDTO.getBrand());
		
		Set<Category> categories = Arrays.stream(productUpdateDTO.getCategories())
				.map(category -> this.categoryService.getByName(category))
				.collect(Collectors.toSet());
		
		if (images != null) {
			Arrays.stream(images)
			.forEach(image -> product.addImage(this.imageService.create(image, UPLOAD_DIR)));
		}
		
		product.setBrand(brand);
		product.setCategories(categories);
		product.setModel(productUpdateDTO.getModel());
		product.setPrice(productUpdateDTO.getPrice());
		product.setDescription(productUpdateDTO.getDescription());
		
		return this.productRepository.saveAndFlush(product);
	}

	
}
