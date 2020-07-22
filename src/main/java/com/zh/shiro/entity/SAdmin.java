package com.zh.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SAdmin implements Serializable {

    private String adminId;

    private String username;

    private String password;

    private String salt;
    private List<SRole> sRoles;

}