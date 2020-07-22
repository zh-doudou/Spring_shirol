package com.zh.shiro.service.impl;

import com.zh.shiro.dao.SAdminDao;
import com.zh.shiro.entity.SAdmin;
import com.zh.shiro.service.SAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (SAdmin)表服务实现类
 *
 * @author makejava
 * @since 2020-07-22 21:53:32
 */
@Service("sAdminService")
public class SAdminServiceImpl implements SAdminService {
    @Resource
    private SAdminDao sAdminDao;

    @Override
    public SAdmin queryByName(String name) {
        SAdmin sAdmin = sAdminDao.queryByName(name);
        return sAdmin;
    }

}