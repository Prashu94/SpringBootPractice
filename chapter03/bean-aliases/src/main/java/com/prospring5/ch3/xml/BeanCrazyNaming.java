package com.prospring5.ch3.xml;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanCrazyNaming {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-03.xml");
		ctx.refresh();
		Map<String, String> beans = ctx.getBeansOfType(String.class);
		beans.entrySet().stream().forEach(
					b -> System.out.println("id : "+ b.getKey() + "\n aliases: "
							+ Arrays.toString(ctx.getAliases(b.getKey()))+ "\n"));
		ctx.close();
	}
}
