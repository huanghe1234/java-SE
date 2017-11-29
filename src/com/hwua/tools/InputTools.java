package com.hwua.tools;

import java.util.Scanner;

/*
 * 1.�ṩ˽�еĹ��췽��
 * 2.�ṩ��̬��������ֵΪ��ǰ��.����
 * 3.�ṩ��̬��Ա����.�����ǵ�ǰ������.
 * 4.�ڷ������ṩif�ж�.���ʵ��Ϊnull��new
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
	 * ��ȡScanner����.
	 * @return
	 */
	public /*synchronized*/ Scanner getScanner() {
		if (mScanner == null) {
			mScanner = new Scanner(System.in);
		}
		return mScanner;
	}
	
	
	/**
	 * getInt һ��Ҫ��getScanner֮�����.
	 * @param range ����ȡֵ��Χ
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
					System.out.println("�������ֵ����ȡֵ��Χ[1,"+range+"]����������");				
				}
			} catch (Exception e) {
				System.out.println("��������Ͳ��Ϸ�,����������,ֻ����������!");
				getScanner().next();
			}
		}
		return num;
	}
	
	
	
	
	
}
