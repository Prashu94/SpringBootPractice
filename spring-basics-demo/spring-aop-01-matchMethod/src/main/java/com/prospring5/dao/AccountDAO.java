package com.prospring5.dao;

import org.springframework.stereotype.Component;

import com.prospring5.Account;

@Component
public class AccountDAO {
	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
}
