package com.prospring5.ch3.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.prospring5.ch3.annotated", "com.prospring5.ch2.annotated"})
@Configuration
public class HelloWorldConfiguration {

}
