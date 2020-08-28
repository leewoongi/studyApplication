package com.woon.wisestudytest1.login.constract;

import android.content.Context;

import com.kakao.auth.ISessionCallback;

public interface LoginContract {

    interface view{
        void loginSuccess();
    }

    interface presenter{

        void onDestroy(ISessionCallback callback);
        void startLogin(Context context);
        void getJwt(Context context);
        void deleteJwt(Context context);
    }

    interface remoteModel {
        String requestLogin(String accessToken);
    }

    interface localModel{
        //userKey = jwt
        void setJwtSharedPreference(Context context, String userKey);
        String getJwtSharedPreference(Context context);
        void deleteJwtSharedPreference(Context context);

    }

}
