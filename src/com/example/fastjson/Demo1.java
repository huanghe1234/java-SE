package com.example.fastjson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.example.Json.Weather;

public class Demo1 {
	
	//测试代码问题
	public void testCode() {
		String str = "早上好";
		try {
		System.out.println("设置转码格式为:"+ URLEncoder.encode(str,"UTF-8"));
			URLDecoder.decode("%E6%97%A9%E4%B8%8A%E5%A5%BD", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//对json文本进行解析,得到json对象,并放在集合中
	public List<Weather> parserJson(String jsonText) {
		List<Weather> list = new ArrayList<>();
		if(jsonText == null || jsonText.length()==0) {
			return null;
		}
		
		return list;
	}
	
	
	
	
	
	public static void main(String[] args) {
	
		
	}
}
