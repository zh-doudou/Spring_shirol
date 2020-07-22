package com.zh.shiro.service;

import com.zh.shiro.entity.SAdmin;

/**
 * (SAdmin)表服务接口
 *
 * @author makejava
 * @since 2020-07-22 21:53:32
 */
public interface SAdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    SAdmin queryByName(String name);


}