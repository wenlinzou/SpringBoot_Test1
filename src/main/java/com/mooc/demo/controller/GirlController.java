package com.mooc.demo.controller;

import com.mooc.demo.entity.Girl;
import com.mooc.demo.mapper.GirlMapper;
import com.mooc.demo.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description
 * @author mqliu
 * @date 2018/8/22 18:07
 */
@RestController
public class GirlController {
    @Autowired
    private GirlMapper girlMapper;

    @Autowired
    private GirlService girlService;

    @GetMapping(value = "/getList")
    public List<Girl> getList() {
        return girlMapper.findAll();
    }

    @PostMapping(value = "/save")
    public Girl save(@RequestParam("age") Integer age, @RequestParam("size")String size) {
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setSize(size);
        return girlMapper.save(girl);
    }

    @GetMapping(value="/get/{id}")
    public Girl get(@PathVariable("id")Integer id) {
        Girl girl = null;
        try {
            girl = girlMapper.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return girl;
    }

    @PutMapping(value="/update/{id}")
    public Girl update(@PathVariable("id")Integer id,@RequestParam("age") Integer age, @RequestParam("size")String size) {
        Girl girl = new Girl();
        girl.setSize(size);
        girl.setAge(age);
        girl.setId(id);
        return girlMapper.save(girl);
    }

    @DeleteMapping(value = "/del/{id}")
    public void del(@PathVariable("id")Integer id) {
        Girl girl = new Girl();
        girl.setId(id);
        girlMapper.delete(girl);
    }

    @GetMapping(value = "/find/age/{age}")
    public List<Girl> findByAge(@PathVariable("age")Integer age) {
        return girlMapper.findByAge(age);
    }

    @PostMapping(value = "/two")
    public void two() {
        girlService.initTwo();
    }


}
