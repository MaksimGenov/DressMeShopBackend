package com.genov.dress_me_shop.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genov.dress_me_shop.dto.product.ProductCreateDTO;

public class ProductCreateDtoHttpMessageConverter implements HttpMessageConverter<ProductCreateDTO> {
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return ProductCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return ProductCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return List.of(MediaType.MULTIPART_FORM_DATA);
	}

	@Override
	public ProductCreateDTO read(Class<? extends ProductCreateDTO> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		ObjectMapper mapper = new ObjectMapper();
		String body = new BufferedReader(new InputStreamReader(inputMessage.getBody())).lines()
				   .parallel().collect(Collectors.joining("\n"));
		return mapper.readValue(body, clazz);
	}

	@Override
	public void write(ProductCreateDTO t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

}
