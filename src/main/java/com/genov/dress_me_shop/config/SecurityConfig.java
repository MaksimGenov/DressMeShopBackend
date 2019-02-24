package com.genov.dress_me_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import com.genov.dress_me_shop.security.JwtAuthorizationFilter;
import com.genov.dress_me_shop.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserService userService;
	private PasswordEncoder passwordEncoder;
	private AuthenticationEntryPoint authenticationEntryPoint;
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	public SecurityConfig(
			UserService userService,
			PasswordEncoder passwordEncoder,
			AuthenticationEntryPoint authenticationEntryPoint,
			JwtAuthorizationFilter jwtAuthorizationFilter
			) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
				.csrf().disable()
				.httpBasic().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.anonymous().disable()
				.authorizeRequests()
//				.antMatchers("/login").permitAll()
				.antMatchers("/brands/edit").hasAuthority("admin")
//				.antMatchers("/users").hasAuthority("user")
//				.antMatchers("/all").hasAnyAuthority("admin", "user")
				.anyRequest().authenticated()
			.and()
				.addFilterAfter(this.jwtAuthorizationFilter, ExceptionTranslationFilter.class)
				//.addFilterBefore(this.jwtAuthorizationFilter, AnonymousAuthenticationFilter.class)
				.exceptionHandling()
				.authenticationEntryPoint(this.authenticationEntryPoint);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/brands/**", "/categories/**", "/sizes/**", "/products**", "/users/register", 
					"/users/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(this.passwordEncoder);
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//		config.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT"));
//		source.registerCorsConfiguration("/**", config);
//
//		//source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return source;
//	}
}
