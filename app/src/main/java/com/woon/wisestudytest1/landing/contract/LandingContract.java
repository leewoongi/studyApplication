package com.woon.wisestudytest1.landing.contract;

import android.content.Context;

import com.kakao.auth.ISessionCallback;

public interface LandingContract {

    interface view {
        void searchUserKey();
        void waitAnimateActivity();
    }

    interface presenter{
        String getJwt(Context context);
        void onDestroy();
    }

    interface localModel{
        String getJwtSharedPreference(Context context);
    }
}
