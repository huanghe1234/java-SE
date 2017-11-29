package com.hwua.tools;

public class StringTools {

	/**
	 * �Բ��������.
	 * @param str ��Ϊ��ȷ��Ҫ���ݶ��ٸ�String�������Բ��ÿɱ��.
	 * @return true �������в������Ϸ�.false.ֻҪ��1�����Ϸ����ǲ��ͷ�.
	 */
	public static boolean checkArgument(String... str) {
		
		if (str == null) {
			return false;
		}
		
		for (int i = 0; i < str.length; i++) {
			if (str[i] == null || str[i].length() == 0) {
				return false;
			}
		}
		
		//--���鲻Ϊnull Ԫ�ز�Ϊnull Ԫ�س��Ȳ�Ϊ0
		return true;
	}
}
