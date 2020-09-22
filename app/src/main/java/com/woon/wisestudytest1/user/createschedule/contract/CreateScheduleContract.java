package com.woon.wisestudytest1.user.createschedule.contract;

import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;

public interface CreateScheduleContract {

    interface view{
        void showStudyInformation(Uri uri, String title);
        void nextActivity();
    }

    interface presenter{
        void retrieveStudyInformation(int studyId);
        void responseStudyInformation(DetailStudyVo detailStudyVo);

        void registerScheduleInformation(int studyId, UserSchedules userSchedules);
    }
}
