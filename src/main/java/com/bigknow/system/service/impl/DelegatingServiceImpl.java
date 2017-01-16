package com.bigknow.system.service.impl;

import com.bigknow.frame.service.BaseService;
import com.bigknow.system.entity.Menu;
import com.bigknow.system.entity.Role;
import com.bigknow.system.entity.User;
import com.bigknow.system.mapper.UserPermissionMapper;
import com.bigknow.system.service.IDelegatingService;
import com.bigknow.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 供shiro认证授权时使用
 * Created by Administrator on 2015/3/20.
 */
@Service("delegatingService")
public class DelegatingServiceImpl extends BaseService implements IDelegatingService {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public User findLoginUserAccount(String account) {
        return userService.findLoginUserAccount(account);
    }

    @Override
    public List<Menu> getPermissionsByUserId(String id) {
        return userPermissionMapper.selectMenuByUserId(id);
    }

    @Override
    public List<Menu> getMenuByUserId(String id) {
        List<Menu> menuLeafs = new ArrayList<Menu>();
        List<Menu> menuList = new ArrayList<Menu>();

        for(Menu menu:this.getPermissionsByUserId(id)){
           if(StringUtils.isEmpty(menu.getPid())){
               menuList.add(menu);
           }else{
               menuLeafs.add(menu);
           }
        }
        menuLeafCreate(menuList,menuLeafs);
        return menuList;
    }

    private void menuLeafCreate(List<Menu> parents,List<Menu> leafs){
        for(Menu parent:parents){
            if(addLeaf(parent,leafs)){
                for(Menu pp:parent.getLeaf()){
                    addLeaf(pp,leafs);
                }
            }
        }
    }

    private boolean addLeaf(Menu parent,List<Menu> leafs){
        boolean haveLeaf = false;
        for(Menu leaf:leafs){
           if(parent.getId().equals(leaf.getPid())){
               haveLeaf=true;
               //如果为空就创建一个list存放leaf
               if(parent.getLeaf()==null){
                   parent.setLeaf(new ArrayList<Menu>());
               }
               parent.getLeaf().add(leaf);
           }
        }
        return haveLeaf;
    }

    @Override
    public Set<String> getPermissionsAsSet(String id) {
       List<Menu> permissions =  this.getPermissionsByUserId(id);
        Set<String> set = new HashSet<String>();
        for(Menu perm:permissions){
            set.add(perm.getPageurl());
        }
        return set;
    }

    @Override
    public Set<String> getRolesAsSet(String id) {
        List<Role> roles = userService.findById(id).getUserRoles();
        Set<String> set = new HashSet<String>();
        for(Role role:roles){
            set.add(role.getCode());
        }
        return set;
    }

}
