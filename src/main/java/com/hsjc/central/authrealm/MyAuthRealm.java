package com.hsjc.central.authrealm;

import com.hsjc.central.domain.User;
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
 * Created by 源 on 2014/12/25.
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
        String userId = token.getUsername();
        User user = userService.findById(userId);
        if (user == null) {
            throw new UnknownAccountException("No account found for user[" + token.getUsername() + "]");
        }

        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(getAdminType(user.getAdminType()));
        return info;
    }

    private String getAdminType(int type) {
        if (type ==1 ) {
            return "Super";
        } else if (type == 2) {
            return "School";
        } else if (type == 3) {
            return "Normal";
        }
        return "None";
    }

}
