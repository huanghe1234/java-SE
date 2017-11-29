package com.example.json;

import org.json.JSONObject;

public class Demo1 {
	public static void main(String[] args) {
				
		String str1 = "{\"key\":10}";
		JSONObject obj1 = new JSONObject(str1);	
		System.out.println(obj1.getInt("key"));
		
		String str2 = "{\"key\":\"Hello\"}";
		JSONObject obj2 = new JSONObject(str2);
		System.out.println(obj2.getString("key"));
	
	}
	

	
}
