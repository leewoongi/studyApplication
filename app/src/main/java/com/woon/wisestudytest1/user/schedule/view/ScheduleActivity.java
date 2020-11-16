package com.woon.wisestudytest1.user.schedule.view;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.schedule.contract.ScheduleContract;
import com.woon.wisestudytest1.user.schedule.presenter.SchedulePresenter;
import com.woon.wisestudytest1.util.TokenManager;
import com.woon.wisestudytest1.util.UiHelper;

import java.text.SimpleDateFormat;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleContract.view, CalendarView.OnDateChangeListener {

    private CalendarView calendarId;
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BottomNavigationView bottomNavigationView;
    private ScheduleContract.presenter presenter;
    private String choice_data = "";
    private String userKey = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schedule_fragment);

        UiHelper.toolBarInitialize(this, findViewById(R.id.plannerToolBarId));
        UiHelper.hideWindow(this);

        initialized();
        recyclerViewInit();
        changeDate();

        userKey = TokenManager.read(getApplicationContext());
        System.out.println("jwt" + userKey);
        presenter = new SchedulePresenter(ScheduleActivity.this, adapter);
        presenter.retrieveSchedule(userKey, choice_data);

        calendarId.setOnDateChangeListener(this);
    }

    private void changeDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(calendarId.getDate());
        choice_data = date;
    }

    private void initialized() {
        recyclerView = findViewById(R.id.plannerRecyclerId);
        calendarId = findViewById(R.id.calendarId);
    }

    private void recyclerViewInit() {
        adapter = new ScheduleAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        String y = Integer.toString(year);
        String m = "";
        if(month < 10 ){
            m = m + 0 + Integer.toString(month+1);
        }else{
            m = Integer.toString(month+1);
        }
        String d = "";
        if(dayOfMonth < 10){
            d = d + 0 + Integer.toString(dayOfMonth);
        }else{
            d = d + Integer.toString(dayOfMonth);
        }
        choice_data = y + "-" + m + "-" + d;
        presenter.retrieveSchedule(userKey, choice_data);
    }

    @Override
    public void showSchedule(List<UserSchedules> userSchedules) {
        presenter.loadItems(userSchedules);
    }
}
