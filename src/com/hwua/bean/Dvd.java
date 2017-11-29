package com.hwua.bean;

/**
 * 
 * @ClassName  : Dvd.java
 * @Package    : com.hwua.bean
 * @Description: TODO
 * 
 * @author       Administrator
 * @date         2017年11月16日下午4:46:55
 * 
 * @version      1.0
 *
 */
public class Dvd {
	
	private int dvdId;
	private String dvdName;
	private int dvdLendCount;
	private String dvdLendDate;
	private boolean dvdStatus;
	public int getDvdId() {
		return dvdId;
	}
	public void setDvdId(int dvdId) {
		this.dvdId = dvdId;
	}
	public String getDvdName() {
		return dvdName;
	}
	public void setDvdName(String dvdName) {
		this.dvdName = dvdName;
	}
	public int getDvdLendCount() {
		return dvdLendCount;
	}
	public void setDvdLendCount(int dvdLendCount) {
		this.dvdLendCount = dvdLendCount;
	}
	public String getDvdLendDate() {
		return dvdLendDate;
	}
	public void setDvdLendDate(String dvdLendDate) {
		this.dvdLendDate = dvdLendDate;
	}
	public boolean isDvdStatus() {
		return dvdStatus;
	}
	public void setDvdStatus(boolean dvdStatus) {
		this.dvdStatus = dvdStatus;
	}
	public Dvd(int dvdId, String dvdName, int dvdLendCount, String dvdLendDate, boolean dvdStatus) {
		super();
		this.dvdId = dvdId;
		this.dvdName = dvdName;
		this.dvdLendCount = dvdLendCount;
		this.dvdLendDate = dvdLendDate;
		this.dvdStatus = dvdStatus;
	}
	public Dvd() {
		super();
	}
	@Override
	public String toString() {
		return "DVD [dvdId=" + dvdId + ", dvdName=" + dvdName + ", dvdLendCount=" + dvdLendCount + ", dvdLendDate="
				+ dvdLendDate + ", dvdStatus=" + dvdStatus + "]";
	}
	
	
	

}
