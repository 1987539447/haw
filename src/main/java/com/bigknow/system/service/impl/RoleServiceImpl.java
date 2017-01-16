package com.bigknow.system.service.impl;

import com.bigknow.frame.service.BaseCRUDService;
import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.frame.util.SecUtils;
import com.bigknow.system.entity.Menu;
import com.bigknow.system.entity.Role;
import com.bigknow.system.entity.RoleMenuLink;
import com.bigknow.system.mapper.RoleMapper;
import com.bigknow.system.mapper.RoleMenuLinkMapper;
import com.bigknow.system.mapper.UserRoleLinkMapper;
import com.bigknow.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色service类
 * Created by Administrator on 2015/3/19.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseCRUDService<Role,RoleMapper> implements IRoleService{

    @Autowired
    private UserRoleLinkMapper userRoleLinkMapper;

    @Autowired
    private RoleMenuLinkMapper roleMenuLinkMapper;


    /**
     * 添加角色,如果menu List不为空,就需要添加 role menu link关联
     * 添加关联关系需要先删除再添加
     */
    @Transactional
    public void add(Role role){
        role.setId(SecUtils.getUUID());
        createMenu(role);
        super.add(role);
        addOrUpdateRoleMenuLink(role);
    }

    @Transactional
    public int update(Role role){
        createMenu(role);
        addOrUpdateRoleMenuLink(role);
        return super.update(role);
    }

    private void createMenu(Role role){
        if(!StringUtils.isEmpty(role.getMenuIds())){
           String[] ids = role.getMenuIds().split(",");
            if(ids!=null&&ids.length>0){
                List<Menu> list = new ArrayList<Menu>();
                for(String id:ids){
                    if(StringUtils.isEmpty(id)) continue;
                    Menu menu = new Menu();
                    menu.setId(id);
                    list.add(menu);
                }
                role.setMenus(list);
            }
        }
    }

    /**
     * 添加 角色 与 菜单(权限) 的关联数据
     * @param role
     */
    private void addOrUpdateRoleMenuLink(Role role){
        //先删除原有的关联数据
        roleMenuLinkMapper.deleteByRoleId(role.getId());
        if(role.getMenus()!=null){
            for(Menu menu:role.getMenus()){
                RoleMenuLink link = new RoleMenuLink();
                link.setId(SecUtils.getUUID());
                link.setRoleId(role.getId());
                link.setMenuId(menu.getId());
                roleMenuLinkMapper.insert(link);
            }
        }
    }
    /**
     * 删除role的同时必须删除user,menu关联表数据
     * @param id
     * @return
     */
    @Transactional
    public int delete(String id){
        assertUserExists(id);//判断用户是否存在
        userRoleLinkMapper.deleteByRoleId(id);
        roleMenuLinkMapper.deleteByRoleId(id);
        return super.delete(id);
    }

    /**
     * 判断角色是否关联有用户,如果有就不应该进行删除,直接抛出异常
     * 如果没有才可以进行删除
     * @param id
     */
    private void assertUserExists(String id){
        if(userRoleLinkMapper.SelectUsersCountByRoleId(id)!=0){
            throw new RuntimeException(springUtils.getMessage(GlobalStatic.ERR_DELETE_ROlE_USER_EXIST));
        }
    }

    /**
     * 查询单条记录的时候将menu记录一起查询出来
     * @param id
     * @return
     */
    public Role findById(String id){
        Role role = super.findById(id);
        role.setMenus(roleMenuLinkMapper.selectMenusByRoleId(role.getId()));
        return role;
    }
}
