package com.woon.wisestudytest1.main.model;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.woon.wisestudytest1.main.contract.MainContract;
import com.woon.wisestudytest1.main.networking.MainApiInterface;
import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainRemoteModel {

    private MainContract.presenter presenter;
    public MainRemoteModel(MainContract.presenter presenter) {
        this.presenter = presenter;
    }

    public void getUserInformation(String userKey) {
        MainApiInterface mainApiInterface = ApiClient.getInstance().create(MainApiInterface.class);
        Call<ApiResponse<UserVo>> call = mainApiInterface.retrieveUser(userKey);

        call.enqueue(new Callback<ApiResponse<UserVo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserVo>> call, Response<ApiResponse<UserVo>> response) {
                if(response.isSuccessful() == false){
                    Log.d("User", "Failed to register");
                }else{
                    UserVo item = response.body().getMessage();
                    presenter.responseUserInformation(item);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserVo>> call, Throwable t) {
                Log.d("UserFailure", t.getMessage());
            }
        });
    }

    public void requestStudyInformation(Activity activity, String userKey, Uri uri, String category, String title, Integer limit, String description){
        RequestBody requestImage = RequestBody.create(MediaType.parse("image/*"), getPathFromUri(activity, uri));
        MultipartBody.Part body = MultipartBody.Part.createFormData("study_image", getPathFromUri(activity, uri).getName(), requestImage);

        RequestBody requestCategory = RequestBody.create(MediaType.parse("multipart/form-data"), category);
        RequestBody requestTitle = RequestBody.create(MediaType.parse("multipart/form-data"), title);
        RequestBody requestLimit = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(limit));
        RequestBody requestDescription = RequestBody.create(MediaType.parse("multipart/form-data"), description);

        MainApiInterface mainApiInterface = ApiClient.getInstance().create(MainApiInterface.class);
        Call<ApiResponse<ResponseBody>> call = mainApiInterface.registerStudyInformation(userKey, body,requestCategory,requestTitle,requestLimit,requestDescription);

        call.enqueue(new Callback<ApiResponse<ResponseBody>>() {
            @Override
            public void onResponse(Call<ApiResponse<ResponseBody>> call, Response<ApiResponse<ResponseBody>> response) {
                if(response.isSuccessful() == false){
                    Log.d("CREATE_STUDY", "register failed");
                }
                Log.d("CREATE_STUDY", "register success");
            }

            @Override
            public void onFailure(Call<ApiResponse<ResponseBody>> call, Throwable t) {
                Log.d("CREATE_STUDY", t.getMessage());
            }
        });

    }

    private File getPathFromUri(Activity activity, Uri uri) {
        Cursor cursor = activity.getContentResolver().query(uri,null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));

        File file = new File(path);
        return file;
    }
}
