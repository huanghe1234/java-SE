package com.hwua.view;



import com.example.data.FinalData;
import com.hwua.biz.UserBiz;
import com.hwua.bizImpl.UserBizImpl;

public class LoginView extends View {

	private UserBiz mUserBiz;
	private View mView;

	public LoginView() {	
		mUserBiz = UserBizImpl.newInstance();
	}

	@Override
	public View showView() {
		switch (mUserBiz.logUser()) {
		case FinalData.USER_STATE_LOCK:
			System.out.println("�˻��ѱ�����,��ϵ�������");
			break;
		case FinalData.USER_STATE_LOGINOK:
			System.out.println("��¼�ɹ�!");
			mView = new MainView();
			break;
		case FinalData.USER_STATE_PWD_ERROR:
			System.out.println("����������̫��,�˻��ѱ�����,��ϵ�������");
			break;
		default:
			break;
		}
		;
		return mView;
	}

}
