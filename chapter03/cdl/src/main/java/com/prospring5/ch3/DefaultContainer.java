package com.prospring5.ch3;

public class DefaultContainer implements Container{
	
	public Object getDependency(String key) {
		if("myDependency".equals(key)) {
			return new Dependency();
		}
		
		throw new RuntimeException("Unknown Dependency "+ key);
	}
}
