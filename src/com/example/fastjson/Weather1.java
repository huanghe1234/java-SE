package com.example.fastjson;

import java.io.Serializable;

public class Weather1 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1930735396344982887L;
	/**
	 * 提供序列化ID
	 */
	
	
	private int weaid;
	private String days;
	private String week;
	private String cityno;
	private String citynm;
	private int cityid;
	private String temperature;
	private String humidity;
	private String weather;
	private String wind;
	private String winp;
	private int temp_high;
	private int temp_low;
	private int humi_high;
	private int humi_low;
	private int weatid;
	private int weatid1;
	private int windid;
	private int winpid;
	
	

	public int getWeaid() {
		return weaid;
	}

	public void setWeaid(int weaid) {
		this.weaid = weaid;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getCityno() {
		return cityno;
	}

	public void setCityno(String cityno) {
		this.cityno = cityno;
	}

	public String getCitynm() {
		return citynm;
	}

	public void setCitynm(String citynm) {
		this.citynm = citynm;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWinp() {
		return winp;
	}

	public void setWinp(String winp) {
		this.winp = winp;
	}

	public int getTemp_high() {
		return temp_high;
	}

	public void setTemp_high(int temp_high) {
		this.temp_high = temp_high;
	}

	public int getTemp_low() {
		return temp_low;
	}

	public void setTemp_low(int temp_low) {
		this.temp_low = temp_low;
	}

	public int getHumi_high() {
		return humi_high;
	}

	public void setHumi_high(int humi_high) {
		this.humi_high = humi_high;
	}

	public int getHumi_low() {
		return humi_low;
	}

	public void setHumi_low(int humi_low) {
		this.humi_low = humi_low;
	}

	public int getWeatid() {
		return weatid;
	}

	public void setWeatid(int weatid) {
		this.weatid = weatid;
	}

	public int getWeatid1() {
		return weatid1;
	}

	public void setWeatid1(int weatid1) {
		this.weatid1 = weatid1;
	}

	public int getWindid() {
		return windid;
	}

	public void setWindid(int windid) {
		this.windid = windid;
	}

	public int getWinpid() {
		return winpid;
	}

	public void setWinpid(int winpid) {
		this.winpid = winpid;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cityid;
		result = prime * result + ((citynm == null) ? 0 : citynm.hashCode());
		result = prime * result + ((cityno == null) ? 0 : cityno.hashCode());
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result + humi_high;
		result = prime * result + humi_low;
		result = prime * result + ((humidity == null) ? 0 : humidity.hashCode());
		result = prime * result + temp_high;
		result = prime * result + temp_low;
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + weaid;
		result = prime * result + ((weather == null) ? 0 : weather.hashCode());
		result = prime * result + weatid;
		result = prime * result + weatid1;
		result = prime * result + ((week == null) ? 0 : week.hashCode());
		result = prime * result + ((wind == null) ? 0 : wind.hashCode());
		result = prime * result + windid;
		result = prime * result + ((winp == null) ? 0 : winp.hashCode());
		result = prime * result + winpid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather1 other = (Weather1) obj;
		if (cityid != other.cityid)
			return false;
		if (citynm == null) {
			if (other.citynm != null)
				return false;
		} else if (!citynm.equals(other.citynm))
			return false;
		if (cityno == null) {
			if (other.cityno != null)
				return false;
		} else if (!cityno.equals(other.cityno))
			return false;
		if (days == null) {
			if (other.days != null)
				return false;
		} else if (!days.equals(other.days))
			return false;
		if (humi_high != other.humi_high)
			return false;
		if (humi_low != other.humi_low)
			return false;
		if (humidity == null) {
			if (other.humidity != null)
				return false;
		} else if (!humidity.equals(other.humidity))
			return false;
		if (temp_high != other.temp_high)
			return false;
		if (temp_low != other.temp_low)
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		if (weaid != other.weaid)
			return false;
		if (weather == null) {
			if (other.weather != null)
				return false;
		} else if (!weather.equals(other.weather))
			return false;
		if (weatid != other.weatid)
			return false;
		if (weatid1 != other.weatid1)
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		if (wind == null) {
			if (other.wind != null)
				return false;
		} else if (!wind.equals(other.wind))
			return false;
		if (windid != other.windid)
			return false;
		if (winp == null) {
			if (other.winp != null)
				return false;
		} else if (!winp.equals(other.winp))
			return false;
		if (winpid != other.winpid)
			return false;
		return true;
	}

	public Weather1(int weaid, String days, String week, String cityno, String citynm, int cityid, String temperature,
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

	public Weather1() {
	}

	@Override
	public String toString() {
		return "Weather [weaid=" + weaid + ", days=" + days + ", week=" + week + ", cityno=" + cityno + ", citynm="
				+ citynm + ", cityid=" + cityid + ", temperature=" + temperature + ", humidity=" + humidity
				+ ", weather=" + weather + ", wind=" + wind + ", winp=" + winp + ", temp_high=" + temp_high
				+ ", temp_low=" + temp_low + ", humi_high=" + humi_high + ", humi_low=" + humi_low + ", weatid="
				+ weatid + ", weatid1=" + weatid1 + ", windid=" + windid + ", winpid=" + winpid + "]\n";
	}

	
}
