package com.hwua.tools;

import java.util.Scanner;

/*
 * 1.提供私有的构造方法
 * 2.提供静态方法返回值为当前类.对象
 * 3.提供静态成员变量.类型是当前类类型.
 * 4.在方法中提供if判断.如果实例为null则new
 */
public class InputTools {
	
	private static InputTools instance;
	private static Scanner mScanner;
	private InputTools() {}	
	public static InputTools getInstance() {
		if (instance == null) {
			instance = new InputTools();
		}
		return instance;
	}
	
	
	/**
	 * 获取Scanner对象.
	 * @return
	 */
	public /*synchronized*/ Scanner getScanner() {
		if (mScanner == null) {
			mScanner = new Scanner(System.in);
		}
		return mScanner;
	}
	
	
	/**
	 * getInt 一定要在getScanner之后调用.
	 * @param range 代表取值范围
	 * @return
	 */
	public int getInt(int range) {
		int num = 0;
		while (true) {
			try {
				num = getScanner().nextInt();
				if (num > 0  && num <= range) {
					break;
				}else {
					System.out.println("所输入的值不在取值范围[1,"+range+"]请重新输入");				
				}
			} catch (Exception e) {
				System.out.println("输入的类型不合法,请重新输入,只可以是数字!");
				getScanner().next();
			}
		}
		return num;
	}
	
	
	
	
	
}
