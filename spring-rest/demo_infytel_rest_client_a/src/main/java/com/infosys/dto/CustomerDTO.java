package com.infosys.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerDTO {

	@NotNull(message = "Phone Number Should Be Present, please check")
	private Long phoneNo;
	
	private String name;
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$",message="Password is not in format, please check")
	private String password;
	@NotNull(message = "Email-Id should be present, please check")
	@Email(message = "Email-Id is not in format, please check")
	private String email;
	private int age;
	private char gender;
	private List<FriendFamilyDTO> friendAndFamily;
	private String address;
	@NotNull(message = "Plan can not be empty, please check")
	private PlanDTO currentPlan;
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public List<FriendFamilyDTO> getFriendAndFamily() {
		return friendAndFamily;
	}
	public void setFriendAndFamily(List<FriendFamilyDTO> friendAndFamily) {
		this.friendAndFamily = friendAndFamily;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public PlanDTO getCurrentPlan() {
		return currentPlan;
	}
	public void setCurrentPlan(PlanDTO currentPlan) {
		this.currentPlan = currentPlan;
	}
	
	@Override
	public String toString() {
		return "CustomerDTO [phoneNo=" + phoneNo + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", age=" + age + ", gender=" + gender + ", friendAndFamily=" + friendAndFamily + ", address="
				+ address + ", currentPlan=" + currentPlan + "]";
	}
	
	
}
