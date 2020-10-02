package com.woon.wisestudytest1.main.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MainApiInterface {

    //User 정보 가져오기
    @GET("/api/v1/users")
    Call<ApiResponse<UserVo>> retrieveUser(@Header("x-jwt-token") String userKey);

}
