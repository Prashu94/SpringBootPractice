package com.prospring5.ch3.annotated;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotatedBeanNaming {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotated.xml");
		ctx.refresh();
		
		Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
		beans.entrySet().stream().forEach(b -> System.out.println(
					"id: "+ b.getKey() + "\n aliases: "
					+ Arrays.toString(ctx.getAliases(b.getKey())) + "\n"
				));
		
		ctx.close();
	}
}
