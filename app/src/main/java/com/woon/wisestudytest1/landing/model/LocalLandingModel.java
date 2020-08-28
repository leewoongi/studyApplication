package com.woon.wisestudytest1.landing.model;

import android.content.Context;

import com.woon.wisestudytest1.landing.contract.LandingContract;
import com.woon.wisestudytest1.util.TokenManager;

public class LocalLandingModel implements LandingContract.localModel {
    private final static String emptyPres = "NO";
    private LandingContract.presenter presenter;

    public LocalLandingModel() {
    }

    @Override
    public String getJwtSharedPreference(Context context) {
        if(TokenManager.read(context).length() == 0){
            return emptyPres;
        }
        return TokenManager.read(context);
    }
}
