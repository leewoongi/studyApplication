package com.woon.wisestudytest1.user.createschedule.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CreateScheduleInterface {

    @GET("/api/v1/studies/{studies_id}")
    Call<ApiResponse<DetailStudyVo>> getStudyInformation(@Path("studies_id") int study_id);

    @POST("/api/v1/studies/{studies_id}/schedules")
    Call<ApiResponse<UserSchedules>> postCreateSchedule(@Path("studies_id") int study_id,
                                                        @Body UserSchedules data);


}
