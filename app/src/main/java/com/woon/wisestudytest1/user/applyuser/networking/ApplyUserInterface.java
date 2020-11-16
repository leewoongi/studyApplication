package com.woon.wisestudytest1.user.applyuser.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApplyUserInterface {

    @GET("/api/v1/studies/{studies_id}/members/apply")
    Call<ApiResponse<List<UserVo>>> getApplyUser(@Path("studies_id") int study_id);

    @POST("/api/v1/studies/{studies_id}/members/comfirm")
    Call<ApiResponse<List<Object>>> postConfirmUser(@Path("studies_id") int study_id,
                                                    @Header("x-jwt-token") String userKey,
                                                    @Body int user_id);

    @HTTP(method = "DELETE", path = "/api/v1/studies/{studies_id}/members/comfirm", hasBody = true)
    Call<ApiResponse<List<Object>>> deleteRejectUser(@Path("studies_id") int study_id,
                                                     @Header("x-jwt-token") String userKey,
                                                     @Body int user_id);

}
