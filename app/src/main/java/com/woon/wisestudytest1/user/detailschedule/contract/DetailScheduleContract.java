package com.woon.wisestudytest1.user.detailschedule.contract;

import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.StudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;

public interface DetailScheduleContract {

    interface view{
        void showStudyImage(Uri uri, String title);
    }

    interface fragmentView{
        void initialized(ViewGroup rootView);
        void showStudySchedule(String title, String place);
        void showStudyScheduleSummary();
    }



    interface presenter{

        void retrieveStudyInformation(int studyId);
        void responseStudyInformation(DetailStudyVo detailStudyVo);

        void setPageAdapterView(DetailSchedulePageAdapterContract.view adapterView);
        void setPageAdapterModel(DetailSchedulePageAdapterContract.model model);
        void loadFragment(Bundle bundle, int study_id, int schedule_id);

        void retrieveStudySchedule(int study_id, int schedule_id);
        void responseStudySchedule(UserSchedules userSchedules);

        void retrieveStudySummary(int study_id, int schedule_id);
        void responseStudySummary(UserSchedules userSchedules);
    }

}
