package com.prospring5.ch3.annotated;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singer")
@Scope("prototype")
public class Singer {
	private String lyric = "I played a quick game of chess with the salt an pepper shaker";
	
	public void sing() {
		
	}
}
