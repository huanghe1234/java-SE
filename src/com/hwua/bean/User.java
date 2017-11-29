package com.hwua.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName  : User.java
 * @Package    : com.hwua.bean
 * @Description: TODO
 * 
 * @author       Administrator
 * @date         2017��11��16������4:47:02
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
	
	//--ö��.�����û���״̬.
	public enum State{
		STATE_OK,//--�˻�����
		STATE_BLACK,//--����
		STATE_LOCK,//--�˻����������������3��
		STATE_DEL;//--��ע���˻�.
	}
	
	public int getUserId() {
		return userId;
	}
	/**
	 * ��ΪID��ͨ�����ݿ��Զ������õ���.
	 * �����ʹ�����ݿ�.���Բ��ṩset����.
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
	 * �вι���
	 * @param userId         ��Ӧ���ݿ�User���е�����.��ˮ��
	 * @param userAccount    ��Ӧ�û��˻�.����QQ��
	 * @param userName       ��Ӧ�û�����.����QQ�ǳ�
	 * @param userPwd
	 * @param userPwdTips    ��Ӧ�û�������ʾ
	 * @param userPoint      ��Ӧ�û�����
	 * @param userVIPLevel   ��Ӧ�û�VIP�ȼ�
	 * @param userState      ��Ӧ�û���״̬<@code State> 
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
	 * �ṩȱʡ����
	 */
	public User() {
		super();
	}
	
	/**
	 * Ϊ�˷����User��������.
	 * 		Ĭ��������User��VIP�ȼ���������
	 */
	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.getUserVIPLevel() - o.getUserVIPLevel();
	}
	
	/**
	 * ��ӡ�û�����Ϣ.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount + ", userName=" + userName + ", userPwd="
				+ userPwd + ", userPwdTips=" + userPwdTips + ", userPoint=" + userPoint + ", userVIPLevel="
				+ userVIPLevel + ", userState=" + userState + "]";
	}
	
	
	
	
}
