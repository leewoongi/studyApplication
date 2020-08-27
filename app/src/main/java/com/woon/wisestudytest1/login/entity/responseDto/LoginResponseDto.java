package com.woon.wisestudytest1.login.entity.responseDto;

import com.woon.wisestudytest1.login.entity.LoginVo;

public class LoginResponseDto {
    private Integer code;
    private String status;
    private LoginVo message;

    public LoginResponseDto(Integer code, String status, LoginVo message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginVo getMessage() {
        return message;
    }

    public void setMessage(LoginVo message) {
        this.message = message;
    }
}
