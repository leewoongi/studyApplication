package com.woon.wisestudytest1.user.Entity;

import okhttp3.MultipartBody;

public class UserImageVo {
    MultipartBody.Part imageFile;

    public UserImageVo(MultipartBody.Part imageFile) {
        this.imageFile = imageFile;
    }

    public MultipartBody.Part getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartBody.Part imageFile) {
        this.imageFile = imageFile;
    }
}
