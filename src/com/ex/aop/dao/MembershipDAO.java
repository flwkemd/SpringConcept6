package com.ex.aop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

//	public void addAccount() {
//	public void addMember() {
	public boolean addMember() {
		System.out.println(getClass()+": Doing Stuff : Adding a membership account");
		
		return true;
	}
	
}
