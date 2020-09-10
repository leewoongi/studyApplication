package com.woon.wisestudytest1.user.user.presenter;

import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.user.user.model.RemoteUserModel;
import com.woon.wisestudytest1.util.SuccessCallback;

public class UserPresenter implements UserContract.presenter, SuccessCallback {
    private UserContract.view view;
    private UserContract.remoteModel remoteModel;

    public UserPresenter(UserContract.view view) {
        this.view = view;
        remoteModel = new RemoteUserModel(this, this);
    }

    @Override
    public void retrieveInformation(String userKey) {
        remoteModel.getUserInformation(userKey);
    }

    @Override
    public void responseUserInformation() {
        view.showInformation();
    }

    @Override
    public void onSuccess() {

    }
}
