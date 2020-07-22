package com.zh.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SAuthority)实体类
 *
 * @author makejava
 * @since 2020-07-22 19:31:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SAuthority implements Serializable {

    private String authorityId;

    private String authorityName;


}