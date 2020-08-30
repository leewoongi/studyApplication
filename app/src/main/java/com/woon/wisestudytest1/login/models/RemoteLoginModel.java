package com.woon.wisestudytest1.login.models;



import android.content.Context;
import android.util.Log;

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

    private String rmJwt;

    public RemoteLoginModel() {
        localModel = new LocalLoginModel();
    }

    //로그인 통신
    @Override
    public void requestLogin(Context mContext, String accessToken) {
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
                localModel.setJwtSharedPreference(mContext, rmJwt);
                System.out.println("=====>JWT : " + rmJwt);
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginVo>> call, Throwable t) {
                System.out.println("=====>"+t.getMessage());
            }
        });
    }
}
