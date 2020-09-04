package com.woon.wisestudytest1.user.modifyuser.netwroking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserImageVo;
import com.woon.wisestudytest1.user.Entity.UserVo;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserModifyInterface {

    //이미지 업데이트
    @Multipart
    @POST("/api/v1/")
    Call<ApiResponse<UserImageVo>> upLoadingImage (@Header("x-jwt-token") String userKey,
                                                   @Part MultipartBody.Part file);


    @PUT("/api/v1/users")
    Call<ApiResponse<UserVo>> putUserInformation (@Header("x-jwt-token") String userKey,
                                                  @Body UserVo userVo);


}
