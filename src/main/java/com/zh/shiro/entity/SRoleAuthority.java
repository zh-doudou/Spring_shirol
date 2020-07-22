package com.zh.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SRoleAuthority)实体类
 *
 * @author makejava
 * @since 2020-07-22 19:31:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SRoleAuthority implements Serializable {

    private String id;

    private String roleId;

    private String authorityId;


}