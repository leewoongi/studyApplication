package com.woon.wisestudytest1.user.user.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.user.user.networking.UserApiInterface;
import com.woon.wisestudytest1.util.SuccessCallback;

public class RemoteUserModel implements UserContract.remoteModel {

    private UserContract.presenter presenter;
    private SuccessCallback successCallback;

    public RemoteUserModel(UserContract.presenter presenter, SuccessCallback successCallback) {
        this.presenter = presenter;
        this.successCallback = successCallback;
    }

    @Override
    public void getUserInformation(String userKey) {
        UserApiInterface userApiInterface = ApiClient.getInstance().create(UserApiInterface.class);
        Call<ApiResponse<UserVo>> call = userApiInterface.retrieveUser(userKey);

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

            }
        });
    }
}
