package com.hwua.ttest;

import java.util.ArrayList;

import com.hwua.bean.User;

public class Demo01 {
	
	private ArrayList<User> userList;
	
	public Demo01() {
		userList = new ArrayList<>();
		userList.add(new User("123","123","123", "123"));
	}

	public ArrayList<User> getUserList() {
		return userList;
	}
	
	

}
