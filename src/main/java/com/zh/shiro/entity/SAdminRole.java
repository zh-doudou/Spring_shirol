package com.zh.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SAdminRole)实体类
 *
 * @author makejava
 * @since 2020-07-22 19:31:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SAdminRole implements Serializable {

    private String id;

    private String adminId;

    private String roleId;

}