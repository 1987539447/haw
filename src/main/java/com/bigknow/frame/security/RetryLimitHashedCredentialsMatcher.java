package com.bigknow.frame.security;

import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.frame.util.SpringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重试次数验证类,继承HashedCredentialsMatcher,能够验证密码.
 * 此类需要从新实现 doCredentialsMatch方法
 * 默认重试5次密码失败后锁定账户1小时
 * Created by Administrator on 2015/4/1.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    /**
     * 将shiro的cacheManager注入进来,用于临时存储登陆消息.
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * 采用Spring的工具类获取到ExcessiveAttemptsException 的消息;
     */
    @Autowired
    private SpringUtils springUtils;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        String userName = ((UsernamePasswordToken)token).getUsername();

        AtomicInteger retrytimes = (AtomicInteger)this.getCache().get(userName);
        if(retrytimes==null){
            retrytimes = new AtomicInteger(0);
            this.getCache().put(userName,retrytimes);
        }
        if(retrytimes.incrementAndGet()>5){
            throw new ExcessiveAttemptsException(springUtils.getMessage(GlobalStatic.ExcessiveAttemptsException));
        }

        boolean returnValue = super.doCredentialsMatch(token,info);

        if(returnValue){
            this.getCache().remove(userName);
        }
        return returnValue;
    }

    private Cache getCache(){
        return  cacheManager.getCache(GlobalStatic.RETRY_CACHE_NAME);
    }
}
