package com.genov.dress_me_shop.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.genov.dress_me_shop.security.JwtAuthorizationFilter;

@Configuration
public class AppBeanConfig {

	private JwtAuthorizationFilter jwtAuthorizationFilter;

	public AppBeanConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	public PasswordEncoder getPaswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public FilterRegistrationBean<JwtAuthorizationFilter> filterRegistrationBean() {
		FilterRegistrationBean<JwtAuthorizationFilter> filterRegistrationBean = new FilterRegistrationBean<JwtAuthorizationFilter>(
				this.jwtAuthorizationFilter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
