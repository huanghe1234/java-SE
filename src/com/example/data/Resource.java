package com.example.data;

import java.util.ArrayList;

import com.hwua.bean.User;

public class Resource {

	/**
	 * ���������û��ļ���
	 */
	private ArrayList<User> userList;
	/**
	 * ���浱ǰ��¼�û��ļ���.
	 */
	private ArrayList<User> loginUser;
	
	/**
	 * ��ɳ�Ա������ʵ��������
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
