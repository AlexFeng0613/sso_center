package com.hsjc.central.authrealm;

import com.hsjc.central.domain.UserMain;
import com.hsjc.central.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author : zga
 * @date : 2015-11-24
 */
public class MyAuthRealm extends AuthorizingRealm {

    ApplicationContext applicationContext;

    @Resource
    UserService userService;

    public void setAc(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public UserService getUserService() {
        return (userService == null) ? (UserService)applicationContext.getBean("userService") : userService;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        UserMain userMain = userService.findByEmail(username);
        if (userMain == null) {
            throw new UnknownAccountException("No account found for user[" + token.getUsername() + "]");
        }

        return new SimpleAuthenticationInfo(userMain, userMain.getPassword(),
                ByteSource.Util.bytes(userMain.getCredentialsSalt()),getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserMain userMain = (UserMain) principalCollection.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(userMain.getType());
        return info;
    }
}
