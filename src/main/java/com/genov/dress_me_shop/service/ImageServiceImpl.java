package com.genov.dress_me_shop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.domain.Image;
import com.genov.dress_me_shop.dto.file.FileMetadataCreateDTO;
import com.genov.dress_me_shop.repository.ImageRepository;
import com.genov.dress_me_shop.utils.FileManager;
import com.genov.dress_me_shop.utils.Mapper;

@Service
public class ImageServiceImpl implements ImageService {

	private FileManager fileManager;
	private ImageRepository imageRepository;
	private Mapper mapper;

	public ImageServiceImpl(FileManager fileManager,
			ImageRepository imageRepository,
			Mapper mapper) {
		this.fileManager = fileManager;
		this.imageRepository = imageRepository;
		this.mapper = mapper;
	}

	@Override
	public Image create(MultipartFile file, String dir) {
		FileMetadataCreateDTO fileMetadata = this.fileManager
				.upload(file, dir, FileMetadataCreateDTO.class);
		Image image = this.mapper.map(fileMetadata, Image.class);
		
		return this.imageRepository.saveAndFlush(image);
	}

	@Override
	public Image update(Image image, MultipartFile newFile, String dir) {	
		if (image != null) 
			this.fileManager.delete(image.getPath());	
		
		Image newImage =  this.create(newFile, dir);
		
		if (image == null)
			return this.imageRepository.saveAndFlush(newImage);
		
		newImage.setId(image.getId());
		
		return newImage;
	}

	@Override
	public void delete(Image image) {
		if (image == null)
			return;
		this.fileManager.delete(image.getPath());
		//this.imageRepository.delete(image);
	}

}
