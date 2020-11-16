package com.woon.wisestudytest1.user.searchStudy.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchStudyInterface {

    @GET("/api/v1/studies")
    Call<ApiResponse<List<DetailStudyVo>>> getAllStudies();

    @GET("/api/v1/studies")
    Call<ApiResponse<List<DetailStudyVo>>> getSpecificStudies(@Query("title") String field);

}
