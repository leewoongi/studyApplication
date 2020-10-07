package com.woon.wisestudytest1.main.networking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MainApiInterface {

    //User 정보 가져오기
    @GET("/api/v1/users")
    Call<ApiResponse<UserVo>> retrieveUser(@Header("x-jwt-token") String userKey);


    //Study 생성
    @Multipart
    @POST("/api/v1/studies")
    Call<ApiResponse<ResponseBody>> registerStudyInformation(@Header("x-jwt-token") String userKey,
                                                             @Part MultipartBody.Part img,
                                                             @Part ("category") RequestBody category,
                                                             @Part ("title")RequestBody title,
                                                             @Part ("limit")RequestBody limit,
                                                             @Part ("description")RequestBody description);

}
