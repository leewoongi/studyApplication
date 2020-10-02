package com.woon.wisestudytest1.main.model;

import android.util.Log;

import com.woon.wisestudytest1.main.contract.MainContract;
import com.woon.wisestudytest1.main.networking.MainApiInterface;
import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainRemoteModel {

    private MainContract.presenter presenter;
    public MainRemoteModel(MainContract.presenter presenter) {
        this.presenter = presenter;
    }

    public void getUserInformation(String userKey) {
        MainApiInterface mainApiInterface = ApiClient.getInstance().create(MainApiInterface.class);
        Call<ApiResponse<UserVo>> call = mainApiInterface.retrieveUser(userKey);

        call.enqueue(new Callback<ApiResponse<UserVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserVo>> call, Response<ApiResponse<UserVo>> response) {
                if(response.isSuccessful() == false){
                    Log.d("User", "Failed to register");
                }else{
                    UserVo item = response.body().getMessage();
                    presenter.responseUserInformation(item);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserVo>> call, Throwable t) {
                Log.d("UserFailure", t.getMessage());
            }
        });
    }
}
