package com.woon.wisestudytest1.landing.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.landing.contract.LandingContract;
import com.woon.wisestudytest1.landing.presenter.LandingPresenter;
import com.woon.wisestudytest1.login.view.LoginActivity;
import com.woon.wisestudytest1.user.user.view.UserActivity;
import com.woon.wisestudytest1.util.UiHelper;

public class LandingActivity extends AppCompatActivity implements LandingContract.view {
    private LandingContract.presenter presenter;

    private Context mContext;
    private Handler waitActivity;
    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        initialized();

        presenter = new LandingPresenter(LandingActivity.this);
        waitAnimateActivity();

    }

    private void initialized() {
        UiHelper.hideWindow(this);
        mContext = this;
    }

    @Override
    public void checkUserKey() {
        Intent intent;
        if(presenter.searchUserKey(mContext) == null){
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        }else{
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            //intent = new Intent(getApplicationContext(), UserActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void waitAnimateActivity() {
        waitActivity = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                checkUserKey();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        waitActivity.postDelayed(runnable, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        waitActivity.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}
