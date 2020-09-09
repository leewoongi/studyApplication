package com.woon.wisestudytest1.user.makestudy.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woon.wisestudytest1.user.makestudy.contract.MakeStudyContract;

public class MakeStudyActivity extends AppCompatActivity implements MakeStudyContract.view, View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showInformation() {

    }

    @Override
    public void newtActivity() {

    }
}
