package com.zh.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (SRole)实体类
 *
 * @author makejava
 * @since 2020-07-22 19:31:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SRole implements Serializable {

    private String roleId;

    private String roleName;
    private List<SAuthority> sAuthorities;

}