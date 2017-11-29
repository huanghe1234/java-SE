package com.hwua.biz;

/**
 * 声明用户要执行的所有行为.
 * 
 * 
 */
public interface UserBiz {

	public int logUser();
	public void regerUser();
	public void getAllDVD();
	
	/**
	 * 验证用户账户是否存在
	 * @param  userAccount 用户提供的账户名称
	 * @return 如果账户已经存在返回true.否则返回false.
	 */
	public int checkUserAccountExists(String userAccount);
	
	
	
	
	
	
	
	
	
	
	
	
}
