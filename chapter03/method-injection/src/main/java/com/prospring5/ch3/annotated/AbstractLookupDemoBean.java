package com.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public abstract class AbstractLookupDemoBean implements DemoBean {
	
	@Override
	@Lookup("singer")
	public Singer getMySinger() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public void doSomething() {
        getMySinger().sing();
    }

}
