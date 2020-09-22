package com.woon.wisestudytest1.user.schedule.networking;


import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserSchedules;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ScheduleInterface {
    @GET("/api/v1/users/schedules")
    Call<ApiResponse<List<UserSchedules>>> getSchedule(@Header("x-jwt-token")String useKey,
                                                       @Query("choice_date")String date);

}
