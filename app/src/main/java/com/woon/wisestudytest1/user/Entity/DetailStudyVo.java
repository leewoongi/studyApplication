package com.woon.wisestudytest1.user.Entity;

import okhttp3.MultipartBody;

public class DetailStudyVo {

    private Integer study_id;
    private String category;
    private String title;
    private Integer limit;
    private String description;
    private String study_image;

    public DetailStudyVo(Integer study_id, String category, String title, Integer limit, String description, String study_image) {
        this.study_id = study_id;
        this.category = category;
        this.title = title;
        this.limit = limit;
        this.description = description;
        this.study_image = study_image;
    }

    public Integer getStudy_id() {
        return study_id;
    }

    public void setStudy_id(Integer study_id) {
        this.study_id = study_id;
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

    public String getStudy_image() {
        return study_image;
    }

    public void setStudy_image(String study_image) {
        this.study_image = study_image;
    }
}
