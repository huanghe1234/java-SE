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
 * @Description: 网页版天气预报界面
 *
 * @author       Administrator
 * @date         2017年11月29日下午7:07:50
 * @version      1.0
 *
 */
public class Demo1 {

	//测试代码问题
	public void testCode() {
		String str = "早上好";
		try {
		System.out.println("编码后为:" + URLEncoder.encode(str,"UTF-8"));
		System.out.println("解码后为:" + URLDecoder.decode("%E6%97%A9%E4%B8%8A%E5%A5%BD", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//通过网络请求获取JSON文本
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
			// System.out.println("得到的json文本为:"+ sBuilder.toString());
		return sBuilder.toString();
		
	}
	
	
	
	//对JSON文本进行解析,得到JSON对象,并放在集合中
	public List<Weather> parserJson(String jsonText) {
		
		ArrayList<Weather> list = new ArrayList<>();
		if(jsonText == null || jsonText.length()==0) {
			return null;
		}
		
		JSONObject json = JSONObject.parseObject(jsonText);
		 //System.out.println(json);
		JSONArray array = json.getJSONArray("result");
		 //System.out.println("集合为:"+json.getJSONArray("result"));
		
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
