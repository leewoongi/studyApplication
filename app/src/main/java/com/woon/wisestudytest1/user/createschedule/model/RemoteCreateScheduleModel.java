package com.woon.wisestudytest1.user.createschedule.model;

import android.util.Log;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.createschedule.contract.CreateScheduleContract;
import com.woon.wisestudytest1.user.createschedule.networking.CreateScheduleInterface;
import com.woon.wisestudytest1.user.createschedule.presenter.CreateSchedulePresenter;
import com.woon.wisestudytest1.user.detailschedule.networking.DetailScheduleInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteCreateScheduleModel {

    private CreateSchedulePresenter presenter;

    public RemoteCreateScheduleModel(CreateSchedulePresenter presenter) {
        this.presenter = presenter;
    }

    public void getStudyInformation(int studyId){
        CreateScheduleInterface createScheduleInterface= ApiClient.getInstance().create(CreateScheduleInterface.class);
        Call<ApiResponse<DetailStudyVo>> call = createScheduleInterface.getStudyInformation(studyId);

        call.enqueue(new Callback<ApiResponse<DetailStudyVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<DetailStudyVo>> call, Response<ApiResponse<DetailStudyVo>> response) {
                if(response.isSuccessful() == false){
                    Log.d("StudyInformation", "Failed to register");
                }
                else{
                    DetailStudyVo data = response.body().getMessage();
                    presenter.responseStudyInformation(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DetailStudyVo>> call, Throwable t) {
                Log.d("StudyInformation fail", t.getMessage());
            }
        });
    }

    public void createSchedule(int studyId, UserSchedules data){
        CreateScheduleInterface createScheduleInterface= ApiClient.getInstance().create(CreateScheduleInterface.class);
        Call<ApiResponse<UserSchedules>> call = createScheduleInterface.postCreateSchedule(studyId, data);

        call.enqueue(new Callback<ApiResponse<UserSchedules>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserSchedules>> call, Response<ApiResponse<UserSchedules>> response) {
                if(response.isSuccessful() == false){
                    Log.d("createSchedule", "Failed to register");
                }else{
                    Log.d("createSchedule", "Success to register");
                    presenter.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserSchedules>> call, Throwable t) {
                Log.d("createSchedule fail", t.getMessage());
            }
        });
    }


}
