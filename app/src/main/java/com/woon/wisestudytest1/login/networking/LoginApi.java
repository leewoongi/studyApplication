package com.woon.wisestudytest1.login.networking;


import com.woon.wisestudytest1.login.entity.LoginVo;
import com.woon.wisestudytest1.login.entity.responseDto.LoginResponseDto;
import com.woon.wisestudytest1.util.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface LoginApi {

    @GET("/api/v1/oauth/token")
    Call<ApiResponse<LoginVo>> getJwtToken(@Header("kakao-access-token") String accessToken);
}
