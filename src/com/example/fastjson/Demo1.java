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
	
	//���Դ�������
	public void testCode() {
		String str = "���Ϻ�";
		try {
		System.out.println("����ת���ʽΪ:"+ URLEncoder.encode(str,"UTF-8"));
			URLDecoder.decode("%E6%97%A9%E4%B8%8A%E5%A5%BD", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��json�ı����н���,�õ�json����,�����ڼ�����
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
