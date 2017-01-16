package com.bigknow.system.service;

import com.bigknow.frame.service.ICRUDService;
import com.bigknow.system.entity.User;

/**
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:03:00
 */
public interface IUserService extends ICRUDService<User> {

    /**
     * 获取登陆的用户,如果不存在就抛出异常
     * @param account
     * @return
     */
    public User findLoginUserAccount(String account);


}
