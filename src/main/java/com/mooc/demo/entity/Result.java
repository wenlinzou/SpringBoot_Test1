package com.mooc.demo.entity;

/**
 * @description
 * @author mqliu
 * @date 2018/8/23 11:22
 */
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
