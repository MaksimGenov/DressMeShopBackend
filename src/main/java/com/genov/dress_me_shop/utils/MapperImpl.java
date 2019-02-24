package com.genov.dress_me_shop.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {
	private static final ModelMapper modelMapper;
	
	static {
		modelMapper = new ModelMapper();
	}
	
	@Override
	public <T> T map(Object src, Class<T> destinationType) {
		return modelMapper.map(src, destinationType);
	}

}
