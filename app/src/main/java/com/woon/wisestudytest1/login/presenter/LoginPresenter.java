package com.woon.wisestudytest1.login.presenter;

import android.content.Context;
import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.woon.wisestudytest1.login.contract.LoginContract;
import com.woon.wisestudytest1.login.models.LocalLoginModel;
import com.woon.wisestudytest1.login.models.RemoteLoginModel;

public class LoginPresenter implements LoginContract.presenter{

    private LoginContract.view view;
    private LoginContract.remoteModel remoteModel;
    private LoginContract.localModel localModel;
    public LoginPresenter(LoginContract.view view) {
        this.view = view;
        remoteModel = new RemoteLoginModel(this);
        localModel = new LocalLoginModel(this);
    }

    //카카오 서버와 통신이 됬다면 로그인 실행 후 카카오토큰 발급
    @Override
    public void startLogin(Context mContext) {
        remoteModel.requestLogin(mContext);
    }


    // jwt 키값의 유무로 이동 페이지가 달라지기 때문에 테스트 용도로 만듬
    @Override
    public String getJwt(Context context) {
        String value = localModel.getJwtSharedPreference(context);
        Log.d("JWT" , value);
        return value;
    }

    @Override
    public void deleteJwt(Context context) {
        localModel.deleteJwtSharedPreference(context);
    }

    @Override
    // jwt 키값을 받아왔으면 SharedPreference 저장
    public void setJwtOnSuccess(Context mContext, String userKey) {
        localModel.setJwtSharedPreference(mContext, userKey);
    }
    @Override
    public void checkJwt() {
        view.loginSuccess();
    }

    @Override
    public void onDestroy(ISessionCallback callback) {
        view = null;
        Session.getCurrentSession().removeCallback(callback);
    }

}
