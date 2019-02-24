package com.genov.dress_me_shop.utils;

public interface Mapper {
	<T> T map(Object src, Class<T> destinationType);
	//<T> T fromString(String src, Class<T> destinationType);
}
