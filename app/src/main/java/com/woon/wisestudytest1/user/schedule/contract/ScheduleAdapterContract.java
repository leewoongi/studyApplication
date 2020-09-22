package com.woon.wisestudytest1.user.schedule.contract;

import com.woon.wisestudytest1.user.Entity.UserSchedules;

import java.util.List;

public interface ScheduleAdapterContract {

    interface adapterView{
        void refresh();
    }

    interface adapterModel{
        void addItems(List<UserSchedules> userSchedules);
    }
}
