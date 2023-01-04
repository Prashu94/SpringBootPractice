package com.infytel.dto;

 
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
 
public class CustomerDTO 
{
    @NotNull(message="Phone number should be present, please check")
	Long phoneNo;
	
	String name;
	//Password should comprise of alphabets of both the cases and digits as well with a length of minimum 5
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$",message="Password is not in format, please check")
	String password;
	@NotNull(message="Email-Id should be present, please check")
	@Email(message="Email-Id is not in format, please check")
	String email;
	int age;
	char gender;
	List<FriendFamilyDTO> friendAndFamily;
	String address;
	@NotNull(message = "Plan can not be empty, please check")
	PlanDTO currentPlan;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public PlanDTO getCurrentPlan() {
		
		return currentPlan;
	}

	public void setCurrentPlan(PlanDTO currentPlan) {
		this.currentPlan = currentPlan;
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

	@Override
	public String toString() {
		return "CustomerDTO [phoneNo=" + phoneNo + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", friendAndFamily=" + friendAndFamily + ", password=" + password + ", address=" + address + "]";
	}
	 
		
	}


