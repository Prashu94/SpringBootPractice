package com.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("johnMayer")
@Trophy(name= {"grammy", "platinum disk"})
public class Singer {
	private String lyric = "We found a message in a bottle we are drining";
	
	private void setLyric(@Value("I'm busted up but I'm loving every minute of it") String lyric) {
		this.lyric = lyric;
	}
	
	public void sing() {
		System.out.println(lyric);
	}
}
