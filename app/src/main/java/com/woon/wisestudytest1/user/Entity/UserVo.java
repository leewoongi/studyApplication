package com.woon.wisestudytest1.user.Entity;

public class UserVo {
    private String email;
    private String name;
    private Integer age;
    private String cellphone;
    private String gender;
    private String categories;

    public UserVo(String email, String name, Integer age, String cellphone, String gender, String categories) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.cellphone = cellphone;
        this.gender = gender;
        this.categories = categories;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
