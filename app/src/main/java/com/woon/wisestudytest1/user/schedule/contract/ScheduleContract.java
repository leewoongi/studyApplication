package com.woon.wisestudytest1.user.schedule.contract;

import com.woon.wisestudytest1.user.Entity.UserSchedules;

import java.util.List;

public interface ScheduleContract {

    interface view{
        void showSchedule(List<UserSchedules> userSchedules);
    }

    interface presenter{
        void retrieveSchedule(String userKey, String date);
        void responseSchedule(List<UserSchedules> userSchedules);
        void loadItems(List<UserSchedules> userSchedules);

    }

    interface remoteModel{
        void getSchedule(String userKey, String date);
    }
}
