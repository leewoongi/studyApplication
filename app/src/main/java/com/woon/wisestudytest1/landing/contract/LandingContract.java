package com.woon.wisestudytest1.landing.contract;

import android.content.Context;

import com.kakao.auth.ISessionCallback;

public interface LandingContract {

    interface view {
        void waitAnimateActivity();
        void checkUserKey();
    }

    interface presenter{
        String searchUserKey(Context context);
        void onDestroy();
    }
}
