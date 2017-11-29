package com.hwua.view;

import com.hwua.tools.InputTools;

/**
 * 
 * @ClassName : MainView.java
 * @Package : com.hwua.view
 * @Description: TODO
 * 
 * @author Administrator
 * @date 2017年11月16日下午4:49:52
 * 
 * @version 1.0
 *
 */
public class SplashView extends View {
	private static final int MEN_NUM_EXIT = 3;
	private static final int MEN_NUM_LOGIN = 1;
	private static final int MEN_NUM_REGISTER = 2;

	private View mView;//--成员变量一般以m开头.

	/**
	 * 显示欢迎界面.
	* @Title: showWelcome 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param   参数说明 
	* @return void    返回类型 
	* @throws
	 */
	public void showWelcome() {
		for (int i = 0; i < 100; i++) {
			System.out.print("*");
			if (i == 49) {
				System.out.println("\n");
				System.out.println("\t\t欢迎来到DVD管理系统");
				System.out.println("\n");
			}
		}
	}
	
	/**
	 * 
	* @Title: showSplashMenu 
	* @Description: 显示菜单 
	* @param   参数说明 
	* @return void    返回类型 
	* @throws
	 */
	public void showSplashMenu() {
		System.out.println("1.登录");
		System.out.println("2.注册");
		System.out.println("3.退出");
		
		InputTools it = InputTools.getInstance();
		System.out.println("Enter Choose");
		switch (it.getInt(3)) {
		//--把固定不会变的值扩展成常量.
		case MEN_NUM_LOGIN:			
			mView = new LoginView();
			break;
		case MEN_NUM_REGISTER:
			mView = new RegisterView();
			break;
		case MEN_NUM_EXIT:
			/*
			 * 强制退出系统.
			 * 这种写法并不推荐因为是强制退出,没有机会保存数据的.
			 * 
			 */
			System.exit(0);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * Title: showView
	 * Description: 显示主界面.
	 * 
	 * @return
	 * @see com.hwua.view.View#showView()
	 */
	@Override
	public View showView() {
		showWelcome();
		System.out.println();
		showSplashMenu();
		return mView;
	}

}
