package com.woon.wisestudytest1.user.detailschedule.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.StudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.detailschedule.networking.DetailScheduleInterface;
import com.woon.wisestudytest1.user.detailschedule.presenter.DetailSchedulePresenter;

public class RemoteDetailScheduleModel {


    private DetailSchedulePresenter detailSchedulePresenter;
    public RemoteDetailScheduleModel(DetailSchedulePresenter detailSchedulePresenter) {
        this.detailSchedulePresenter = detailSchedulePresenter;
    }

    public void getStudyInformation(int studyId){
        DetailScheduleInterface detailScheduleInterface = ApiClient.getInstance().create(DetailScheduleInterface.class);
        Call<ApiResponse<DetailStudyVo>> call = detailScheduleInterface.getStudyInformation(studyId);

        call.enqueue(new Callback<ApiResponse<DetailStudyVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<DetailStudyVo>> call, Response<ApiResponse<DetailStudyVo>> response) {
                if(response.isSuccessful() == false){
                    Log.d("StudyInformation", "Failed to register");
                }
                else{
                    DetailStudyVo data = response.body().getMessage();
                    detailSchedulePresenter.responseStudyInformation(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DetailStudyVo>> call, Throwable t) {
                Log.d("StudyInformation fail", t.getMessage());
            }
        });
    }

    public void getStudySchedule(int study_id, int schedule_id){
        DetailScheduleInterface detailScheduleInterface = ApiClient.getInstance().create(DetailScheduleInterface.class);
        Call<ApiResponse<UserSchedules>> call = detailScheduleInterface.getStudySchedule(study_id, schedule_id);

        call.enqueue(new Callback<ApiResponse<UserSchedules>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserSchedules>> call, Response<ApiResponse<UserSchedules>> response) {
                if(response.isSuccessful() == false){
                    Log.d("studySchedule", "Failed to register");
                }else{
                    UserSchedules data = response.body().getMessage();
                    detailSchedulePresenter.responseStudySchedule(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserSchedules>> call, Throwable t) {
                Log.d("studyScheduleFail",t.getMessage());
            }
        });
    }

    public void getStudyScheduleSummary(int study_id, int schedule_id){
        DetailScheduleInterface detailScheduleInterface = ApiClient.getInstance().create(DetailScheduleInterface.class);
        Call<ApiResponse<UserSchedules>> call = detailScheduleInterface.getStudyScheduleSummary(study_id, schedule_id);

        call.enqueue(new Callback<ApiResponse<UserSchedules>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserSchedules>> call, Response<ApiResponse<UserSchedules>> response) {
                if(response.isSuccessful() == false){
                    Log.d("studySchedule", "Failed to register");
                }else{
                    UserSchedules data = response.body().getMessage();
                    detailSchedulePresenter.responseStudySummary(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserSchedules>> call, Throwable t) {
                Log.d("studyScheduleFail",t.getMessage());
            }
        });
    }
}
