package org.javaforever.myareas.utils;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class MyConfigurationSource implements CorsConfigurationSource{

	@Override
	public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
		//创建CorsConfiguration对象后添加配置
		CorsConfiguration config = new CorsConfiguration();
		
		List<String> allowedHeaders = Arrays.asList("x-token", "content-type", "X-Requested-With", "XMLHttpRequest");
		List<String> exposedHeaders = Arrays.asList("x-token", "content-type", "X-Requested-With", "XMLHttpRequest",
				"Referrer Policy:no-referrer-when-downgrade");
		List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
		List<String> allowedOrigins = Arrays.asList("*");
		config.setAllowedHeaders(allowedHeaders);
		config.setAllowedMethods(allowedMethods);
		config.setAllowedOrigins(allowedOrigins);
		config.setExposedHeaders(exposedHeaders);
		config.setMaxAge(36000L);
		config.setAllowCredentials(true);
//		        //设置放行哪些原始域
//		        config.addAllowedOrigin("*");

		//是否发送Cookie
		//config.setAllowCredentials(true);

		//2. 添加映射路径
		UrlBasedCorsConfigurationSource corsConfigurationSource =
				new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", config);
		//返回CorsFilter
		return config;
	}
	
}
