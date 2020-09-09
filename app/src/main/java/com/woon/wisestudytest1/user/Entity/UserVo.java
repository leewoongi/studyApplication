package com.woon.wisestudytest1.user.Entity;

import java.util.List;

public class UserVo {
    private Integer user_id;
    private String email;
    private String name;
    private Integer age;
    private String cellphone;
    private String gender;
    private String description;
    private String categories;
    private String kakao_profile_img;
    private String s3_profile_img;
    private boolean img_flag;
    private List<UserStudies> study_list;

    public UserVo(Integer user_id, String email, String name, Integer age, String cellphone, String gender, String description, String categories, String kakao_profile_img, String s3_profile_img, boolean img_flag, List<UserStudies> study_list) {
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.age = age;
        this.cellphone = cellphone;
        this.gender = gender;
        this.description = description;
        this.categories = categories;
        this.kakao_profile_img = kakao_profile_img;
        this.s3_profile_img = s3_profile_img;
        this.img_flag = img_flag;
        this.study_list = study_list;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getKakao_profile_img() {
        return kakao_profile_img;
    }

    public void setKakao_profile_img(String kakao_profile_img) {
        this.kakao_profile_img = kakao_profile_img;
    }

    public String getS3_profile_img() {
        return s3_profile_img;
    }

    public void setS3_profile_img(String s3_profile_img) {
        this.s3_profile_img = s3_profile_img;
    }

    public boolean isImg_flag() {
        return img_flag;
    }

    public void setImg_flag(boolean img_flag) {
        this.img_flag = img_flag;
    }

    public List<UserStudies> getStudy_list() {
        return study_list;
    }

    public void setStudy_list(List<UserStudies> study_list) {
        this.study_list = study_list;
    }
}
