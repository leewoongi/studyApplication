package com.woon.wisestudytest1.user.detailschedule.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.detailschedule.contract.DetailScheduleContract;
import com.woon.wisestudytest1.user.detailschedule.presenter.DetailSchedulePresenter;

public class DetailScheduleSummaryFragment extends Fragment implements DetailScheduleContract.fragmentView {

    private DetailScheduleContract.presenter presenter;
    private MaterialTextView plannerMember;

    public DetailScheduleSummaryFragment(DetailScheduleContract.presenter detailSchedulePresenter) {
        this.presenter = detailSchedulePresenter;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_detail_schedule_fragmnet_tab2, container,false);

        int study_id = getArguments().getInt("STUDY_ID",0);
        int schedule_id = getArguments().getInt("SCHEDULE_ID",0);

        initialized(rootView);
        presenter.retrieveStudySummary(study_id,schedule_id);

        return rootView;
    }

    @Override
    public void initialized(ViewGroup rootView) {
        plannerMember = rootView.findViewById(R.id.plannerMember);
    }

    @Override
    public void showStudySchedule(String title, String place) {

    }

    @Override
    public void showStudyScheduleSummary() {

    }
}
