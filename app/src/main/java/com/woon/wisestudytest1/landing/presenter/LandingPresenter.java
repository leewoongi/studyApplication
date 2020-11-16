package com.woon.wisestudytest1.landing.presenter;

import android.content.Context;

import com.woon.wisestudytest1.landing.contract.LandingContract;
import com.woon.wisestudytest1.landing.model.LocalLandingModel;

public class LandingPresenter implements LandingContract.presenter {
    private LandingContract.view view;
    private LocalLandingModel model;

    public LandingPresenter(LandingContract.view view) {
        this.view = view;
        model = new LocalLandingModel();
    }

    @Override
    public String searchUserKey(Context context) {
        String existKey = model.getJwtSharedPreference(context);
        return existKey;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
