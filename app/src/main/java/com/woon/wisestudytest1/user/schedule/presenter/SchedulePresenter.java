package com.woon.wisestudytest1.user.schedule.presenter;

import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.schedule.contract.ScheduleContract;
import com.woon.wisestudytest1.user.schedule.model.RemoteScheduleModel;
import com.woon.wisestudytest1.user.schedule.view.ScheduleAdapter;

import java.util.List;

public class SchedulePresenter implements ScheduleContract.presenter {

    private ScheduleContract.view view;
    private ScheduleAdapter adapter;
    private ScheduleContract.remoteModel remoteModel;

    public SchedulePresenter(ScheduleContract.view view, ScheduleAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
        remoteModel = new RemoteScheduleModel(this);
    }

    @Override
    public void retrieveSchedule(String userKey, String date) {
        remoteModel.getSchedule(userKey, date);
    }

    @Override
    public void responseSchedule(List<UserSchedules> userSchedules) {
        view.showSchedule(userSchedules);
    }

    @Override
    public void loadItems(List<UserSchedules> userSchedules) {
        adapter.addItems(userSchedules);
        adapter.refresh();
    }
}
