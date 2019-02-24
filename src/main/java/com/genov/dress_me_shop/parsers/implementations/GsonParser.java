package com.genov.dress_me_shop.parsers.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.genov.dress_me_shop.parsers.Parser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component()
@Qualifier("GsonParser")
public class GsonParser implements Parser {
	private static Gson gson;
	
	static {
		gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				//.setPrettyPrinting()
				.create();
	}
	
	@Override
	public String toString(Object obj) {
		return gson.toJson(obj);
	}

	@Override
	public <T> T fromString(String text, Class<T> klass) {
		return gson.fromJson(text, klass);
	}
}
