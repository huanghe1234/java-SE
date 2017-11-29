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
	 * 登录行为
	 */
	@Override
	public int logUser() {
		int count = 0;
		while(true) {
			System.out.println("请输入用户帐号:");
			String name = mScanner.next();
			//--根据用户名获取用户对象.	
			System.out.println("集合大小:"+userList.size());
			User loginUser = getUserByName(name);
			if (loginUser == null) {
				System.out.println("账户不存在");
				//--return 还是左什么
				continue;
			}
			if(loginUser.getUserState() == User.State.STATE_LOCK) {
				return FinalData.USER_STATE_LOCK;						
			}else {
				System.out.println("请输入密码.");
				String pwd = mScanner.next();
				if (pwd.equals(loginUser.getUserPwd())) {
					return FinalData.USER_STATE_LOGINOK;
				}else {
					System.out.println("密码错误请重新输入");
					count ++;
				}
				if (count == 2) {
					System.out.println("密码错误次数太多.账户已锁定");
					//--更改用户状态.
					loginUser.setUserState(User.State.STATE_LOCK);					
					return FinalData.USER_STATE_PWD_ERROR;
				}
			}
		}
		
	}


	/**
	 * 根据用户名获取用户对象
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
		System.out.println(">>>>欢迎来到注册界面");
		
		while(true) {
			System.out.println("请输入用户账户");
			account = mScanner.next();
			//--判断账户是否合法
			Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z0-9_.]{6,16}");
			Matcher m = p.matcher(account);
			if(m.matches()) {
				//--验证账户是否存在.			
				if (checkUserAccountExists(account) == FinalData.USER_ACCOUNT_NOT_EXISTS) {
					break;
				}else {
					System.out.println("账户已经存在请重新输入");
				}
			}else{
				System.out.println("输入的账户不合法请按照下面格式来输出");
			}
		}
		
		while(true) {
			System.out.println("请输入用户昵称");
			name = mScanner.next();
			if (!(name == null || name.length() == 0 || name.length() > 16)) {
				break;
			}
		}
		System.out.println("请输入用户密码");
		pwd = mScanner.next();
		System.out.println("请设置密码提示");
		pwdTips = mScanner.next();
	
		//--利用上面所获取的字段信息构建User对象
		User user = new User(account, name, pwd, pwdTips);
		//-- 将User加入到userList中.
		userList.add(user);

	}

	@Override
	public void getAllDVD() {

	}

	/**
	 * 注册是检查用户输入的账户是否存在.
	 * 1.验证参数
	 * 2.对模拟的数据源Resource.userList进行遍历
	 * 3.取每一个User对象和提供的账户进行比较.
	 * 4.如果有存在相同的返回true.否则false
	 * 
	 */
	@Override
	public int checkUserAccountExists(String userAccount) {
		
		//-- 参数有问题.checkArgument返回值是false
		//-- 参数没问题.checkArgument返回值是true
		if(!StringTools.checkArgument(userAccount)){
			return FinalData.ARGUMENT_ERROR;
		}
		
		for (User user : userList) {
			//--如果账户存在返回false
			if (userAccount.equals(user.getUserAccount())) {
				return FinalData.USER_ACCOUNT_EXISTS;
			}
		}
		//--代表账户是不存在的
		return FinalData.USER_ACCOUNT_NOT_EXISTS;
	}

}
