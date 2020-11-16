package com.woon.wisestudytest1.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.login.contract.LoginContract;
import com.woon.wisestudytest1.login.presenter.LoginPresenter;
import com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity;
import com.woon.wisestudytest1.util.TokenManager;
import com.woon.wisestudytest1.util.UiHelper;

public class LoginActivity extends AppCompatActivity implements LoginContract.view, View.OnClickListener {

    public static String userKey = "";
    private LoginContract.presenter presenter;
    private Context mContext;

    private ISessionCallback sessionCallback  = new ISessionCallback() {
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
                Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL,LoginActivity.this);
                Session.getCurrentSession().addCallback(sessionCallback);
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
        userKey = TokenManager.read(mContext);
        //userKey = presenter.getJwt(mContext);
        Intent intent = new Intent(this, ModifyUserActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy(sessionCallback);
    }

}
