package com.infosys.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Services {
	
	@Id
	private Integer serviceId;
	private String serviceName;
	private Integer serviceCost;
	
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Integer getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(Integer serviceCost) {
		this.serviceCost = serviceCost;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(serviceId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Services other = (Services) obj;
		return Objects.equals(serviceId, other.serviceId);
	}
	
	
	
	
}
