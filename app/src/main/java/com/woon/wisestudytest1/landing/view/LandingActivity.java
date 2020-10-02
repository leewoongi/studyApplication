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
import com.woon.wisestudytest1.util.UiHelper;

public class LandingActivity extends AppCompatActivity implements LandingContract.view {
    //userKey = jwt(앱이 처음 실행인지 아닌지 확인 하기 위해)
    public static String userKey = "";
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
        userKey = presenter.searchUserKey(mContext);
        if( userKey == null){
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        }else{
            //jwt 키가 존재하면 유저 화면으로 바로 가기
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            //intent = new Intent(getApplicationContext(), UserActivity.class);
            intent.putExtra("RESULT",1);
        }
        startActivity(intent);
        finish();
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
