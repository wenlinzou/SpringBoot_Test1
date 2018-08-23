package com.mooc.demo.service;

import com.mooc.demo.entity.Girl;
import com.mooc.demo.mapper.GirlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @description
 * @author mqliu
 * @date 2018/8/23 8:51
 */
@Service
public class GirlService {
    @Autowired
    private GirlMapper girlMapper;

    @Transactional(rollbackFor={Exception.class})
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
}
