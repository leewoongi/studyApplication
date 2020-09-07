package com.woon.wisestudytest1.user.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface UserApiInterface {

    @PUT("/api/v1/users")
    Call<ApiResponse<UserVo>> modifyUserInformation(@Header(" x-jwt-token") String id,
                                                    @Body UserVo userVo);


}
