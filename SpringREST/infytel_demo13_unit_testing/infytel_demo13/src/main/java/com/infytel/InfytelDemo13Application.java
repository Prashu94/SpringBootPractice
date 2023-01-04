package com.infytel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class InfytelDemo13Application implements WebMvcConfigurer{
	
	
	public static void main(String[] args)
	{
		SpringApplication.run(InfytelDemo13Application.class, args);
	}
}
