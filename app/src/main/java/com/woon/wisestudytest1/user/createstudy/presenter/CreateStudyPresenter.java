package com.woon.wisestudytest1.user.createstudy.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.StudyVo;
import com.woon.wisestudytest1.user.createstudy.contract.CreateStudyContract;
import com.woon.wisestudytest1.user.createstudy.model.LocalModelCreateStudy;
import com.woon.wisestudytest1.user.createstudy.model.RemoteModelCreateStudy;
import com.woon.wisestudytest1.util.SuccessCallback;
import com.woon.wisestudytest1.util.TokenManager;

import okhttp3.MultipartBody;

public class CreateStudyPresenter implements CreateStudyContract.presenter, SuccessCallback {

    private CreateStudyContract.view view;
    private CreateStudyContract.localModel localModel;
    private CreateStudyContract.remoteModel remoteModel;
    private String userKey;

    public CreateStudyPresenter(CreateStudyContract.view view) {
        this.view = view;
        this.localModel = new LocalModelCreateStudy(this, this);
        this.remoteModel = new RemoteModelCreateStudy(this, this);
    }


    @Override
    public String getJwt(Context context) {
        userKey = TokenManager.read(context);
        return userKey;
    }

    @Override
    public MultipartBody.Part registerImage(Activity activity, String userKey, Uri uri) {
        return localModel.getPicture(activity, userKey, uri);
    }

    @Override
    public void registerStudyInformation(String userKey, StudyVo studyVo) {
        remoteModel.requestStudyInformation(userKey, studyVo);
    }


    @Override
    public void onSuccess() {
        view.nextActivity();
    }
}
