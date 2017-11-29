package com.hwua.tools;

public class StringTools {

	/**
	 * 对参数做检查.
	 * @param str 因为不确定要传递多少个String进来所以采用可变参.
	 * @return true 代表所有参数都合法.false.只要有1个不合法就是不和法.
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
		
		//--数组不为null 元素不为null 元素长度不为0
		return true;
	}
}
