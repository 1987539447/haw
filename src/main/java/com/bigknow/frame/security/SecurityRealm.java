package com.bigknow.frame.security;

import com.bigknow.frame.security.exception.AuthenticationNotUseException;
import com.bigknow.frame.util.Assert;
import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.system.entity.User;
import com.bigknow.system.service.IDelegatingService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用于shiro 验证用户和权限的自定制realm
 * Created by Administrator on 2015/3/10.
 */
@Component("securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private IDelegatingService delegatingService;

    private Logger logger = LoggerFactory.getLogger(this.getName());

    /**
     * 配置密码验证类,默认我实现了 RetryLimitHashedCredentialsMatcher
     * bean id 为credentialsMatcher,见applicationContext-parent-security.xml
     * @param credentialsMatcher
     */
    @Autowired
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 如果非正常退出，由于没有显式调用 SecurityUtils.getSubject().logout()，
        // 造成一定时间内授权认证信息还存在于缓存中，所以必须判断进行清除缓存操作。
        // SecurityUtils.getSubject() 采用threadlocal方式存储了subject，所以
        // 可以得到当前的subject。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }

        User user = (User) principals.getPrimaryPrincipal();
        Assert.assertNotNull(user, AuthorizationException.class);
        SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
        sazi.setRoles(delegatingService.getRolesAsSet(user.getId()));
        sazi.setStringPermissions(delegatingService.getPermissionsAsSet(user.getId()));
        return sazi;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userAccount = ((UsernamePasswordToken) token).getUsername();
        logger.debug("getCredentials-->password:  "+new String((char[])token.getCredentials()));
        User user = delegatingService.findLoginUserAccount(userAccount);
        Assert.assertNotNull(user, AuthenticationException.class);
        if("否".equals(user.getState())){
            throw new AuthenticationNotUseException();
        }
        //SimpleAuthenticationInfo实现了 SaltedAuthenticationInfo接口,支持加salt的密码验证;
        return new SimpleAuthenticationInfo(
                user, user.getPassword(), ByteSource.Util.bytes(user.getId() + GlobalStatic.SECURITY_SALT), this.getName());
    }
}
