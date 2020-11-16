package com.woon.wisestudytest1.user.schedule.model;

import android.util.Log;

import retrofit2.Call;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.schedule.contract.ScheduleContract;
import com.woon.wisestudytest1.user.schedule.networking.ScheduleInterface;
import com.woon.wisestudytest1.user.schedule.presenter.SchedulePresenter;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class RemoteScheduleModel implements ScheduleContract.remoteModel {

    private SchedulePresenter presenter;
    public RemoteScheduleModel(SchedulePresenter schedulePresenter) {
        this.presenter = schedulePresenter;
    }

    @Override
    public void getSchedule(String userKey, String date) {

        ScheduleInterface scheduleInterface = ApiClient.getInstance().create(ScheduleInterface.class);
        Call<ApiResponse<List<UserSchedules>>> call = scheduleInterface.getSchedule(userKey, date);

        call.enqueue(new Callback<ApiResponse<List<UserSchedules>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<UserSchedules>>> call, Response<ApiResponse<List<UserSchedules>>> response) {
                if(response.isSuccessful() == false){
                    Log.d("Schedule", "Failed to load");
                }

                if(response.body().getMessage() == null){
                    Log.d("Schedule", "you haven't schedule");
                }else{
                    List<UserSchedules> userSchedules = response.body().getMessage();
                    presenter.responseSchedule(userSchedules);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<UserSchedules>>> call, Throwable t) {
                Log.d("ScheduleFailure", t.getMessage());
            }
        });
    }
}
