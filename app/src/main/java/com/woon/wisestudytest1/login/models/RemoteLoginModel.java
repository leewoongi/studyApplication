package com.woon.wisestudytest1.login.models;



import android.content.Context;
import android.util.Log;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.woon.wisestudytest1.login.contract.LoginContract;
import com.woon.wisestudytest1.login.entity.LoginVo;
import com.woon.wisestudytest1.login.networking.LoginApiInterface;
import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteLoginModel implements LoginContract.remoteModel {

    private LoginContract.presenter presenter;
    private LoginContract.localModel localModel;

    // 카카오 토큰 및 jwt 키
    private String accessToken;
    private String rmJwt;

    public RemoteLoginModel(LoginContract.presenter presenter) {
        this.presenter = presenter;
    }

    //로그인 통신
    @Override
    public void requestLogin(Context mContext) {

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

                        LoginApiInterface loginApiInterface = ApiClient.getInstance().create(LoginApiInterface.class);
                        Call<ApiResponse<LoginVo>> call = loginApiInterface.getJwtToken(accessToken);

                        call.enqueue(new Callback<ApiResponse<LoginVo>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<LoginVo>> call, Response<ApiResponse<LoginVo>> response) {
                                if(!response.isSuccessful()){
                                    Log.d("LoginFail","Failed to register");
                                }
                                Log.d("LoginSuccess","Success to register");
                                rmJwt = response.body().getMessage().getJwt();
                                System.out.println("=====>JWT : " + rmJwt);
                                presenter.setJwtOnSuccess(mContext, rmJwt);
                            }

                            @Override
                            public void onFailure(Call<ApiResponse<LoginVo>> call, Throwable t) {
                                System.out.println("=====>"+t.getMessage());
                            }
                        });
                    }
                });
    }
}
