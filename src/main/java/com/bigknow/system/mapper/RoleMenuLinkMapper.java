package com.bigknow.system.mapper;

import com.bigknow.frame.mapper.IMapper;
import com.bigknow.system.entity.Menu;
import com.bigknow.system.entity.Role;
import com.bigknow.system.entity.RoleMenuLink;

import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public interface RoleMenuLinkMapper extends IMapper<RoleMenuLink> {

    /**
     * 通过角色id删除 菜单(权限) 关联数据
     * 一般用于删除角色时使用
     * @param id
     * @return
     */
    public int deleteByRoleId(String id);

    /**
     * 通过菜单ID删除关联的 角色数据
     * 一般用于删除菜单时使用
     * @param id
     * @return
     */
    public int deleteByMenuId(String id);

    /**
     * 通过角色查询相关的权限
     * 一般用于查看角色配置信息时使用
     * @param id
     * @return
     */
    public List<Menu> selectMenusByRoleId(String id);

    /**
     * 通过菜单(权限)id来获取角色关联信息
     * 当需要删除菜单的时候需要判断是否有角色在使用
     * @param id
     * @return
     */
    public List<Role> selectRolesByMenuId(String id);
}
