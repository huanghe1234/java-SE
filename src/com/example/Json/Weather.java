package com.example.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Weather {
	int weaid;
	String days;
	String week;
	String cityno;
	String citynm;
	int cityid;
	String temperature;
	String humidity;
	String weather;
	String wind;
	String winp;
	int temp_high;
	int temp_low;
	int humi_high;
	int humi_low;
	int weatid;
	int weatid1;
	int windid;
	int winpid;

	public Weather(int weaid, String days, String week, String cityno, String citynm, int cityid, String temperature,
			String humidity, String weather, String wind, String winp, int temp_high, int temp_low, int humi_high,
			int humi_low, int weatid, int weatid1, int windid, int winpid) {
		this.weaid = weaid;
		this.days = days;
		this.week = week;
		this.cityno = cityno;
		this.citynm = citynm;
		this.cityid = cityid;
		this.temperature = temperature;
		this.humidity = humidity;
		this.weather = weather;
		this.wind = wind;
		this.winp = winp;
		this.temp_high = temp_high;
		this.temp_low = temp_low;
		this.humi_high = humi_high;
		this.humi_low = humi_low;
		this.weatid = weatid;
		this.weatid1 = weatid1;
		this.windid = windid;
		this.winpid = winpid;
	}

	public Weather() {
	}

	@Override
	public String toString() {
		return "Weather [weaid=" + weaid + ", days=" + days + ", week=" + week + ", cityno=" + cityno + ", citynm="
				+ citynm + ", cityid=" + cityid + ", temperature=" + temperature + ", humidity=" + humidity
				+ ", weather=" + weather + ", wind=" + wind + ", winp=" + winp + ", temp_high=" + temp_high
				+ ", temp_low=" + temp_low + ", humi_high=" + humi_high + ", humi_low=" + humi_low + ", weatid="
				+ weatid + ", weatid1=" + weatid1 + ", windid=" + windid + ", winpid=" + winpid + "]\n";
	}

	public static void main(String[] args) throws IOException {
		
		//读取json格式文本文档
		File file = new File("resource\\weather.txt");
		Reader read = new FileReader(file);
		BufferedReader br = new BufferedReader(read);
		String line = "";
		StringBuilder sBuilder = new StringBuilder();
		while ((line = br.readLine()) != null) {
			String str = new String(line.trim().getBytes(), "GBK");
			sBuilder.append(str);
		}
		
		//把json格式文档的内容保存到一行中 
		line = sBuilder.toString();
		
		ArrayList<Weather> list = new ArrayList<>();
		//利用json方法把json文档把进行分割,存到数组里面
		JSONObject objAll = new JSONObject(line);
		JSONArray jsonArray = objAll.getJSONArray("result");  //得到的是JSON数组
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject temp = jsonArray.getJSONObject(i); //i为JSONArray的下标
			Weather w = new Weather(
					temp.getInt("weaid"), 
					temp.getString("days"), 
					temp.getString("week"),
					temp.getString("cityno"), 
					temp.getString("citynm"), 
					temp.getInt("cityid"),
					temp.getString("temperature"), 
					temp.getString("humidity"), 
					temp.getString("weather"),
					temp.getString("wind"), 
					temp.getString("winp"), 
					temp.getInt("temp_high"),
					temp.getInt("temp_low"), 
					temp.getInt("humi_high"),
					temp.getInt("humi_low"),
					temp.getInt("weatid"), 
					temp.getInt("weatid1"), 
					temp.getInt("windid"),
					temp.getInt("winpid"));
			list.add(w);
		}
		System.out.println(list);
	}
}
