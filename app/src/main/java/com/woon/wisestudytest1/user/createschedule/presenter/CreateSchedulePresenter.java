package com.woon.wisestudytest1.user.createschedule.presenter;

import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.createschedule.contract.CreateScheduleContract;
import com.woon.wisestudytest1.user.createschedule.model.RemoteCreateScheduleModel;
import com.woon.wisestudytest1.user.createschedule.view.CreateScheduleActivity;
import com.woon.wisestudytest1.util.SuccessCallback;

public class CreateSchedulePresenter implements CreateScheduleContract.presenter, SuccessCallback {

    private CreateScheduleContract.view view;
    private RemoteCreateScheduleModel remoteCreateScheduleModel;

    public CreateSchedulePresenter(CreateScheduleContract.view view) {
        this.view = view;
        remoteCreateScheduleModel = new RemoteCreateScheduleModel(this);
    }

    @Override
    public void retrieveStudyInformation(int studyId) {
        remoteCreateScheduleModel.getStudyInformation(studyId);
    }

    @Override
    public void responseStudyInformation(DetailStudyVo detailStudyVo) {
        Uri uri = Uri.parse(detailStudyVo.getStudy_image());
        String title = detailStudyVo.getTitle();

        view.showStudyInformation(uri, title);
    }

    @Override
    public void registerScheduleInformation(int studyId, UserSchedules data) {
        remoteCreateScheduleModel.createSchedule(studyId, data);
    }

    @Override
    public void onSuccess() {
        view.nextActivity();
    }
}
