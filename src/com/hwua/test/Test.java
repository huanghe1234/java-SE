package com.hwua.test;

import com.hwua.view.SplashView;
import com.hwua.view.View;

/**
 * 
 * @ClassName  : Test.java
 * @Package    : com.hwua.test
 * @Description: ��������.
 * 
 * @author       Administrator
 * @date         2017��11��16������4:51:50
 * 
 * @version      1.0
 *
 */
public class Test {
	
	public static void main(String[] args) {
		
		View view = new SplashView();
		/*
		 * ? ˼��:����������תʱ,�Ƿ���Ҫ��һЩ���ݴ��ݵ��ڶ�������.
		 * �����Ҫ��ʲô���ķ�ʽ������.
		 */
		while(view != null) {
			view = view.showView();
		}
		System.out.println("��ӭ�´�����");
	}

}
