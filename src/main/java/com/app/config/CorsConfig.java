package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/login")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.exposedHeaders("*");
				
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/login/**")
//				.allowedOrigins("http://localhost:4200")
//				.allowedHeaders("POST", "Content-Type","X-Requested-With","accept","Origin",
//						"Access-Control-Request-Method","Access-Control-Request-Headers")
//				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
//				registry.addMapping("/api/clients/**")
//				.allowedOrigins("http://localhost:4200")
//				.allowedHeaders("GET", "Content-Type","X-Requested-With","accept","Origin",
//						"Access-Control-Request-Method","Access-Control-Request-Headers")
//				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
//			};
//		};
//	}
//	
//	@Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:4200")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("Authorization", "Content-Type")
//                .allowCredentials(true);
//    }
}