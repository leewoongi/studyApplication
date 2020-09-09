package com.woon.wisestudytest1.user.modifyuser.netwroking;

import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UpdateUserVo;
import com.woon.wisestudytest1.user.Entity.UserImageVo;
import com.woon.wisestudytest1.user.Entity.UserVo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserModifyInterface {

    //User 정보 가져오기
    @GET("/api/v1/users")
    Call<ApiResponse<UserVo>> retrieveUser(@Header("x-jwt-token") String userKey);

    //User 정보 업데이트
    @PATCH("/api/v1/users")
    Call<ApiResponse<UpdateUserVo>> updateUser(@Header("x-jwt-token") String userKey,
                                               @Body UpdateUserVo updateUserVo);

    //User 이미지 업데이트

    /**
     * user jwt 넣기
     *
     * @param file
     * @return
     */
    @Multipart
    @POST("/api/v1/users/image")
     Call<ApiResponse<UserImageVo>> updateImage(@Header("x-jwt-token") String userKey,
                                                @Part MultipartBody.Part file);


}
