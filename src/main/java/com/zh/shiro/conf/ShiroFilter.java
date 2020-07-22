package com.zh.shiro.conf;

import com.zh.shiro.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 配置类
 *
 * @author 张辉
 */
@Configuration
public class ShiroFilter {
    /**
     * 将Shiro的过滤器工厂交给Spring工厂管理
     *
     * @param securityManager 将安全管理器注入到过滤器工厂中
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        //创建过滤器工厂
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //将Shiro安全管理器交给过滤器工厂
        shiroFilter.setSecurityManager(securityManager);
        //定义过滤规则
        HashMap<String, String> map = new HashMap<>();
        /**
         * 定义过滤规则  (要过滤的资源,过滤器)
         *  anon：org.apache.shiro.web.filter.authc.AnonymousFilter   匿名过滤器
         * authc： org.apache.shiro.web.filter.authc.FormAuthenticationFilter  认证过滤器
         * map.put("/**","anon"); //anon   过滤器的简称  匿名过滤器  不用认证都可以访问
         */
        //authc  认证过滤器  只有认证成功才能访问的资源
        map.put("/**", "authc");
        //放行登录方法
        map.put("/user/login", "anon");
        //放行主页面展示
        map.put("/main/main.jsp", "anon");
        //器中具体的退出代码Shiro已经替我们做了实现 --退出过滤器
        map.put("/logout", "logout");
        //记住我过滤器  用户可以访问其他的资源
        map.put("/**", "user");
        //设置过滤器链
        shiroFilter.setFilterChainDefinitionMap(map);
        //自定义登录页面
        shiroFilter.setLoginUrl("/user/login.jsp");
        return shiroFilter;
    }

    /**
     * 将安全管理器交给Spring工厂管理
     *
     * @param realm          将自定义的领域注入到 安全管理器
     * @param sessionManager 会话管理器
     * @return 返回一个安全管理器交过滤器工厂
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroRealm realm,
                                                                  DefaultWebSessionManager sessionManager,
                                                                  CookieRememberMeManager rememberMeManager,
                                                                  CacheManager cacheManager
    ) {
        //创建安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将realm交给安全管理器
        securityManager.setRealm(realm);
        //将会话管理器交给安全管理器
        System.out.println("准备依赖注入session");
        securityManager.setSessionManager(sessionManager);
        //记住我管理器 交给安全管理器
        securityManager.setRememberMeManager(rememberMeManager);
        //缓存管理器 交给安全管理器
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    /**
     * @param credentialsMatcher 凭证匹配器
     * @return 将自定义Realm装配好交给Spring工厂管理
     */
    @Bean
    public ShiroRealm getShiroRealm(HashedCredentialsMatcher credentialsMatcher) {
        ShiroRealm realm = new ShiroRealm();

        //将凭证匹配器交给自定义Realm
        realm.setCredentialsMatcher(credentialsMatcher);

        return realm;
    }

    /**
     * 将凭证匹配器交给Spring工厂管理
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        //创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //加密算法
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }

    /**
     * 将默认Web会话管理器交给spring管理
     *
     * @return 返回一个会话管理器交给安全管理器
     */

    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        //org.apache.shiro.web.session.mgt.DefaultWebSessionManager;导包切记
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session的过期时间  参数：毫秒  20分钟
        sessionManager.setGlobalSessionTimeout((1 * 1000) * 60 * 20);
        return sessionManager;
    }

    /**
     * 将CookieRememberMeManager记住我管器交给spring管理
     *
     * @param cookie 简单的Cookie
     * @return 返回一个记住我管理器交给安全管理器
     */
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(SimpleCookie cookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        //将cook交给记住我管理器
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }

    /**
     * 返回一个简单的Cookie
     *
     * @return 返回一个简单的Cookie交给记住我管理器
     */
    @Bean
    public SimpleCookie getSimpleCookie() {
        SimpleCookie cookie = new SimpleCookie();
        //cookie的名称 对应的是前端checxkbox中的name属性的值  name="rememberme"
        cookie.setName("rememberme");
        //设置记住我cook的失效时间 参数：秒
        cookie.setMaxAge(60 * 2);
        return cookie;
    }

    /**
     * 将缓存管理器交给spring管理
     *
     * @return 返回cacheManager管理器 交给安全管理器
     */
    @Bean
    public CacheManager getCacheManager() {
        CacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }
}

