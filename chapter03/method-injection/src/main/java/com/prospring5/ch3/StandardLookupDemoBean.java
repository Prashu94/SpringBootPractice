package com.prospring5.ch3;

public class StandardLookupDemoBean implements DemoBean{
	
	private Singer mySinger;
	
	
	public void setMySinger(Singer mySinger) {
		this.mySinger = mySinger;
	}


	public Singer getMySinger() {
		return this.mySinger;
	}


	public void doSomething() {
		mySinger.sing();
	}
	

}
