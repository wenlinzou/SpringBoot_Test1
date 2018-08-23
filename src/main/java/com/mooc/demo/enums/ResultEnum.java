package com.mooc.demo.enums;

/**
 * @description
 * @author mqliu
 * @date 2018/8/23 15:40
 */
public enum ResultEnum {
    UNKOWN_ERROR(-1, "未知错误")
    , SUCCESS(0, "成功")
    , PRIMARY_SCHOOL(100, "你还在上小学吧")
    , MIDDLE_SCHOOL(101, "你可能在上初中")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
