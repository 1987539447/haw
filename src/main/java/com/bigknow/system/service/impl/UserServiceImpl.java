package com.bigknow.system.service.impl;

import com.bigknow.frame.security.SecInfo;
import com.bigknow.frame.service.BaseCRUDService;
import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.frame.util.SecUtils;
import com.bigknow.system.entity.*;
import com.bigknow.system.mapper.UserMapper;
import com.bigknow.system.mapper.UserOrgLinkMapper;
import com.bigknow.system.mapper.UserRoleLinkMapper;
import com.bigknow.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 约定凡是list为null的都不做删除和更新操作（Role Org）,凡是不为null的都做删除和更新
 */
@Service("userService")
public class UserServiceImpl extends BaseCRUDService<User, UserMapper> implements IUserService {

    @Autowired
    private UserOrgLinkMapper userOrgLinkMapper;

    @Autowired
    private UserRoleLinkMapper userRoleLinkMapper;

    @Override
    @Transactional
    public void add(User user) {
        user.setId(SecUtils.getUUID());
        encryptPassword(user);
        mapper.insert(user);
        addOrUpdateOrgLink(user);
        addOrUpdateRoleLink(user);
    }

    private void encryptPassword(User user) {
        String password = SecUtils.encryptPassword(springUtils.getBeanByType(SecInfo.class), user.getPassword(), user.getId() + GlobalStatic.SECURITY_SALT);
        user.setPassword(password);
    }


    private void addOrUpdateOrgLink(User user) {

        if (user.getUserOrgs() == null) return;
        //先删除所有orglink
        userOrgLinkMapper.deleteByUserId(user.getId());
        for (Org org : user.getUserOrgs()) {
            UserOrgLink link = new UserOrgLink();
            link.setOrgId(org.getId());
            link.setUserId(user.getId());
            link.setId(SecUtils.getUUID());//感觉这表id主键没啥用

            userOrgLinkMapper.insert(link);
        }
    }

    private void addOrUpdateRoleLink(User user) {
        if (user.getUserRoles() == null) return;
        //先删除所有roleLink
        userRoleLinkMapper.deleteByUserId(user.getId());
        for (Role role : user.getUserRoles()) {
            UserRoleLink link = new UserRoleLink();
            link.setUserId(user.getId());
            link.setRoleId(role.getId());
            link.setId(SecUtils.getUUID());//感觉这表id主键没啥用

            userRoleLinkMapper.insert(link);
        }
    }

    @Override
    @Transactional
    public int update(User user) {

        User oldUser = this.findById(user.getId());
        //如果密码没有改变过就不进行加密，不更新密码
        if(oldUser.getPassword()!=null&&oldUser.getPassword().equals(user.getPassword())){
            user.setPassword(null);
        }else{
            encryptPassword(user);
        }

        int value = mapper.update(user);
        addOrUpdateOrgLink(user);
        addOrUpdateRoleLink(user);
        return value;
    }

    @Override
    @Transactional
    public int delete(String id) {
        userOrgLinkMapper.deleteByUserId(id);
        userRoleLinkMapper.deleteByUserId(id);
        return mapper.delete(id);
    }

    @Override
    @Transactional
    public User findById(String id) {
        User user = mapper.select(id);
        user.setUserOrgs(userOrgLinkMapper.selectOrgsByUserId(id));
        user.setUserRoles(userRoleLinkMapper.selectRolesByUserId(id));
        return user;
    }


    @Override
    /**
     * 获取登陆的用户,如果不存在就抛出异常
     */
    public User findLoginUserAccount(String account) {
        User user = new User();
        user.setAccount(account);
        List<User> list = mapper.selectAll(user);
        if (list != null && list.size() == 1) {
            return list.get(0);
        } else {
            throw new RuntimeException(springUtils.getMessage(GlobalStatic.USER_NOT_EXIST));
        }
    }
}
