package com.mooc.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author mqliu
 * @date 2018/8/22 15:01
 */
@Component
@ConfigurationProperties(prefix = "girl")
@PropertySource("classpath:girl-${spring.profiles.active}.properties")
public class GirlConfig {
    private String size;
    private Integer age;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
