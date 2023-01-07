package com.infosys.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({PlanDTO.class})
public class EntityList<T> {
	
	public List<T> listOfEntityObjects;
	
	public EntityList() {
		this.listOfEntityObjects = new ArrayList<>();
	}
	
	public EntityList(List<T> listOfEntityObjects) {
		this.listOfEntityObjects = listOfEntityObjects;
	}
	
	@XmlAnyElement
	public List<T> getItems(){
		return listOfEntityObjects;
	}
	
	
}
