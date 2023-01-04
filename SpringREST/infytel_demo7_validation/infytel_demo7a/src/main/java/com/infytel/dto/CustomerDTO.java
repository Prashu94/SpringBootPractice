package com.infytel.dto;
 
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerDTO 
{
	@NotNull(message="{customer.phone.must}")
	Long phoneNo;
	@NotBlank(message="{customer.name.must}")
	String name;
	//Password should comprise of alphabets of both the cases and digits as well with a length of minimum 5
	@NotEmpty(message="{customer.password.must}")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$",message= "{customer.password.invalid}")
	String password;
	@NotNull(message="{customer.email.must}")
	@Email(message= "{customer.email.invalid}")
	String email;
	@Min(value=18, message = "{customer.age.invalid}")
	@Max(value=60, message = "{customer.age.invalid}")
	int age;
	char gender;
	List<FriendFamilyDTO> friendAndFamily;
	String address;
	@NotNull(message="{customer.plan.must}")
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


