package com.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean {
	
	
	private Singer mySinger;

	@Override
	public Singer getMySinger() {
		// TODO Auto-generated method stub
		return this.mySinger;
	}

	
	@Autowired
	@Qualifier("singer")
	public void setMySinger(Singer mySinger) {
		this.mySinger = mySinger;
	}
	
	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		mySinger.sing();
	}
	
	
}
