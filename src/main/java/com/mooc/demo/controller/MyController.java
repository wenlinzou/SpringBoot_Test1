package com.mooc.demo.controller;

import com.mooc.demo.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description
 * @author mqliu
 * @date 2018/8/22 14:58
 */
@RestController
public class MyController {

    @Autowired
    GirlConfig girlConfig;

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello " + girlConfig.getAge() + " " + girlConfig.getSize();
    }

    @GetMapping(value = {"/girl", "/girl1"})
    public String girl() {
        return girlConfig.getAge() + " " + girlConfig.getSize();
    }

    @GetMapping(value = "/test/{id}")
    public String test(@PathVariable("id")String id) {
        return id;
    }

    @GetMapping(value = "/test02")
    public String test02(@RequestParam(value = "id", defaultValue = "123", required = true)String id) {
        return id;
    }


}
