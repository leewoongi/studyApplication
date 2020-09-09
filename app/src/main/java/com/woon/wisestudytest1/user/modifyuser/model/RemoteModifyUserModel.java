package com.woon.wisestudytest1.user.modifyuser.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UpdateUserVo;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.netwroking.UserModifyInterface;
import com.woon.wisestudytest1.util.SuccessCallback;

public class RemoteModifyUserModel implements ModifyUserContract.remoteModel {
    private ModifyUserContract.presenter presenter;
    private SuccessCallback successCallback;

    public RemoteModifyUserModel(ModifyUserContract.presenter presenter, SuccessCallback successCallback) {
        this.presenter = presenter;
        this.successCallback = successCallback;
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
    public void patchUserInformation(String userKey, UpdateUserVo updateUserVo) {
        UserModifyInterface userModifyInterface = ApiClient.getInstance().create(UserModifyInterface.class);
        Call<ApiResponse<UpdateUserVo>> call = userModifyInterface.updateUser(userKey, updateUserVo);

        call.enqueue(new Callback<ApiResponse<UpdateUserVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UpdateUserVo>> call, Response<ApiResponse<UpdateUserVo>> response) {
                if(response.isSuccessful() == false){
                    Log.d("UserUpdate","Failed to UserUpdate");

                }
                successCallback.onSuccess();
            }

            @Override
            public void onFailure(Call<ApiResponse<UpdateUserVo>> call, Throwable t) {
                Log.d("UserFailure", t.getMessage());
            }
        });
    }

}
