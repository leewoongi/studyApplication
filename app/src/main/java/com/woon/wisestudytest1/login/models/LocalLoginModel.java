package com.woon.wisestudytest1.login.models;

import android.content.Context;

import com.woon.wisestudytest1.login.contract.LoginContract;
import com.woon.wisestudytest1.util.TokenManager;

public class LocalLoginModel implements LoginContract.localModel {

    private LoginContract.presenter presenter;

    public LocalLoginModel(LoginContract.presenter presenter) {
        //쉐어드 프리퍼런스 생성
        this.presenter = presenter;
    }

    @Override
    public void setJwtSharedPreference(Context context, String userKey) {
        TokenManager.save(context, userKey);
        presenter.checkJwt();
    }

    @Override
    public String getJwtSharedPreference(Context context) {
        return TokenManager.read(context);
    }

    @Override
    public void deleteJwtSharedPreference(Context context) {
        TokenManager.remove(context);
    }
}
