package com.woon.wisestudytest1.landing.presenter;

import android.content.Context;
import android.preference.PreferenceManager;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.landing.contract.LandingContract;
import com.woon.wisestudytest1.landing.model.LocalLandingModel;

public class LandingPresenter implements LandingContract.presenter {
    private LandingContract.view view;
    private LandingContract.localModel model;

    public LandingPresenter(LandingContract.view view) {
        this.view = view;
        model = new LocalLandingModel();
    }

    @Override
    public String getJwt(Context context) {
        String existKey = model.getJwtSharedPreference(context);
        return existKey;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
