package com.woon.wisestudytest1.user.createschedule.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.createschedule.contract.CreateScheduleContract;
import com.woon.wisestudytest1.user.createschedule.presenter.CreateSchedulePresenter;
import com.woon.wisestudytest1.user.schedule.view.ScheduleActivity;

import com.woon.wisestudytest1.util.UiHelper;

public class CreateScheduleActivity extends AppCompatActivity implements CreateScheduleContract.view {

    private CreateScheduleContract.presenter presenter;
    private ShapeableImageView CreateScheduleImageView;
    private MaterialTextView CreateScheduleTitle;
    private TextInputEditText createScheduleDate;
    private TextInputEditText createScheduleLocation;
    private TextInputEditText createScheduleDescription;
    private TextInputEditText createScheduleTime;

    private int studyId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);

        UiHelper.hideWindow(this);
        UiHelper.toolBarInitialize(this, findViewById(R.id.createScheduleToolbar));

        initialized();

        studyId = getIntent().getIntExtra("STUDY_ID",0);
        presenter = new CreateSchedulePresenter(this);

        presenter.retrieveStudyInformation(studyId);
    }

    private void initialized() {
        CreateScheduleImageView = findViewById(R.id.CreateScheduleImageView);
        CreateScheduleTitle = findViewById(R.id.createScheduleTitle);
        createScheduleDate = findViewById(R.id.createScheduleDate);
        createScheduleLocation = findViewById(R.id.createScheduleLocation);
        createScheduleTime = findViewById(R.id.createScheduleTime);
        createScheduleDescription = findViewById(R.id.createScheduleDescription);
    }

    @Override
    public void showStudyInformation(Uri uri, String title) {
        Glide.with(this).load(uri).into(CreateScheduleImageView);
        CreateScheduleTitle.setText(title);
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.push_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.ok){
            presenter.registerScheduleInformation(studyId, getSchedule());
        }
        return super.onOptionsItemSelected(item);
    }

    private UserSchedules getSchedule() {
        if(createScheduleDate.getText().toString() == null ||createScheduleTime.getText().toString() == null ||
                createScheduleLocation.getText().toString() == null || createScheduleDescription.getText().toString() == null){
            Toast.makeText(getApplicationContext(),"항목을 전부 입력해 주세요", Toast.LENGTH_LONG).show();
        }else{

            String date = createScheduleDate.getText().toString();
            String time = createScheduleTime.getText().toString();
            String datetime = date+"T"+time;

            String place = createScheduleLocation.getText().toString();
            String title = "스터디";
            String description = createScheduleDescription.getText().toString();

            UserSchedules data = new UserSchedules(studyId, datetime, place, title, description);
            return data;
        }
        return null;
    }

}
