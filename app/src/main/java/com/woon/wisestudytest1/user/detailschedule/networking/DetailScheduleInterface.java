package com.woon.wisestudytest1.user.detailschedule.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserSchedules;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface DetailScheduleInterface {

    @GET("/api/v1/studies/{studies_id}")
    Call<ApiResponse<DetailStudyVo>> getStudyInformation(@Path("studies_id") int study_id);

    @GET("/api/v1/studies/{studies_id}/schedules/{schedules_id}")
    Call<ApiResponse<UserSchedules>> getStudySchedule(@Path("studies_id") int study_id,
                                                      @Path("schedules_id") int schedule_id);

    @GET("/api/v1/studies/{studies_id}/schedules/{schedules_id}")
    Call<ApiResponse<UserSchedules>> getStudyScheduleSummary(@Path("studies_id") int study_id,
                                                      @Path("schedules_id") int schedule_id);
}
