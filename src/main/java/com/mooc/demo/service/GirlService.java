package com.mooc.demo.service;

import com.mooc.demo.entity.Girl;
import com.mooc.demo.enums.ResultEnum;
import com.mooc.demo.exception.GirlException;
import com.mooc.demo.mapper.GirlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * @author mqliu
 * @description
 * @date 2018/8/23 8:51
 */
@Service
public class GirlService {
    @Autowired
    private GirlMapper girlMapper;

    @Transactional(rollbackFor = {Exception.class})
    public void initTwo() {
        Girl girl1 = new Girl();
        girl1.setAge(24);
        girl1.setSize("C");
        girlMapper.save(girl1);

        Girl girl2 = new Girl();
        girl2.setAge(25);
        girl2.setSize("DD");
        girlMapper.save(girl2);

    }

    public void getAge(Integer id) {
        Optional<Girl> optional = girlMapper.findById(id);
        Girl girl = optional.orElseGet(Girl::new);
        Integer age = girl.getAge();
        if (age < 10) {
            //返回你还在上小学吧
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            //返回你可能在上初中
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /**
     * 通过id查询一个女生信息
     * @param id
     * @return
     */
    public Girl findOne(Integer id) {
        Optional<Girl> optional = girlMapper.findById(id);
        return optional.orElseGet(Girl::new);
    }
}
