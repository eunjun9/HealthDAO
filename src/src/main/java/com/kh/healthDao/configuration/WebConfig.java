package com.kh.healthDao.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final String uploadImagesPath;
	
	public WebConfig(@Value("${custom.path.upload-images}") String uploadImagesPath) {
		this.uploadImagesPath = uploadImagesPath;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		List<String> imageFolders = Arrays.asList("main", "etc");	// 폴더 추가
		
		for(String imageFolder : imageFolders) {
			registry.addResourceHandler("/images/upload/" + imageFolder + "/**")			// 이걸로 보여짐
				.addResourceLocations("file:///" + uploadImagesPath + imageFolder + "/");	// 이 경로가
			
		}
	}
	
	
}
