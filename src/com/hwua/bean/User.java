package com.hwua.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName  : User.java
 * @Package    : com.hwua.bean
 * @Description: TODO
 * 
 * @author       Administrator
 * @date         2017年11月16日下午4:47:02
 * 
 * @version      1.0
 *
 */
public class User implements Serializable,Comparable<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -226152939252489L;
	private int userId;
	private String userAccount;
	private String userName;
	private String userPwd;
	private String userPwdTips;
	private int userPoint;
	private int userVIPLevel;
	private State userState;
	
	//--枚举.定义用户的状态.
	public enum State{
		STATE_OK,//--账户正常
		STATE_BLACK,//--老赖
		STATE_LOCK,//--账户被锁比如密码错误3次
		STATE_DEL;//--已注销账户.
	}
	
	public int getUserId() {
		return userId;
	}
	/**
	 * 因为ID是通过数据库自动增长得到的.
	 * 如果是使用数据库.可以不提供set方法.
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	public int getUserVIPLevel() {
		return userVIPLevel;
	}
	public void setUserVIPLevel(int userVIPLevel) {
		this.userVIPLevel = userVIPLevel;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPwdTips() {
		return userPwdTips;
	}
	public void setUserPwdTips(String userPwdTips) {
		this.userPwdTips = userPwdTips;
	}
	public State getUserState() {
		return userState;
	}
	public void setUserState(State userState) {
		this.userState = userState;
	}
	/**
	 * 有参构造
	 * @param userId         对应数据库User表中的主键.流水号
	 * @param userAccount    对应用户账户.比如QQ号
	 * @param userName       对应用户姓名.比如QQ昵称
	 * @param userPwd
	 * @param userPwdTips    对应用户密码提示
	 * @param userPoint      对应用户积分
	 * @param userVIPLevel   对应用户VIP等级
	 * @param userState      对应用户的状态<@code State> 
	 * 				
	 */
	public User(int userId, String userAccount, String userName, String userPwd, String userPwdTips, int userPoint,
			int userVIPLevel, State userState) {
		this.userId = userId;
		this.userAccount = userAccount;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userPwdTips = userPwdTips;
		this.userPoint = userPoint;
		this.userVIPLevel = userVIPLevel;
		this.userState = userState;
	}	
	
	public User(String userAccount, String userName, String userPwd, String userPwdTips, int userPoint,
			int userVIPLevel, State userState) {
		this.userAccount = userAccount;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userPwdTips = userPwdTips;
		this.userPoint = userPoint;
		this.userVIPLevel = userVIPLevel;
		this.userState = userState;
	}
	
	
	
	public User(String userAccount,String userName, String userPwd, String userPwdTips) {
		this(userAccount, userName, userPwd, userPwdTips, 0, 0, User.State.STATE_OK);
	}
	/**
	 * 提供缺省构造
	 */
	public User() {
		super();
	}
	
	/**
	 * 为了方便对User进行排序.
	 * 		默认排序按照User的VIP等级进行排序
	 */
	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.getUserVIPLevel() - o.getUserVIPLevel();
	}
	
	/**
	 * 打印用户的信息.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount + ", userName=" + userName + ", userPwd="
				+ userPwd + ", userPwdTips=" + userPwdTips + ", userPoint=" + userPoint + ", userVIPLevel="
				+ userVIPLevel + ", userState=" + userState + "]";
	}
	
	
	
	
}
