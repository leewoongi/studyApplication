package com.woon.wisestudytest1.user.schedule.view;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.util.UiHelper;

public class ScheduleActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        UiHelper.toolBarInitialize(this, findViewById(R.id.plannerToolBarId));
        UiHelper.hideWindow(this);
        UiHelper.navigationOnclick(this, findViewById(R.id.plannerBottomNavigation));

        
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

    }
}
