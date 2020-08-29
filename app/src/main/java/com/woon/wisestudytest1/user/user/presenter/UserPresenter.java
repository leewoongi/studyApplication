package com.woon.wisestudytest1.user.user.presenter;

import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.user.user.view.UserActivity;

public class UserPresenter implements UserContract.presenter {
    private UserContract.view view;

    public UserPresenter(UserContract.view view) {
        this.view = view;
    }

    @Override
    public void demandPicture() {

    }

    @Override
    public void demandInformation() {

    }
}
