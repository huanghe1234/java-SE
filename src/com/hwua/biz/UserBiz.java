package com.hwua.biz;

/**
 * �����û�Ҫִ�е�������Ϊ.
 * 
 * 
 */
public interface UserBiz {

	public int logUser();
	public void regerUser();
	public void getAllDVD();
	
	/**
	 * ��֤�û��˻��Ƿ����
	 * @param  userAccount �û��ṩ���˻�����
	 * @return ����˻��Ѿ����ڷ���true.���򷵻�false.
	 */
	public int checkUserAccountExists(String userAccount);
	
	
	
	
	
	
	
	
	
	
	
	
}
