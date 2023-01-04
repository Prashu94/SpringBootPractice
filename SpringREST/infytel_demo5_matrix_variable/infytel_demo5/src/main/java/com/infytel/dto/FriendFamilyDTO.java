package com.infytel.dto;




public class FriendFamilyDTO {

	long phoneNo;
	long friendAndFamily;

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getFriendAndFamily() {
		return friendAndFamily;
	}

	public void setFriendAndFamily(long friendAndFamily) {
		this.friendAndFamily = friendAndFamily;
	}

	public FriendFamilyDTO(long phoneNo, long friendAndFamily) {
		this();
		this.phoneNo = phoneNo;
		this.friendAndFamily = friendAndFamily;
	}

	public FriendFamilyDTO() {
		super();
	}

	 
	  
	@Override
	public String toString() {
		return "FriendFamilyDTO [phoneNo=" + phoneNo + ", friendAndFamily=" + friendAndFamily + "]";
	}

}
