package com.mooc.demo.util;

import com.mooc.demo.entity.Result;

/**
 * @description
 * @author mqliu
 * @date 2018/8/23 11:26
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setData(object);
        result.setMsg("success");
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
