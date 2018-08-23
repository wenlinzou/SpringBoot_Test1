package com.mooc.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @description
 * @author mqliu
 * @date 2018/8/22 16:23
 */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Girl implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String size;

    private Integer age;

    /**
     * 不生成会报错
     */
    public Girl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
