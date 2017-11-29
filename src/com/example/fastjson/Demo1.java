package com.example.fastjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @ClassName:   Demo1.java
 * @Package:     com.example.fastjson
 * @Description: ��ҳ������Ԥ������
 *
 * @author       Administrator
 * @date         2017��11��29������7:07:50
 * @version      1.0
 *
 */
public class Demo1 {

	//���Դ�������
	public void testCode() {
		String str = "���Ϻ�";
		try {
		System.out.println("�����Ϊ:" + URLEncoder.encode(str,"UTF-8"));
		System.out.println("�����Ϊ:" + URLDecoder.decode("%E6%97%A9%E4%B8%8A%E5%A5%BD", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ͨ�����������ȡJSON�ı�
	public String getJsontext (String url) throws UnsupportedEncodingException, IOException {
		
		if(url == null || url.length() == 0 ) {
			return null;
		}
		
			URL u = new URL(url);
			BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream(),"UTF-8")); 
			StringBuilder sBuilder = new StringBuilder();
			String line = "";
			while (null != (line = br.readLine())) {
				sBuilder.append(line);
			}	
			// System.out.println("�õ���json�ı�Ϊ:"+ sBuilder.toString());
		return sBuilder.toString();
		
	}
	
	
	
	//��JSON�ı����н���,�õ�JSON����,�����ڼ�����
	public List<Weather> parserJson(String jsonText) {
		
		ArrayList<Weather> list = new ArrayList<>();
		if(jsonText == null || jsonText.length()==0) {
			return null;
		}
		
		JSONObject json = JSONObject.parseObject(jsonText);
		 //System.out.println(json);
		JSONArray array = json.getJSONArray("result");
		 //System.out.println("����Ϊ:"+json.getJSONArray("result"));
		
		for(int i = 0 ; i < array.size();i ++) {			
			Weather w = JSONObject.toJavaObject(array.getJSONObject(i), Weather.class );
			list.add(w);
			
		}
	
		return list;
	}	
	
	
	public static void main(String[] args) {
		Demo1 d = new Demo1();
		try {
			String json = d.getJsontext("http://api.k780.com/?app=weather.future&weaid=36&appkey=12897&sign=ad041abb874869a9764b5891234459b3&format=json");
			List<Weather> list = d.parserJson(json);
			
			for (Weather w1 : list) {
				System.out.println(w1);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
		
}
