package com.woon.wisestudytest1.user.modifyuser.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.model.LocalModifyUserModel;
import com.woon.wisestudytest1.user.modifyuser.model.RemoteModifyUserModel;
import com.woon.wisestudytest1.util.TokenManager;

public class ModifyUserPresenter implements ModifyUserContract.presenter {

    private ModifyUserContract.view view;
    private ModifyUserContract.remoteModel remoteModel;
    private ModifyUserContract.localModel localModel;

    private String userKey;

    public ModifyUserPresenter(ModifyUserContract.view view) {
        this.view = view;
        remoteModel = new RemoteModifyUserModel(this);
        localModel = new LocalModifyUserModel(this);
    }

    @Override
    public String getJwt(Context context) {
        userKey = TokenManager.read(context);
        return userKey;
    }

    @Override
    public void upLoadImage(Activity activity, Uri uri) {
        localModel.postPicture(activity, uri);
    }

    @Override
    public void bringUserInformation(String userKey) {
        remoteModel.getUserInformation(userKey);
    }

    @Override
    public void responseUserInformation(UserVo item) {
        view.showInformation(item);
    }

    @Override
    public void updateUserInformation(String userKey, UserVo userVo) {
        remoteModel.patchUserInformation(userKey, userVo);
    }
}
