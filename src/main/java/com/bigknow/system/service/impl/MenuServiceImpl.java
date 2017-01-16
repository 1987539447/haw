package com.bigknow.system.service.impl;

import com.bigknow.frame.service.BaseCRUDService;
import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.system.entity.Menu;
import com.bigknow.system.entity.Role;
import com.bigknow.system.mapper.MenuMapper;
import com.bigknow.system.mapper.RoleMenuLinkMapper;
import com.bigknow.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
@Service("menuService")
public class MenuServiceImpl extends BaseCRUDService<Menu,MenuMapper> implements IMenuService {

    @Autowired
    private RoleMenuLinkMapper roleMenuLinkMapper;

    /**
     * 当菜单拥有使用的角色的时候,不能删除
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int delete(String id) {
        List<Role> list = roleMenuLinkMapper.selectRolesByMenuId(id);
        if((list!=null)&&(list.size()!=0)){
            throw new RuntimeException(springUtils.getMessage(GlobalStatic.ERR_DELETE_MENU_ROLE_EXIST));
        }
        return deleteRecursion(id);// mapper.delete(id);
    }

    private int deleteRecursion(String pid){
        List<Menu> ms = mapper.selectParent(pid);
        for(Menu menu:ms){
            deleteRecursion(menu.getId());
        }
        return mapper.delete(pid);
    }
}
