package com.zh.shiro.controller;

import com.zh.shiro.service.SAdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (SAdmin)表控制层
 *
 * @author makejava
 * @since 2020-07-22 19:31:26
 */
@RestController
@RequestMapping("sAdmin")
public class SAdminController {
    /**
     * 服务对象
     */
    @Resource
    private SAdminService sAdminService;


}