package com.hwua.ttest;

import java.util.ArrayList;

import com.hwua.bean.User;

public class Demo02 {
	
	public static void main(String[] args) {
		
		Demo01 d = new Demo01();
		ArrayList<User> list = d.getUserList();
		System.out.println(list.size());
		list.remove(0);
		System.out.println(list.size());
		
	}

}
