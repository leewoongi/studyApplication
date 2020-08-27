package com.woon.wisestudytest1.login.entity;

import com.woon.wisestudytest1.util.ApiResponse;

public class LoginVo {
    private String jwt;

    public LoginVo(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
