package com.woon.wisestudytest1.user.applystudy.model;

import android.util.Log;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.applystudy.networking.ApplyStudyInterface;
import com.woon.wisestudytest1.user.applystudy.presenter.ApplyStudyPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteApplyStudyModel {

    private ApplyStudyPresenter presenter;
    public RemoteApplyStudyModel(ApplyStudyPresenter applyStudyPresenter) {
        this.presenter = applyStudyPresenter;
    }

    public void getApplyStudyInformation(int studyId){
        ApplyStudyInterface applyStudyInterface = ApiClient.getInstance().create(ApplyStudyInterface.class);
        Call<ApiResponse<DetailStudyVo>> call = applyStudyInterface.getApplyStudyInformation(studyId);

        call.enqueue(new Callback<ApiResponse<DetailStudyVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<DetailStudyVo>> call, Response<ApiResponse<DetailStudyVo>> response) {
                if (response.isSuccessful() == false){
                    Log.d("applyStudy", "failed to load");
                }else{
                    DetailStudyVo data = response.body().getMessage();
                    presenter.responseStudyInformation(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DetailStudyVo>> call, Throwable t) {
                Log.d("applyStudy", t.getMessage());
            }
        });
    }

    public void postApplyMemberInStudy(int studyId, String userKey){
        ApplyStudyInterface applyStudyInterface = ApiClient.getInstance().create(ApplyStudyInterface.class);
        Call<ApiResponse<UserVo>> call = applyStudyInterface.postApplyStudy(studyId, userKey);

        call.enqueue(new Callback<ApiResponse<UserVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserVo>> call, Response<ApiResponse<UserVo>> response) {
                if(response.isSuccessful() == false){
                    Log.d("applyStudy", "failed to apply");
                }else{
                    presenter.responseApplyMember();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserVo>> call, Throwable t) {
                Log.d("applyStudy", t.getMessage());
            }
        });
    }

}
