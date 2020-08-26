package com.woon.wisestudytest1.login.models;

import android.content.Context;

import com.woon.wisestudytest1.login.constract.LoginContract;
import com.woon.wisestudytest1.util.TokenManager;

public class LocalLoginModel implements LoginContract.localModel {

    public LocalLoginModel() {
        //쉐어드 프리퍼런스 생성
    }

    @Override
    public void setJwtSharedPreference(Context context, String userKey) {
        TokenManager.setPreferences(context, userKey);
    }

    @Override
    public void getJwtSharedPreference(Context context, String userKey) {

    }

    @Override
    public void deleteJwtSharedPreference(Context context, String userKey) {

    }
}
