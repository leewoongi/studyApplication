package com.woon.wisestudytest1.login.presenter;

import com.woon.wisestudytest1.login.constract.LoginContract;

public class LoginPresenter implements LoginContract.presenter {
    private LoginContract.view view;
    private LoginContract.model model;


    @Override
    public void onDestroy() {
        view = null;
    }
}
