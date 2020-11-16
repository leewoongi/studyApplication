package com.woon.wisestudytest1.user.Entity;

import java.io.File;

import okhttp3.MultipartBody;

public class UserImageVo {
    private Integer id;
    private MultipartBody.Part s3_profile_img;

    public UserImageVo(Integer id, MultipartBody.Part s3_profile_img) {
        this.id = id;
        this.s3_profile_img = s3_profile_img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartBody.Part getS3_profile_img() {
        return s3_profile_img;
    }

    public void setS3_profile_img(MultipartBody.Part s3_profile_img) {
        this.s3_profile_img = s3_profile_img;
    }
}
