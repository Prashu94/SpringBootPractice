package com.infosys.dto;

public class FriendFamilyDTO {
	
	private long phoneNo;
	private long friendAndFamily;
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
	@Override
	public String toString() {
		return "FriendFamilyDTO [phoneNo=" + phoneNo + ", friendAndFamily=" + friendAndFamily + "]";
	}
	
	
}
