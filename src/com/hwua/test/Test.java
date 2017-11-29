package com.hwua.test;

import com.hwua.view.SplashView;
import com.hwua.view.View;

/**
 * 
 * @ClassName  : Test.java
 * @Package    : com.hwua.test
 * @Description: 程序的入口.
 * 
 * @author       Administrator
 * @date         2017年11月16日下午4:51:50
 * 
 * @version      1.0
 *
 */
public class Test {
	
	public static void main(String[] args) {
		
		View view = new SplashView();
		/*
		 * ? 思考:在做界面跳转时,是否需要把一些内容传递到第二个界面.
		 * 如果需要以什么样的方式来传递.
		 */
		while(view != null) {
			view = view.showView();
		}
		System.out.println("欢迎下次再来");
	}

}
