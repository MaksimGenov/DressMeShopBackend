package com.genov.dress_me_shop.parsers.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genov.dress_me_shop.parsers.Parser;

@Component
@Qualifier("JsonParser")
public class JsonParser implements Parser {
	private static ObjectMapper parser;
	
	static {
		parser = new ObjectMapper();
	}
	
	@Override
	public String toString(Object obj) throws Exception {
		return parser.writeValueAsString(obj);
	}

	@Override
	public <T> T fromString(String text, Class<T> klass) throws Exception {
		return parser.readValue(text, klass);
	}

}
