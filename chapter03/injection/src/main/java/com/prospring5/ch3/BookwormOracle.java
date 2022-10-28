package com.prospring5.ch3;

public class BookwormOracle implements Oracle{
	private Encyclopedia encyclopedia;
	
	public void setEncyclopedia() {
		this.encyclopedia = encyclopedia;
	}
	public String defineMeaningOfLife() {
		return "Encyclopeadias are waste of money - go see the world instead";
	}
	
}
