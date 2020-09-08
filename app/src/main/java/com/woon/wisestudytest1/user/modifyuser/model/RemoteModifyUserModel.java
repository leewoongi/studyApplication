package com.woon.wisestudytest1.user.modifyuser.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.netwroking.UserModifyInterface;

public class RemoteModifyUserModel implements ModifyUserContract.remoteModel {
    private ModifyUserContract.presenter presenter;

    public RemoteModifyUserModel(ModifyUserContract.presenter presenter) {
        this.presenter = presenter;
    }

    //화면 켜질시 데이터 가져옴
    @Override
    public void getUserInformation(String userKey) {
        UserModifyInterface userModifyInterface = ApiClient.getInstance().create(UserModifyInterface.class);
        Call<ApiResponse<UserVo>> call = userModifyInterface.retrieveUser(userKey);

        call.enqueue(new Callback<ApiResponse<UserVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserVo>> call, Response<ApiResponse<UserVo>> response) {
                if(!response.isSuccessful()){
                    Log.d("User", "Failed to register");
                }

                UserVo item = response.body().getMessage();
                System.out.println("이름 : " + item.getName() + "/n" + "나이 : " + item.getAge() + "/n" +
                        "휴대폰번호 : " + item.getCellphone() +"/n" + "소개 : " + item.getDescription());
                presenter.responseUserInformation(item);

            }

            @Override
            public void onFailure(Call<ApiResponse<UserVo>> call, Throwable t) {
                Log.d("UserFailure", t.getMessage());
            }
        });

    }

    @Override
    public void patchUserInformation(String userKey, UserVo userVo) {
        UserModifyInterface userModifyInterface = ApiClient.getInstance().create(UserModifyInterface.class);
        Call<ApiResponse<UserVo>> call = userModifyInterface.updateUser(userKey, userVo);

        call.enqueue(new Callback<ApiResponse<UserVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserVo>> call, Response<ApiResponse<UserVo>> response) {
                UserVo item  = response.body().getMessage();
                presenter.responseUserInformation(item);
            }

            @Override
            public void onFailure(Call<ApiResponse<UserVo>> call, Throwable t) {
                Log.d("UserFailure", t.getMessage());
            }
        });
    }

}
