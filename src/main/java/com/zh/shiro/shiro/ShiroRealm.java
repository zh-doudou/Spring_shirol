package com.zh.shiro.shiro;

import com.zh.shiro.dao.SAdminDao;
import com.zh.shiro.entity.SAdmin;
import com.zh.shiro.entity.SAuthority;
import com.zh.shiro.entity.SRole;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SAdminDao sAdminDao;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        //User("1","zh","c3f2b09474f65a0bb8eda78e3682955f","abcd")

        AuthenticationInfo info = null;
        if (username.equals("zh")) {
            info = new SimpleAuthenticationInfo(
                    username,
                    "c3f2b09474f65a0bb8eda78e3682955f",
                    ByteSource.Util.bytes("abcd"),
                    this.getName()
            );
        }
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权");
        String userName = (String) principals.getPrimaryPrincipal();
        SAdmin zh = sAdminDao.queryByName(userName);
        List<SRole> sRoles = zh.getSRoles();
        //根据用户查询角色
        List<String> roles = new ArrayList<>();
        //根据角色查询权限
        List<String> permissions = new ArrayList<>();

        for (SRole sRole : sRoles) {
            System.out.println(sRole);
            //遍历所有角色
            roles.add(sRole.getRoleName());
            List<SAuthority> sAuthorities = sRole.getSAuthorities();

            for (SAuthority sAuthority : sAuthorities) {
                System.out.println("当前角色：" + sRole.getRoleName() + "所拥有的权限-" + sAuthority.getAuthorityName());
                System.out.println(sAuthority);
                //遍历当前角色下的权限
                permissions.add(sAuthority.getAuthorityName());
            }
        }
        SimpleAuthorizationInfo info = null;
        if (userName.equals(zh.getUsername())) {
            info = new SimpleAuthorizationInfo();
            info.addRoles(roles);
            info.addStringPermissions(permissions);
        }
        return info;
    }

}
