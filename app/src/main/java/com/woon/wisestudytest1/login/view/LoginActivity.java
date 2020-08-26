package com.woon.wisestudytest1.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.Session;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.login.constract.LoginContract;
import com.woon.wisestudytest1.login.presenter.LoginPresenter;
import com.woon.wisestudytest1.user.UserActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.view, View.OnClickListener {
    private LoginContract.presenter presenter;

    //카카오서버와 테스트
    private boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Session.getCurrentSession().addCallback(sessionCallback);
        presenter = new LoginPresenter(LoginActivity.this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onClick(View v) {
        //카카오서버와 연결이 되는지 확인
        flag = presenter.checkLogin();

        if(flag == true){
            //연결이 된다면 엑세스토큰 값 받으러 가기
            presenter.startLogin();
        }
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
