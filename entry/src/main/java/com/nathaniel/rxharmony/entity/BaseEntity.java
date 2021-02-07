package com.nathaniel.rxharmony.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseEntity<T> implements Serializable {
    @SerializedName(value = "code", alternate = {"status", "Code", "code"})
    private int code;
    @SerializedName(value = "message", alternate = {"message", "msg"})
    private String message;
    private T data;

    public BaseEntity() {
    }

    public BaseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
