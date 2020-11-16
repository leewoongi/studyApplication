package com.woon.wisestudytest1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String baseUrl = "http://ec2-3-34-134-147.ap-northeast-2.compute.amazonaws.com/";
    //private static final String baseUrl = "https://api-stage-wisestudy.herokuapp.com/";
    // 외부에 공개할 자신의 객체
    private static Retrofit retrofit = null;

    //싱글톤을 만들기위해서 생성자는 private

    private ApiClient(){};

    // retrofit 객체가 없다면 새로 생성, 있다면 기존에 생성되어 있던 레트로핏 객체를 리턴
    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().
                           baseUrl(baseUrl).
                           addConverterFactory(GsonConverterFactory.create()).
                           build();
        }

        return retrofit;
    }
}
