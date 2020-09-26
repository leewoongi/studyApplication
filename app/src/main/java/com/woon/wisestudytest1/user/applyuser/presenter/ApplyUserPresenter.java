package com.woon.wisestudytest1.user.applyuser.presenter;

import com.woon.wisestudytest1.landing.view.LandingActivity;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.applyuser.cotract.ApplyUserContract;
import com.woon.wisestudytest1.user.applyuser.model.RemoteApplyUserModel;
import com.woon.wisestudytest1.user.applyuser.view.ApplyUserActivity;
import com.woon.wisestudytest1.util.SuccessCallback;

import java.util.List;

public class ApplyUserPresenter implements ApplyUserContract.presenter, SuccessCallback {

    private ApplyUserActivity view;
    private RemoteApplyUserModel remoteApplyUserModel;
    private ApplyUserContract.adapterView adapterView;
    private ApplyUserContract.adapterModel adapterModel;
    private int studyId;

    public ApplyUserPresenter(ApplyUserActivity applyUserActivity, String jwt) {
        this.view = applyUserActivity;
        remoteApplyUserModel = new RemoteApplyUserModel(this);
    }

    @Override
    public void retrieveApply(int study_id) {
        studyId = study_id;
        remoteApplyUserModel.getPresentApplyUser(studyId);
    }

    @Override
    public void responseApply(List<UserVo> data) {
        adapterModel.addItems(data);
        adapterView.refresh();
    }

    @Override
    public void confirmApply(int user_id) {
        remoteApplyUserModel.postConfirmUser(LandingActivity.userKey, studyId, user_id);
    }

    @Override
    public void rejectApply(int user_id) {
        remoteApplyUserModel.deleteRejectUser(LandingActivity.userKey, studyId, user_id);
    }

    @Override
    public void setAdapterView(ApplyUserContract.adapterView view) {
        this.adapterView = view;
    }

    @Override
    public void setAdapterModel(ApplyUserContract.adapterModel model) {
        this.adapterModel = model;
    }

    @Override
    public void onSuccess() {
        view.showToast();
    }
}
