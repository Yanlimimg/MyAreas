package org.javaforever.myareas;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MvcConfig implements WebMvcConfigurer{

//	@Override
//
//	public void addCorsMappings(CorsRegistry registry) {
//
//// 这里匹配了所有的URL，允许所有的外域发起跨域请求，允许外域发起请求任意HTTP Method，允许跨域请求包含任意的头信息。
//
//		registry.addMapping("/**")
//
//				.allowedHeaders("*")
//
//				.allowedMethods("*")
//
//				.allowedOrigins("*")
//
//				.allowCredentials(true);
//
//	}
	
	@Bean
	public CorsFilter corsFilter() {

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
//        //设置放行哪些原始域
//        config.addAllowedOrigin("*");
//
//        //放行哪些原始请求头部信息
//        config.addAllowedHeader("*");
//        //暴露哪些头部信息
//        config.addExposedHeader("*");
		//放行哪些请求方式
//        config.addAllowedMethod("GET");     //get
//        config.addAllowedMethod("PUT");     //put
//        config.addAllowedMethod("POST");    //post
//        config.addAllowedMethod("DELETE");  //delete
		//corsConfig.addAllowedMethod("*");     //放行全部请求

		//是否发送Cookie
		//config.setAllowCredentials(true);

		//2. 添加映射路径
		UrlBasedCorsConfigurationSource corsConfigurationSource =
				new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", config);
		//返回CorsFilter
		return new CorsFilter(corsConfigurationSource);
	}

}
