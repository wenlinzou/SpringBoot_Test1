package com.mooc.demo.exception;

import com.mooc.demo.enums.ResultEnum;

/**
 * @description
 * @author mqliu
 * @date 2018/8/23 15:03
 * springboot对Exception不回滚，RuntimeException会回滚
 */
public class GirlException extends RuntimeException {

    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
