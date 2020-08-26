package com.woon.wisestudytest1.login.constract;

import android.content.Context;

import com.kakao.auth.ISessionCallback;

public interface LoginContract {

    interface view{
        void loginSuccess();
    }

    interface presenter{
        ISessionCallback checkLogin();
        void onDestroy();
    }

    interface remoteModel {
        String requestLogin(String accessToken);
    }

    interface localModel{
        //userKey = jwt
        void setJwtSharedPreference(Context context, String userKey);
        void getJwtSharedPreference(Context context, String userKey);
        void deleteJwtSharedPreference(Context context, String userKey);

    }

}
