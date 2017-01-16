package com.bigknow.system.service;

import com.bigknow.frame.service.IService;
import com.bigknow.system.entity.Menu;
import com.bigknow.system.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/3/20.
 */
public interface IDelegatingService extends IService {


    /**
     * 查询已经存在的用户
     * @param account
     * @return
     */
    public User findLoginUserAccount(String account);

    /**
     * 根据用户ID查询权限
     * @param id
     * @return
     */
    public List<Menu> getPermissionsByUserId(String id);

    /**
     * 根据用户id获得菜单
     * @param id
     * @return
     */
    public List<Menu> getMenuByUserId(String id);

    /**
     * 根据用户id查询权限,并返回set
     * @param id
     * @return
     */
    public Set<String> getPermissionsAsSet(String id);

    /**
     * 根据用户id查询用户角色名称,并返回set
     * @param id
     * @return
     */
    public Set<String> getRolesAsSet(String id);
}
