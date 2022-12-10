package com.infosys.dto;

public class AddressDTO {
	
	private Integer addressId;
	private String street;
	private String city;
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "AddressDTO [addressId=" + addressId + ", street=" + street + ", city=" + city + "]";
	}
	
	
}
