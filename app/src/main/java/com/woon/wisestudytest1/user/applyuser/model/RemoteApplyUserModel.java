package com.woon.wisestudytest1.user.applyuser.model;

import android.util.Log;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.applyuser.networking.ApplyUserInterface;
import com.woon.wisestudytest1.user.applyuser.presenter.ApplyUserPresenter;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteApplyUserModel {
    private ApplyUserPresenter presenter;

    public RemoteApplyUserModel(ApplyUserPresenter presenter) {
        this.presenter = presenter;
    }

    public void getPresentApplyUser(int study_id){
        ApplyUserInterface applyUserInterface = ApiClient.getInstance().create(ApplyUserInterface.class);
        Call<ApiResponse<List<UserVo>>> call = applyUserInterface.getApplyUser(study_id);

        call.enqueue(new Callback<ApiResponse<List<UserVo>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<UserVo>>> call, Response<ApiResponse<List<UserVo>>> response) {
                if(response.isSuccessful() == false){
                    Log.d("ApplyUser", "fail to load");
                }else{
                    List<UserVo> data = response.body().getMessage();
                    presenter.responseApply(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<UserVo>>> call, Throwable t) {
                Log.d("ApplyUser", t.getMessage());
            }
        });
    }

    public void postConfirmUser(String userKey, int study_id, int user_id){
        ApplyUserInterface applyUserInterface = ApiClient.getInstance().create(ApplyUserInterface.class);
        Call<ApiResponse<List<Object>>> call = applyUserInterface.postConfirmUser(study_id, userKey, user_id);

        call.enqueue(new Callback<ApiResponse<List<Object>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Object>>> call, Response<ApiResponse<List<Object>>> response) {
                if(response.isSuccessful() == false){
                    Log.d("confirm", "fail");
                }else{
                    response.body();
                    presenter.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Object>>> call, Throwable t) {
                Log.d("confirm",t.getMessage());
            }
        });

    }

    public void deleteRejectUser(String userKey, int study_id, int user_id){
        ApplyUserInterface applyUserInterface = ApiClient.getInstance().create(ApplyUserInterface.class);
        Call<ApiResponse<List<Object>>> call = applyUserInterface.deleteRejectUser(study_id, userKey, user_id);

        call.enqueue(new Callback<ApiResponse<List<Object>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Object>>> call, Response<ApiResponse<List<Object>>> response) {
                if(response.isSuccessful() == false){
                    Log.d("reject", "fail");
                }else{
                    presenter.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Object>>> call, Throwable t) {
                Log.d("reject", t.getMessage());
            }
        });
    }
}
