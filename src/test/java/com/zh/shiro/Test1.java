package com.zh.shiro;

import com.zh.shiro.dao.SAdminDao;
import com.zh.shiro.entity.SAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = ShiroApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {
    @Resource
    private SAdminDao sAdminDao;

    @Test
    public void select() {
        SAdmin zh = sAdminDao.queryByName("zh");
        System.out.println(zh);

      /*

        SAdmin(
        adminId=1, username=zh, password=a2c9ec06b8c0a2be811dfd47be6e5f82, salt=asdfaf,
        sRoles=[
                        /拥有的角色
           SRole(roleId=2, roleName=superAdmin,
                    角色下的权限
                sAuthorities=[
                    SAuthority(authorityId=null, authorityName=admin:query),
                    SAuthority(authorityId=null, authorityName=admin:delete),
                    SAuthority(authorityId=null, authorityName=admin:update),
                    SAuthority(authorityId=null, authorityName=admin:insert)]),

         SRole(roleId=3, roleName=admin,
                sAuthorities=[
                    SAuthority(authorityId=null, authorityName=user:select),
                    SAuthority(authorityId=null, authorityName=user:update)]),
          SRole(roleId=1, roleName=common,
                sAuthorities=[]),
          SRole(roleId=4, roleName=user,
                sAuthorities=[])])

         */
    }
}
