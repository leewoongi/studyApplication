package com.woon.wisestudytest1.user.modifyuser.model;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.woon.wisestudytest1.login.entity.LoginVo;
import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserImageVo;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.netwroking.UserModifyInterface;
import com.woon.wisestudytest1.util.TokenManager;

import java.io.File;
import retrofit2.Call;

public class RemoteModifyUserModel implements ModifyUserContract.remoteModel {
    private ModifyUserContract.presenter presenter;

    public RemoteModifyUserModel(ModifyUserContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void uploadImage(Activity activity, Uri uri) {

        //jwt 토큰 가져오기
        String userKey = TokenManager.read(activity);

        //uri 경로 file로 바꾸기
        String uToF = getPathFromUri(activity, uri);
        System.out.println("uri -> file : " + uToF );
        File file = new File(uToF);
        System.out.println("file : " + file );

        //이미지를 requsetbody 타입으로 만들고 그걸 다시 mutilpartbodyd.part 타입으로 변경
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        //통신부
        UserModifyInterface userModifyInterface = ApiClient.getInstance().create(UserModifyInterface.class);
        Call<ApiResponse<UserImageVo>> call = userModifyInterface.upLoadingImage(userKey, body);
        call.enqueue(new Callback<ApiResponse<UserImageVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserImageVo>> call, Response<ApiResponse<UserImageVo>> response) {

            }

            @Override
            public void onFailure(Call<ApiResponse<UserImageVo>> call, Throwable t) {

            }
        });

    }

    //uri로 받아온 걸 실제경로 file 로 변경
    private String getPathFromUri(Activity activity, Uri uri) {
        Cursor cursor = activity.getContentResolver().query(uri,null,null,null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex((MediaStore.MediaColumns.DATA)));

        return path;
    }

    @Override
    public void putUserInformation(String userKey) {

    }
}
