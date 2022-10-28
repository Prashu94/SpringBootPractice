package com.prospring5.ch3.config;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring5.ch3.annotated.Singer;

public class AliasConfigDemo {
	
	@Configuration
	static class AliasBeanConfig{
		@Bean(name= {"johnMayer", "john", "johnathan", "johnny"})
		public Singer getSinger() {
			return new Singer();
		}
	}
	
	public static void main(String[] args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AliasBeanConfig.class);
		Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
		beans.entrySet().stream().forEach(b ->
		System.out.println(
				"id: " + b.getKey() + "\n aliases: "
						+ Arrays.toString(ctx.getAliases(b.getKey())) + "\n")
				);
		ctx.close();
	}
}
