package com.infytel;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@SpringBootApplication

public class InfytelDemo6Application implements WebMvcConfigurer
{

	
	public static void main(String[] args) 
	{
		
		SpringApplication.run(InfytelDemo6Application.class, args);

	}


	//To support matrix parameters
	 @Override
	 public void configurePathMatch(PathMatchConfigurer configurer) 
	{
	        UrlPathHelper urlPathHelper = new UrlPathHelper();
	        urlPathHelper.setRemoveSemicolonContent(false);
	        configurer.setUrlPathHelper(urlPathHelper);
	    }
}
