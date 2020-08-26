package com.woon.wisestudytest1.login.networking;


import com.woon.wisestudytest1.login.entity.LoginResponse;
import com.woon.wisestudytest1.util.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("api/v1/oauth/token")
    Call<ApiResponse<LoginResponse>> getJwt(@Header("kakao-access-token") String accessToken);
}
