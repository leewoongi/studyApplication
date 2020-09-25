package com.woon.wisestudytest1.user.applystudy.presenter;

import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.applystudy.contract.ApplyStudyContract;
import com.woon.wisestudytest1.user.applystudy.model.RemoteApplyStudyModel;
import com.woon.wisestudytest1.user.applystudy.view.ApplyStudyActivity;

public class ApplyStudyPresenter implements ApplyStudyContract.presenter {

    private ApplyStudyActivity view;
    private RemoteApplyStudyModel remoteApplyStudyModel;

    public ApplyStudyPresenter(ApplyStudyActivity applyStudyActivity) {
        this.view = applyStudyActivity;
        remoteApplyStudyModel = new RemoteApplyStudyModel(this);
    }

    @Override
    public void retrieveStudyInformation(int studyId) {
        remoteApplyStudyModel.getApplyStudyInformation(studyId);
    }

    @Override
    public void responseStudyInformation(DetailStudyVo detailStudyVo) {
        Uri uri = Uri.parse(detailStudyVo.getStudy_image());
        String studyName = detailStudyVo.getTitle();
        String description = detailStudyVo.getDescription();
        int needMember = detailStudyVo.getLimit();

        view.showInformation(uri, studyName, description, needMember);
    }

    @Override
    public void registerApplyMember(int studyId, String userKey) {
        remoteApplyStudyModel.postApplyMemberInStudy(studyId, userKey);
    }

    @Override
    public void responseApplyMember() {
        view.nextActivity();
    }


}
