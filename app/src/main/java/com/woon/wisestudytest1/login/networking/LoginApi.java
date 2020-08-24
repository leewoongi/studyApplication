package com.woon.wisestudytest1.login.networking;

import com.woon.wisestudytest1.login.models.responseDto.LoginResponseDto;

import okhttp3.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginApi {

    //@POST("api/v1/oauth/token")
    //Call<LoginResponseDto> getJwt(@Header("kakao-access-token") String accessToken);
}
