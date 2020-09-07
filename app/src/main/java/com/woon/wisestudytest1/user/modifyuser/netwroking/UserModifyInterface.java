package com.woon.wisestudytest1.user.modifyuser.netwroking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface UserModifyInterface {

    //User 정보 가져오기
    @GET("/api/v1/users")
    Call<ApiResponse<UserVo>> retrieveUser(@Header("x-jwt-token") String userKey);

    //User 정보 업데이트
    @PUT("/api/v1/users")
    Call<ApiResponse<UserVo>> updateUser(@Header("x-jwt-token") String userKey,
                                         @Body UserVo userVo);

   // @Multipart
   // @PATCH("/api/v1/users")
   // Call<ApiResponse>



}
