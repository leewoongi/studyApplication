package com.woon.wisestudytest1.user.Entity;

import okhttp3.MultipartBody;

public class StudyVo {

    private String category;
    private String title;
    private Integer limit;
    private String description;
    private MultipartBody.Part study_image;

    public StudyVo(String category, String title, Integer limit, String description, MultipartBody.Part study_image) {
        this.category = category;
        this.title = title;
        this.limit = limit;
        this.description = description;
        this.study_image = study_image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartBody.Part getStudy_image() {
        return study_image;
    }

    public void setStudy_image(MultipartBody.Part study_image) {
        this.study_image = study_image;
    }
}
