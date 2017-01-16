package com.bigknow.system.mapper;

import com.bigknow.frame.mapper.IMapper;
import com.bigknow.system.entity.Role;
import com.bigknow.system.entity.UserRoleLink;

import java.util.List;

/**
 * Created by Administrator on 2015/2/28.
 */
public interface UserRoleLinkMapper extends IMapper<UserRoleLink> {

    /**
     * 根据user id 查询用户角色
     * @param id
     * @return
     */
    public List<Role> selectRolesByUserId(String id);

    /**
     * 查询角色关联的用户数量
     * @param id
     * @return
     */
    public int SelectUsersCountByRoleId(String id);

    /**
     * 根据user id删除关联表数据
     * @param id
     */
    public int deleteByUserId(String id);

    /**
     * 根据role ID删除关联表数据
     * @param id
     */
    public int deleteByRoleId(String id);





}
