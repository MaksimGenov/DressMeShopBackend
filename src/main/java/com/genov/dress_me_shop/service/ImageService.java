package com.genov.dress_me_shop.service;

import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Image;

public interface ImageService {
	Image create(MultipartFile file, String dir);
	void delete(Image image);
	Image update(Image image, MultipartFile newFile, String dir);
}
