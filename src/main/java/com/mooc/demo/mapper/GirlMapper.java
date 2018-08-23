package com.mooc.demo.mapper;

import com.mooc.demo.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author mqliu
 * @date 2018/8/22 18:08:59
 */
public interface GirlMapper extends JpaRepository<Girl, Integer> {
    /**
     * 通过年龄查询
     */
    List<Girl> findByAge(Integer age);
}
