package com.prospring5.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	public boolean addSillyMember() {
		System.out.println(getClass() + ": DOING STUFF: ADDING A MEMBERSHIP ACCOUNT");
		return true;
	}
}
