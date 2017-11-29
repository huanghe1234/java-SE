package com.hwua.view;

import com.hwua.tools.InputTools;

/**
 * 
 * @ClassName : MainView.java
 * @Package : com.hwua.view
 * @Description: TODO
 * 
 * @author Administrator
 * @date 2017��11��16������4:49:52
 * 
 * @version 1.0
 *
 */
public class SplashView extends View {
	private static final int MEN_NUM_EXIT = 3;
	private static final int MEN_NUM_LOGIN = 1;
	private static final int MEN_NUM_REGISTER = 2;

	private View mView;//--��Ա����һ����m��ͷ.

	/**
	 * ��ʾ��ӭ����.
	* @Title: showWelcome 
	* @Description: TODO(������һ�仰�����������������) 
	* @param   ����˵�� 
	* @return void    �������� 
	* @throws
	 */
	public void showWelcome() {
		for (int i = 0; i < 100; i++) {
			System.out.print("*");
			if (i == 49) {
				System.out.println("\n");
				System.out.println("\t\t��ӭ����DVD����ϵͳ");
				System.out.println("\n");
			}
		}
	}
	
	/**
	 * 
	* @Title: showSplashMenu 
	* @Description: ��ʾ�˵� 
	* @param   ����˵�� 
	* @return void    �������� 
	* @throws
	 */
	public void showSplashMenu() {
		System.out.println("1.��¼");
		System.out.println("2.ע��");
		System.out.println("3.�˳�");
		
		InputTools it = InputTools.getInstance();
		System.out.println("Enter Choose");
		switch (it.getInt(3)) {
		//--�ѹ̶�������ֵ��չ�ɳ���.
		case MEN_NUM_LOGIN:			
			mView = new LoginView();
			break;
		case MEN_NUM_REGISTER:
			mView = new RegisterView();
			break;
		case MEN_NUM_EXIT:
			/*
			 * ǿ���˳�ϵͳ.
			 * ����д�������Ƽ���Ϊ��ǿ���˳�,û�л��ᱣ�����ݵ�.
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
	 * Description: ��ʾ������.
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
