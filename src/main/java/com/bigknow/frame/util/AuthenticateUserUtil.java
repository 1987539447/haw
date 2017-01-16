package com.bigknow.frame.util;

import com.bigknow.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2015/5/6.
 */
public final class AuthenticateUserUtil {

    public static String getUserId(){
        User user;
        if((user=getUser())!=null){
            return user.getId();
        }else{
            return null;
        }
    }

    public static User getUser(){
        Subject subject = SecurityUtils.getSubject();
        if(subject==null){
            return null;
        }
       return (User)subject.getPrincipal();
    }
}
