package com.woon.wisestudytest1.user.createstudy.model;

import android.util.Log;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.StudyVo;
import com.woon.wisestudytest1.user.createstudy.contract.CreateStudyContract;
import com.woon.wisestudytest1.user.createstudy.networking.CreateStudyInterface;
import com.woon.wisestudytest1.user.createstudy.presenter.CreateStudyPresenter;
import com.woon.wisestudytest1.util.SuccessCallback;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteModelCreateStudy implements CreateStudyContract.remoteModel {

    private CreateStudyPresenter presenter;
    private SuccessCallback successCallback;

    public RemoteModelCreateStudy(CreateStudyPresenter presenter, SuccessCallback successCallback) {
        this.presenter = presenter;
        this.successCallback = successCallback;
    }


    @Override
    public void requestStudyInformation(String userKey, StudyVo studyVo) {

        RequestBody requestCategory = RequestBody.create(MediaType.parse("multipart/form-data"), studyVo.getCategory());
        RequestBody requestTitle = RequestBody.create(MediaType.parse("multipart/form-data"), studyVo.getTitle());
        RequestBody requestLimit = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(studyVo.getLimit()));
        RequestBody requestDescription = RequestBody.create(MediaType.parse("multipart/form-data"), studyVo.getDescription());

        CreateStudyInterface createStudyInterface = ApiClient.getInstance().create(CreateStudyInterface.class);
        Call<ApiResponse<ResponseBody>> call = createStudyInterface.registerStudyInformation(userKey, studyVo.getStudy_image(),
                                                                                             requestCategory, requestTitle, requestLimit, requestDescription);
        call.enqueue(new Callback<ApiResponse<ResponseBody>>() {
            @Override
            public void onResponse(Call<ApiResponse<ResponseBody>> call, Response<ApiResponse<ResponseBody>> response) {
                if(response.isSuccessful() == false){
                    Log.d("StudyUpdate","Failed to StudyUpdate");
                }

                presenter.onSuccess();
            }

            @Override
            public void onFailure(Call<ApiResponse<ResponseBody>> call, Throwable t) {
                Log.d("CreateStudyFailure", t.getMessage());
            }
        });

    }
}
