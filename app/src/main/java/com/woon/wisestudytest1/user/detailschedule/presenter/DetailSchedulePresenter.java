package com.woon.wisestudytest1.user.detailschedule.presenter;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.detailschedule.contract.DetailScheduleContract;
import com.woon.wisestudytest1.user.detailschedule.contract.DetailSchedulePageAdapterContract;
import com.woon.wisestudytest1.user.detailschedule.model.RemoteDetailScheduleModel;
import com.woon.wisestudytest1.user.detailschedule.view.DetailScheduleFragment;
import com.woon.wisestudytest1.user.detailschedule.view.DetailScheduleSummaryFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailSchedulePresenter implements DetailScheduleContract.presenter {

    private DetailScheduleContract.view view;
    private TabLayout tabLayout;
    private DetailSchedulePageAdapterContract.view adapterView;
    private DetailSchedulePageAdapterContract.model adapterModel;
    private RemoteDetailScheduleModel remoteDetailScheduleModel;
    private DetailScheduleFragment detailScheduleFragment;
    private DetailScheduleSummaryFragment detailScheduleSummaryFragment;

    public DetailSchedulePresenter(DetailScheduleContract.view view, TabLayout tabLayout) {
        this.view = view;
        this.tabLayout = tabLayout;
        remoteDetailScheduleModel = new RemoteDetailScheduleModel(this);
        detailScheduleFragment = new DetailScheduleFragment(this);
        detailScheduleSummaryFragment = new DetailScheduleSummaryFragment(this);
    }

    @Override
    public void retrieveStudyInformation(int StudyId) {
        remoteDetailScheduleModel.getStudyInformation(StudyId);
    }

    @Override
    public void responseStudyInformation(DetailStudyVo detailStudyVo) {
        Uri uri = Uri.parse(String.valueOf(detailStudyVo.getStudy_image()));
        String title = detailStudyVo.getTitle();
        view.showStudyImage(uri, title);
    }

    @Override
    public void setPageAdapterView(DetailSchedulePageAdapterContract.view adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setPageAdapterModel(DetailSchedulePageAdapterContract.model adapterModel) {
        this.adapterModel = adapterModel;
    }


    @Override
    public void loadFragment(Bundle bundle, int study_id, int schedule_id) {
        bundle.putInt("STUDY_ID", study_id);
        bundle.putInt("SCHEDULE_ID", schedule_id);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(detailScheduleFragment);
        fragments.add(detailScheduleSummaryFragment);

        fragments.get(0).setArguments(bundle);
        fragments.get(1).setArguments(bundle);

        adapterModel.addPage(fragments);
        adapterView.refresh();
    }

    @Override
    public void retrieveStudySchedule(int study_id, int schedule_id) {
        remoteDetailScheduleModel.getStudySchedule(study_id,schedule_id);
    }

    @Override
    public void responseStudySchedule(UserSchedules userSchedules) {
        String title = userSchedules.getTitle();
        String place = userSchedules.getPlace();

        detailScheduleFragment.showStudySchedule(title, place);
    }

    @Override
    public void retrieveStudySummary(int study_id, int schedule_id) {
        remoteDetailScheduleModel.getStudyScheduleSummary(study_id, schedule_id);
    }

    @Override
    public void responseStudySummary(UserSchedules userSchedules) {
        /**
         * api 수정되면
         * 인원 int
         * 구성원 카톡 프로필 사진
         */
        //detailScheduleSummaryFragment();
    }


}
