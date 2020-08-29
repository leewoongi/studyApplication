package com.woon.wisestudytest1.user.user.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.user.contract.UserContract;

public class UserActivity extends AppCompatActivity implements UserContract.view {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    @Override
    public void showInformation() {

    }

    @Override
    public void showJoinedStudy() {

    }

    @Override
    public void showFavoriteField() {

    }
}
