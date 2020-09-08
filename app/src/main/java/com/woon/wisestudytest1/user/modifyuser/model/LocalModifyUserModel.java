package com.woon.wisestudytest1.user.modifyuser.model;


import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserImageVo;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.netwroking.UserModifyInterface;
import com.woon.wisestudytest1.user.modifyuser.presenter.ModifyUserPresenter;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalModifyUserModel implements ModifyUserContract.localModel {

    private ModifyUserContract.presenter modifyUserPresenter;

    public LocalModifyUserModel(ModifyUserPresenter modifyUserPresenter) {
        this.modifyUserPresenter = modifyUserPresenter;
    }

    @Override
    public void postPicture(Activity activity, Uri uri) {
        String realPath = getPathFromUri(activity, uri);
        //File file = new File(uri.getPath());
        File file = new File(realPath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("s3_profile_img", file.getName(), requestFile);

        UserModifyInterface userModifyInterface = ApiClient.getInstance().create(UserModifyInterface.class);
        Call<ApiResponse<UserImageVo>> call = userModifyInterface.updateImage(body);

        call.enqueue(new Callback<ApiResponse<UserImageVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserImageVo>> call, Response<ApiResponse<UserImageVo>> response) {
                System.out.println("success update Image");
            }

            @Override
            public void onFailure(Call<ApiResponse<UserImageVo>> call, Throwable t) {
                Log.d("UserImage", t.getMessage());
            }
        });
    }

    private String getPathFromUri(Activity activity, Uri uri) {
        Cursor cursor = activity.getContentResolver().query(uri,null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));

        return path;
    }

}
