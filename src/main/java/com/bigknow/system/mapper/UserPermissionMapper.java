package com.bigknow.system.mapper;

import com.bigknow.system.entity.Menu;

import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public interface UserPermissionMapper {

    /**
     * 为了提高性能单独创建一个方法用于通过用户查询 menu(permission)
     * @param id
     * @return
     */
    public List<Menu> selectMenuByUserId(String id);

  //  public List<Menu> SelectMenuAndLeafByUserId(String id);

}
