package com.zh.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


public class TestShiro {
    @Test
    public void shiro() {
        //创建安全管理工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //通过安全管理工厂获取安全管理器
        SecurityManager securityManager = iniSecurityManagerFactory.createInstance();
        //讲安全管理器交给安全管理工具类
        SecurityUtils.setSecurityManager(securityManager);
        //通过安全管理类获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zh", "123");
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账户异常  用户名不不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常，密码不对");
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证结果：" + authenticated);
        if (authenticated) {
            //授权  判断主体是否有这个角色
            boolean b = subject.hasRole("admin");
            System.out.println("判断是否有这个角色结果：" + b);
            //判断主体是否拥有这些角色
           /*
           boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "sAdmin"));
            for (boolean aBoolean : booleans) {
                System.out.println("当前主身份拥有的角色" + aBoolean);

            }*/
            //判断主体是否有这些角色 一个不符合也不行
           /*
            boolean b = subject.hasAllRoles(Arrays.asList("admin", "sAdmin", "aa"));
            System.out.println("判断是否有这些角色：" + b);*/

            //判断主体是否有该权限
            boolean permitted = subject.isPermitted("user:select");
            System.out.println("主体是否有这个权限：" + permitted);

        }
    }

    @Test
    public void jiami() {
        Md5Hash md5Hash = new Md5Hash("123", "abcd", 1024);
        String s = md5Hash.toHex();
        System.out.println(s);

    }
}
