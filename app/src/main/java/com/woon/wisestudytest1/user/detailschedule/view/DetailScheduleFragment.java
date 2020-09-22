package com.woon.wisestudytest1.user.detailschedule.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.createschedule.view.CreateScheduleActivity;
import com.woon.wisestudytest1.user.detailschedule.contract.DetailScheduleContract;

public class DetailScheduleFragment extends Fragment implements DetailScheduleContract.fragmentView, View.OnClickListener {
    private DetailScheduleContract.presenter presenter;

    private MaterialTextView plannerDetailLocation;
    private MaterialTextView plannerDetailDescription;
    private MaterialButton detailScheduleCreateSchedule;

    private int study_id;
    private  int schedule_id;

    public DetailScheduleFragment(DetailScheduleContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_detail_schedule_fragment_tab1, container,false);

        initialized(rootView);
        study_id = getArguments().getInt("STUDY_ID",0);
        schedule_id = getArguments().getInt("SCHEDULE_ID",0);

        presenter.retrieveStudySchedule(study_id,schedule_id);
        detailScheduleCreateSchedule.setOnClickListener(this);

        return rootView;
    }

    public void initialized(ViewGroup v) {
        plannerDetailLocation = v.findViewById(R.id.plannerDetailLocation);
        plannerDetailDescription = v.findViewById(R.id.plannerDetailDescription);
        detailScheduleCreateSchedule = v.findViewById(R.id.detailScheduleCreateSchedule);
    }

    @Override
    public void showStudySchedule(String title, String place) {
        plannerDetailLocation.setText(place);
        plannerDetailDescription.setText(title);
    }

    @Override
    public void showStudyScheduleSummary() {
        //사용안함
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.detailScheduleCreateSchedule :
                Intent intent = new Intent(getActivity(), CreateScheduleActivity.class);
                intent.putExtra("STUDY_ID", study_id);
                startActivity(intent);
            default:
                break;
        }
    }
}
