package com.infosys.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.infosys.dto.DesktopStatus;

@Entity
public class Desktop {
	
	@Id
	private String desktopId;
	private String desktopMake;
	private String desktopModel;
	@Enumerated(value = EnumType.STRING)
	private DesktopStatus desktopStatus;
	
	public String getDesktopId() {
		return desktopId;
	}
	public void setDesktopId(String desktopId) {
		this.desktopId = desktopId;
	}
	
	public String getDesktopMake() {
		return desktopMake;
	}
	public void setDesktopMake(String desktopMake) {
		this.desktopMake = desktopMake;
	}
	public String getDesktopModel() {
		return desktopModel;
	}
	public void setDesktopModel(String desktopModel) {
		this.desktopModel = desktopModel;
	}
	public DesktopStatus getDesktopStatus() {
		return desktopStatus;
	}
	public void setDesktopStatus(DesktopStatus desktopStatus) {
		this.desktopStatus = desktopStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(desktopId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Desktop other = (Desktop) obj;
		return Objects.equals(desktopId, other.desktopId);
	}
	
	
}
