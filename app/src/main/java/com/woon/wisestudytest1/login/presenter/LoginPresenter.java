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
import com.woon.wisestudytest1.login.models.LocalLoginModel;
import com.woon.wisestudytest1.login.models.RemoteLoginModel;

public class LoginPresenter implements LoginContract.presenter {

    private LoginContract.view view;
    private LoginContract.remoteModel remoteModel;
    private LoginContract.localModel localModel;

    // 카카오 토큰 및 jwt 키
    private String accessToken;
    private String pJwt = "";

    public LoginPresenter(LoginContract.view view) {
        this.view = view;
        remoteModel = new RemoteLoginModel();
        localModel = new LocalLoginModel();
    }

    //카카오 서버와 통신이 됬다면 로그인 실행 후 카카오토큰 발급
    @Override
    public void startLogin(Context context) {
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
                            localModel.setJwtSharedPreference(context, pJwt);
                            //view.loginSuccess();
                        }
                    }
                });
    }


    // jwt 키값의 유무로 이동 페이지가 달라지기 때문에 테스트 용도로 만듬
    @Override
    public void getJwt(Context context) {
        String value = localModel.getJwtSharedPreference(context);
        Log.d("JWT" , value);
    }

    @Override
    public void deleteJwt(Context context) {
        localModel.deleteJwtSharedPreference(context);
    }


    @Override
    public void onDestroy(ISessionCallback callback) {
        view = null;
        Session.getCurrentSession().removeCallback(callback);
    }
}
