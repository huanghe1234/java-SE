package com.hwua.bizImpl;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.data.FinalData;
import com.example.data.Resource;
import com.hwua.bean.User;
import com.hwua.biz.UserBiz;
import com.hwua.tools.InputTools;
import com.hwua.tools.StringTools;

public class UserBizImpl implements UserBiz {	

	private Resource mResource;
	private ArrayList<User> userList;
	private Scanner mScanner;
	private static UserBizImpl mInstance;	
	
	private UserBizImpl() {
		mResource = new Resource();
		userList = mResource.getUserList();
		mScanner = InputTools.getInstance().getScanner();
	}
	
	public static UserBizImpl newInstance() {
		if (mInstance == null) {
			mInstance = new UserBizImpl();
		}
		return mInstance;
	}
	

	/**
	 * ��¼��Ϊ
	 */
	@Override
	public int logUser() {
		int count = 0;
		while(true) {
			System.out.println("�������û��ʺ�:");
			String name = mScanner.next();
			//--�����û�����ȡ�û�����.	
			System.out.println("���ϴ�С:"+userList.size());
			User loginUser = getUserByName(name);
			if (loginUser == null) {
				System.out.println("�˻�������");
				//--return ������ʲô
				continue;
			}
			if(loginUser.getUserState() == User.State.STATE_LOCK) {
				return FinalData.USER_STATE_LOCK;						
			}else {
				System.out.println("����������.");
				String pwd = mScanner.next();
				if (pwd.equals(loginUser.getUserPwd())) {
					return FinalData.USER_STATE_LOGINOK;
				}else {
					System.out.println("�����������������");
					count ++;
				}
				if (count == 2) {
					System.out.println("����������̫��.�˻�������");
					//--�����û�״̬.
					loginUser.setUserState(User.State.STATE_LOCK);					
					return FinalData.USER_STATE_PWD_ERROR;
				}
			}
		}
		
	}


	/**
	 * �����û�����ȡ�û�����
	 * @param name
	 * @return
	 */
	private User getUserByName(String name) {
		
		if (!StringTools.checkArgument(name)) {
			return null;
		}
		for (User user : userList) {
			System.out.println(user);			
			if (user.getUserAccount().equals(name)) {
				return user;
			}
		}
		return null;
	}


	@Override
	public void regerUser() {
		String account = "";
		String name = "";
		String pwd = "";
		String pwdTips = "";
		System.out.println(">>>>��ӭ����ע�����");
		
		while(true) {
			System.out.println("�������û��˻�");
			account = mScanner.next();
			//--�ж��˻��Ƿ�Ϸ�
			Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z0-9_.]{6,16}");
			Matcher m = p.matcher(account);
			if(m.matches()) {
				//--��֤�˻��Ƿ����.			
				if (checkUserAccountExists(account) == FinalData.USER_ACCOUNT_NOT_EXISTS) {
					break;
				}else {
					System.out.println("�˻��Ѿ���������������");
				}
			}else{
				System.out.println("������˻����Ϸ��밴�������ʽ�����");
			}
		}
		
		while(true) {
			System.out.println("�������û��ǳ�");
			name = mScanner.next();
			if (!(name == null || name.length() == 0 || name.length() > 16)) {
				break;
			}
		}
		System.out.println("�������û�����");
		pwd = mScanner.next();
		System.out.println("������������ʾ");
		pwdTips = mScanner.next();
	
		//--������������ȡ���ֶ���Ϣ����User����
		User user = new User(account, name, pwd, pwdTips);
		//-- ��User���뵽userList��.
		userList.add(user);

	}

	@Override
	public void getAllDVD() {

	}

	/**
	 * ע���Ǽ���û�������˻��Ƿ����.
	 * 1.��֤����
	 * 2.��ģ�������ԴResource.userList���б���
	 * 3.ȡÿһ��User������ṩ���˻����бȽ�.
	 * 4.����д�����ͬ�ķ���true.����false
	 * 
	 */
	@Override
	public int checkUserAccountExists(String userAccount) {
		
		//-- ����������.checkArgument����ֵ��false
		//-- ����û����.checkArgument����ֵ��true
		if(!StringTools.checkArgument(userAccount)){
			return FinalData.ARGUMENT_ERROR;
		}
		
		for (User user : userList) {
			//--����˻����ڷ���false
			if (userAccount.equals(user.getUserAccount())) {
				return FinalData.USER_ACCOUNT_EXISTS;
			}
		}
		//--�����˻��ǲ����ڵ�
		return FinalData.USER_ACCOUNT_NOT_EXISTS;
	}

}
