package com.zh.config.shiro;

import com.zh.pojo.User;
import com.zh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


//自定义 UserRealm extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService service;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //得到当前登录对象
        Subject subject = SecurityUtils.getSubject();
        //拿到user对象
        User user = (User) subject.getPrincipal();

        //设置当前用户的权限
        info.addStringPermission(user.getPerms());

        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");

        //获取用户令牌
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //连接数据库
        User user = service.findByName(userToken.getUsername());

        if (user == null){
            //return null; //抛出异常 UnknownAccountException
            throw new LockedAccountException();
        }


        /**
         * 密码认证 shiro做
         * 密码加密shiro有默认的加密方式SimpleCredentialsMatcher
         *
         * CredentialsMatcher实现类可查看加密方式
         *
         * SimpleAuthenticationInfo可以传入四个参数
         * 第一个参数：传入的都是com.java.entity包下的User类的user对象。
         * 第二个参数:  传入的是从数据库中获取到的password，然后再与token中的password进行对比，匹配上了就通过，匹配不上就报异常。
         * 第三个参数，盐–用于加密密码对比。 若不需要，则可以设置为空 “ ”
         * 第四个参数：当前realm的名字。
         */
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
