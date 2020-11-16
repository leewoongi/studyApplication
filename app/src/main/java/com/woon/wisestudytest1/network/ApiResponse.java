package com.woon.wisestudytest1.network;

public class ApiResponse<T> {
    private Integer code;
    private String status;
    private T message;

    public ApiResponse(Integer code, String status, T message) {
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

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
