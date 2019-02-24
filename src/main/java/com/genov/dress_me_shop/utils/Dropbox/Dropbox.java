package com.genov.dress_me_shop.utils.Dropbox;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.genov.dress_me_shop.dto.file.FileMetadataCreateDTO;
import com.genov.dress_me_shop.parsers.Parser;
import com.genov.dress_me_shop.utils.FileManager;

@Component
public class Dropbox implements FileManager {

	private final String token = "eMMmRqDA0dUAAAAAAAAFUm2dboz2CPNsInEs7apAq3DC5RQc_0Xs6kwQOgHPZ64N";
	private Parser parser;

	public Dropbox(@Qualifier("JsonParser")Parser parser) {
		this.parser = parser;
	}

	@Override
	public <T extends FileMetadataCreateDTO> T upload(MultipartFile file, String dirPath, Class<T> returnType) {
		String filename = String.valueOf(new Date().getTime()) + file.getOriginalFilename();
		String dropboxPath = dirPath + filename;

		Map<String, Object> args = new HashMap<>();
		args.put("path", dropboxPath);
		args.put("mode", "add");
		args.put("autorename", true);

		String dxArgs;
		
		try {
			dxArgs = this.parser.toString(args);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(this.token);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.add("Dropbox-API-Arg", dxArgs);

		byte[] body;
		
		try {
			body = file.getBytes();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage(), ioe);
		}
		
		HttpEntity<byte[]> request = new HttpEntity<>(body, headers);

		RestTemplate template = new RestTemplate();

		String url = "https://content.dropboxapi.com/2/files/upload";

		DxUploadResponse uploadResponse = template.postForObject(url, request, DxUploadResponse.class);

		return this.getFileMetadataCreateDTO(uploadResponse.getPath(), returnType);
	}

	public void delete(String filePath) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(this.token);
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> rawBody = new HashMap<>();
		rawBody.put("path", filePath);

		String body;

		try {
			body = this.parser.toString(rawBody);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		HttpEntity<String> request = new HttpEntity<>(body, headers);

		String url = "https://api.dropboxapi.com/2/files/delete_v2";

		RestTemplate template = new RestTemplate();
		template.postForObject(url, request, Object.class);
	}

	private <T extends FileMetadataCreateDTO> T getFileMetadataCreateDTO(String path, Class<T> returnType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(this.token);
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> rawBody = new HashMap<>();
		rawBody.put("path", path);
		
		String body;
		
		try {
			body = this.parser.toString(rawBody);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		HttpEntity<String> request = new HttpEntity<String>(body, headers);

		String url = "https://api.dropboxapi.com/2/sharing/create_shared_link_with_settings";

		RestTemplate template = new RestTemplate();

		return template.postForObject(url, request, returnType);
	}
}
