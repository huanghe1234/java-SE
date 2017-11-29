package com.example.Json;

import org.json.JSONObject;
/**
 * 
 * @ClassName:   Demo1.java
 * @Package:     com.example.Json
 * @Description: 自己写的家庭作业,天气预报json写法,未完成版
 *
 * @author       Administrator
 * @date         2017年11月29日上午10:38:04
 * @version      1.0
 *
 */

public class Demo1 {
	
	public static void main(String[] args) {
		
		String str1 = "{\"key\":10}";
		JSONObject obj1 = new JSONObject(str1);	
		System.out.println(obj1.getInt("key"));
		
		String str2 = "{\"key\":\"Hello\"}";
		JSONObject obj2 = new JSONObject(str2);
		System.out.println(obj2.getString("key"));
		
		String  st3 = "{\"key\":{\"Innerkey\":\"world\"}}";
		JSONObject obj3 = new JSONObject(st3);
		JSONObject obj = obj3.getJSONObject("key");
		System.out.println(obj.getString("Innerkey"));

	
	}
}
