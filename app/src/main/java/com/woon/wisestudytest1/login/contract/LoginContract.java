package com.woon.wisestudytest1.login.contract;

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
        void requestLogin(Context context, String accessToken);
    }

    interface localModel{
        //userKey = jwt
        void setJwtSharedPreference(Context context, String userKey);
        String getJwtSharedPreference(Context context);
        void deleteJwtSharedPreference(Context context);

    }

}
