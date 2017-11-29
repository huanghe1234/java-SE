package com.hwua.view;

import java.util.Scanner;
import com.hwua.biz.UserBiz;
import com.hwua.bizImpl.UserBizImpl;
import com.hwua.tools.InputTools;

/**
 * ×¢²á½çÃæ.
 * @author Administrator
 *
 */
public class RegisterView extends View {
	
	UserBiz mUserBiz;
	Scanner mScanner;
	
	public RegisterView() {
		mUserBiz = UserBizImpl.newInstance();
		mScanner = InputTools.getInstance().getScanner();
	}

	@Override
	public View showView() {
		mUserBiz.regerUser();		
		return new SplashView();
	}

}
