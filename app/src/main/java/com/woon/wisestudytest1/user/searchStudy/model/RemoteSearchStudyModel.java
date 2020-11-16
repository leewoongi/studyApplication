package com.woon.wisestudytest1.user.searchStudy.model;

import android.util.Log;

import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.searchStudy.networking.SearchStudyInterface;
import com.woon.wisestudytest1.user.searchStudy.presenter.SearchStudyPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class RemoteSearchStudyModel {

    private SearchStudyPresenter presenter;
    public RemoteSearchStudyModel(SearchStudyPresenter searchStudyPresenter) {
        this.presenter = searchStudyPresenter;
    }

    public void getSearchStudy(){
        SearchStudyInterface searchStudyInterface = ApiClient.getInstance().create(SearchStudyInterface.class);
        Call<ApiResponse<List<DetailStudyVo>>> call = searchStudyInterface.getAllStudies();

        call.enqueue(new Callback<ApiResponse<List<DetailStudyVo>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DetailStudyVo>>> call, Response<ApiResponse<List<DetailStudyVo>>> response) {
                if(response.isSuccessful() == false){
                    Log.d("searchStudy", "fAil to load");
                }else{
                    List<DetailStudyVo> data = response.body().getMessage();
                    presenter.responseSearchStudy(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DetailStudyVo>>> call, Throwable t) {
                Log.d("searchStudy", t.getMessage());
            }
        });
    }

    public void getSpecificSearchStudy(String field){

        SearchStudyInterface searchStudyInterface = ApiClient.getInstance().create(SearchStudyInterface.class);
        Call<ApiResponse<List<DetailStudyVo>>> call = searchStudyInterface.getSpecificStudies(field);

        call.enqueue(new Callback<ApiResponse<List<DetailStudyVo>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DetailStudyVo>>> call, Response<ApiResponse<List<DetailStudyVo>>> response) {
                if(response.isSuccessful() == false){
                    Log.d("specificStudies", "failed to load" );
                }else{
                    List<DetailStudyVo> data = response.body().getMessage();
                    presenter.responseSelectFieldStudy(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DetailStudyVo>>> call, Throwable t) {
                Log.d("specificStudies", t.getMessage());
            }
        });

    }
}
