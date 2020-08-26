package com.woon.wisestudytest1.login.presenter;

import android.content.Context;
import android.util.Log;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.util.exception.KakaoException;
import com.woon.wisestudytest1.login.constract.LoginContract;

public class LoginPresenter implements LoginContract.presenter {

    private LoginContract.view view;
    private LoginContract.remoteModel remoteModel;
    private LoginContract.localModel localModel;

    public LoginPresenter(LoginContract.view view, LoginContract.remoteModel remoteModel, LoginContract.localModel localModel) {
        this.view = view;
        this.remoteModel = remoteModel;
        this.localModel = localModel;
    }

    //로그인 확인
    private ISessionCallback sessionCallback;
    private String accessToken;

    //jwt 키 값
    private String pJwt = "";

    public LoginPresenter(LoginContract.view view) {
        this.view = view;
    }

    // 카카오서버와 통신 여부 확인
    @Override
    public ISessionCallback checkLogin() {
        sessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                Log.i("KAKAO_SESSION", "로그인 성공");

                //카카오 서버와 통신이 됬다면 로그인 실행 후 카카오토큰 발급
                AuthService.getInstance()
                        .requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
                            @Override
                            public void onSessionClosed(ErrorResult errorResult) {
                                Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                            }

                            @Override
                            public void onFailure(ErrorResult errorResult) {
                                Log.e("KAKAO_API", "토큰 정보 요청 실패: " + errorResult);
                            }

                            @Override
                            public void onSuccess(AccessTokenInfoResponse result) {
                                Log.i("KAKAO_API", "사용자 아이디: " + result.getUserId());
                                Log.i("KAKAO_API", "남은 시간 (ms): " + result.getExpiresInMillis());
                                accessToken = Session.getCurrentSession().getAccessToken();

                                //모델에서 실서버와 통신하기
                                pJwt = remoteModel.requestLogin(accessToken);
                                if(pJwt != null){
                                    // jwt 값 sharedPre에 저장
                                    localModel.setJwtSharedPreference((Context) view, pJwt);
                                    view.loginSuccess();
                                }
                            }
                        });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {
                Log.e("KAKAO_SESSION", "로그인 실패", exception);
            }
        };
        return sessionCallback;
    }


    @Override
    public void onDestroy() {
        view = null;
        Session.getCurrentSession().removeCallback(sessionCallback);
    }
}
