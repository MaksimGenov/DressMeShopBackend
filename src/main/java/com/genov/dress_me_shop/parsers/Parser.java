package com.genov.dress_me_shop.parsers;

public interface Parser {
	String toString(Object obj) throws Exception;
	<T> T fromString(String text, Class<T> klass) throws Exception;
}
