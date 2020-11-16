package com.woon.wisestudytest1.user.Entity;

public class UpdateUserVo {
    private String name;
    private Integer age;
    private String cellphone;
    private String description;

    public UpdateUserVo(String name, Integer age, String cellphone, String description) {
        this.name = name;
        this.age = age;
        this.cellphone = cellphone;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
