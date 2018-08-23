package com.mooc.demo;

import com.mooc.demo.entity.Girl;
import com.mooc.demo.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @author mqliu
 * @date 2018/8/23 16:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest() {
        Girl girl = girlService.findOne(23);
        //断言
        Assert.assertEquals(new Integer(25), girl.getAge());
    }
}
