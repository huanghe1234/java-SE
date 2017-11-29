package com.example.data;

import java.util.ArrayList;

import com.hwua.bean.User;

public class Resource {

	/**
	 * 保存所有用户的集合
	 */
	private ArrayList<User> userList;
	/**
	 * 保存当前登录用户的集合.
	 */
	private ArrayList<User> loginUser;
	
	/**
	 * 完成成员变量的实例化操作
	 */
	public Resource() {
		userList = new ArrayList<>();
		loginUser = new ArrayList<>();
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public ArrayList<User> getLoginUser() {
		return loginUser;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
