package com.woon.wisestudytest1.user.modifyuser.presenter;

import android.content.Context;

import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.model.RemoteModifyUserModel;
import com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity;
import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.util.TokenManager;

public class ModifyUserPresenter implements ModifyUserContract.presenter {

    private ModifyUserContract.view view;
    private ModifyUserContract.remoteModel remoteModel;

    private String userKey;

    public ModifyUserPresenter(ModifyUserContract.view view) {
        this.view = view;
        remoteModel = new RemoteModifyUserModel();
    }

    @Override
    public String getJwt(Context context) {
        userKey = TokenManager.read(context);
        return userKey;
    }

    @Override
    public String changeImage() {

        return null;
    }

    @Override
    public void requestModifyUser(String key) {
        remoteModel.putUserInformation(key);
        view.showInformation();
    }
}
