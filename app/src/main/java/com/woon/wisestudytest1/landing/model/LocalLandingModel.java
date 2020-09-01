package com.woon.wisestudytest1.landing.model;

import android.content.Context;

import com.woon.wisestudytest1.landing.contract.LandingContract;
import com.woon.wisestudytest1.util.TokenManager;

public class LocalLandingModel {
    private final static String emptyPref = null;
    private LandingContract.presenter presenter;

    public LocalLandingModel() {
    }

    public String getJwtSharedPreference(Context context) {
        if(TokenManager.read(context).length() == 0){
            return emptyPref;
        }
        return TokenManager.read(context);
    }
}
