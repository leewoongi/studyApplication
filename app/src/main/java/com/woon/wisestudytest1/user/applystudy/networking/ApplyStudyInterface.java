package com.woon.wisestudytest1.user.applystudy.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.Entity.UserVo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApplyStudyInterface {

    @GET("/api/v1/studies/{studies_id}")
    Call<ApiResponse<DetailStudyVo>> getApplyStudyInformation(@Path("studies_id") int id);

    @POST("/api/v1/studies/{studies_id}/members/apply")
    Call<ApiResponse<List<Object>>> postApplyStudy(@Path("studies_id")int id,
                                                   @Header("x-jwt-token") String userKey);
}
