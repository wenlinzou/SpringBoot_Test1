package com.mooc.demo.controller;

import com.mooc.demo.entity.Girl;
import com.mooc.demo.entity.Result;
import com.mooc.demo.mapper.GirlMapper;
import com.mooc.demo.service.GirlService;
import com.mooc.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author mqliu
 * @description
 * @date 2018/8/22 18:07
 */
@RestController
public class GirlController {

    private static final Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlMapper girlMapper;

    @Autowired
    private GirlService girlService;

    @GetMapping(value = "/getList")
    public List<Girl> getList() {
        return girlMapper.findAll();
    }

    /**
     * 添加一个女生
     *
     * @param girl
     * @return
     */
    @PostMapping(value = "/save")
    public Result<Girl> save(@Validated Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlMapper.save(girl));
    }

    @GetMapping(value = "/get/{id}")
    public Girl get(@PathVariable("id") Integer id) {
        Optional<Girl> optional = girlMapper.findById(id);
        Girl girl = optional.orElseGet(Girl::new);
        return girl;
    }

    @PutMapping(value = "/update/{id}")
    public Girl update(@PathVariable("id") Integer id, @RequestParam("age") Integer age, @RequestParam("size") String size) {
        Girl girl = new Girl();
        girl.setSize(size);
        girl.setAge(age);
        girl.setId(id);
        return girlMapper.save(girl);
    }

    @DeleteMapping(value = "/del/{id}")
    public void del(@PathVariable("id") Integer id) {
        Girl girl = new Girl();
        girl.setId(id);
        girlMapper.delete(girl);
    }

    @GetMapping(value = "/find/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age) {
        return girlMapper.findByAge(age);
    }

    @PostMapping(value = "/two")
    public void two() {
        girlService.initTwo();
    }

    @GetMapping(value = "/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) {
        girlService.getAge(id);
    }


}
