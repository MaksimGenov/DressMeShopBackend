package com.genov.dress_me_shop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.AppUser;
import com.genov.dress_me_shop.domain.Product;
import com.genov.dress_me_shop.dto.file.FileMetadataCreateDTO;
import com.genov.dress_me_shop.utils.FileManager;

@RestController
@RequestMapping(path = "/test")
public class TestController {
	
	private FileManager dropbox;

	public TestController(FileManager dropbox) {
		this.dropbox = dropbox;
	}

	@GetMapping
	public AppUser getUser() {
		AppUser user = new AppUser();
		user.setUsername("Max");
		user.setEmail("email@abv.bg");
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		return user;
	}
	
	@GetMapping("/secured")
	public AppUser getUser1() {
		AppUser user = new AppUser();
		user.setUsername("Max1");
		user.setEmail("email@abv.bg");
		return user;
	}
	
	@GetMapping("/users")
	public AppUser getUser5() {
		AppUser user = new AppUser();
		user.setUsername("Max111");
		user.setEmail("email@abv.bg");
		return user;
	}
	

	@GetMapping("/all")
	public Product[] getUser4() {
		RestTemplate rt = new RestTemplate();
		Product[] products;
		try {
			products = rt.getForObject("https://dress-me-shop.herokuapp.com/api/products/all", Product[].class);			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return products;
	}
	
	@PostMapping("/free")
	public FileMetadataCreateDTO getUser43(@RequestPart MultipartFile[] files) throws Exception {
		return this.dropbox.upload(files[0], "/test-images/", FileMetadataCreateDTO.class);
	}
	
	@PostMapping("/delete")
	public boolean getUser76(@RequestParam("path")String path) throws Exception {
		this.dropbox.delete(path);
		
		return true;
	}
	
}
