package com.zh.shiro.dao;

import com.zh.shiro.entity.SAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * (SAdmin)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-22 19:43:38
 */
public interface SAdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    SAdmin queryByName(@Param("username") String username);


}