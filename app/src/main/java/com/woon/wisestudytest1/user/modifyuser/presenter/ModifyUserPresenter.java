package com.woon.wisestudytest1.user.modifyuser.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.model.LocalModifyUserModel;
import com.woon.wisestudytest1.user.modifyuser.model.RemoteModifyUserModel;
import com.woon.wisestudytest1.util.TokenManager;


import java.io.IOException;
import java.io.InputStream;

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
    public void changeImage(Activity activity, Intent data) {

        try {

            InputStream in = activity.getContentResolver().openInputStream(data.getData());
            Bitmap img = BitmapFactory.decodeStream(in);
            in.close();
            view.showImage(img);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestModifyUser(String key) {
        remoteModel.putUserInformation(key);
        view.showInformation();
    }
}
