package com.infosys.dto;

import java.util.List;

public class CustomerDTO {
	
	private long phoneNo;
	private String name;
	private String email;	
	private int age;
	private char gender;
	private String password;
	private String address;
	private List<FriendFamilyDTO> friendAndFamily;
	private PlanDTO currentPlan;
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<FriendFamilyDTO> getFriendAndFamily() {
		return friendAndFamily;
	}
	public void setFriendAndFamily(List<FriendFamilyDTO> friendAndFamily) {
		this.friendAndFamily = friendAndFamily;
	}
	public PlanDTO getCurrentPlan() {
		return currentPlan;
	}
	public void setCurrentPlan(PlanDTO currentPlan) {
		this.currentPlan = currentPlan;
	}
	@Override
	public String toString() {
		return "CustomerDTO [phoneNo=" + phoneNo + ", name=" + name + ", email=" + email + ", age=" + age + ", gender="
				+ gender + ", password=" + password + ", address=" + address + ", friendAndFamily=" + friendAndFamily
				+ ", currentPlan=" + currentPlan + "]";
	}
	
	
	
	
		
}
