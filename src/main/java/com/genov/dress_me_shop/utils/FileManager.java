package com.genov.dress_me_shop.utils;

import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.dto.file.FileMetadataCreateDTO;

public interface FileManager {
	<T extends FileMetadataCreateDTO> T upload(MultipartFile file, String dir, Class<T> returnType);
	void delete(String filePath);
}
