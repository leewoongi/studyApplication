package com.woon.wisestudytest1.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.login.contract.LoginContract;
import com.woon.wisestudytest1.login.presenter.LoginPresenter;
import com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity;
import com.woon.wisestudytest1.util.UiHelper;

import static com.kakao.auth.AuthType.*;

public class LoginActivity extends AppCompatActivity implements LoginContract.view, View.OnClickListener {

    private LoginContract.presenter presenter;
    private Context mContext;

    private ISessionCallback callback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Log.i("KAKAO_SESSION", "로그인 성공");
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("KAKAO_SESSION", "로그인 실패", exception);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialized();
        presenter = new LoginPresenter(LoginActivity.this);

        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.getJwtToken).setOnClickListener(this);
        findViewById(R.id.deleteJwtToken).setOnClickListener(this);
    }

    private void initialized() {
        UiHelper.hideWindow(this);
        mContext = this;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.loginButton:
                //카카오서버와 연결이 되는지 확인j
                Session.getCurrentSession().addCallback(callback);
                Session.getCurrentSession().open(KAKAO_LOGIN_ALL, this);
                presenter.startLogin(mContext);
                break;

            case R.id.getJwtToken:
                //jwt가 잘 들어갔는지 확인하기 위한 테스트 용도
                presenter.getJwt(mContext);
                break;

            case R.id.deleteJwtToken:
                presenter.deleteJwt(mContext);
                break;
        }
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, ModifyUserActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy(callback);
    }

}
