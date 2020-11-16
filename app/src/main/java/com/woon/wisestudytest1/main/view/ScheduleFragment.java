package com.woon.wisestudytest1.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.main.contract.MainContract;

public class ScheduleFragment extends Fragment {

    private MainContract.presenter presenter;

    public ScheduleFragment(MainContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_main_schedule_fragment,container,false);
        return rootView;
    }
}
