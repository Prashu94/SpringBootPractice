package com.prospring5.ch3.xml;

import com.prospring5.ch3.ContentHolder;

public class LyricHolder implements ContentHolder{
	private String value = "'You be the DJ, I'll will be the driver'";

	@Override
	public String toString() {
		return "LyricHolder [value=" + value + "]";
	}
	
	
}
