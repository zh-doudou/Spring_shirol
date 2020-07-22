package com.zh.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@Controller
public class UserController {

    /**
     * @param username   用户名字
     * @param password   用户密码
     * @param rememberme 是否选择记住我登录
     */
    @RequestMapping("login")
    public String login(String username, String password, Integer rememberme) {

        //根据安全工具类获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建token对象 参数：身份信息，凭证信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        if (rememberme != null && rememberme == 1) {
            //如果获取的标识成立那么就为记住当前主体
            token.setRememberMe(true);
        }

        //认证
        try {
            subject.login(token);
            boolean authenticated = subject.isAuthenticated();
            System.out.println("认证状态：" + authenticated);
            return "redirect:/main/main.jsp";
        } catch (UnknownAccountException e) {
            System.out.println("未知的账户异常 用户名不正确");
            return "redirect:/login/login.jsp";
        } catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常  密码错误");
            return "redirect:/login/login.jsp";
        }
    }

/*    @RequestMapping("logout")
    public String logout() {
        //根据安全工具类获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //退出
        subject.logout();
        return "redirect:/login/login.jsp";
    }*/

}
