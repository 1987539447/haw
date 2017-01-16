package com.bigknow.system.mapper;

import com.bigknow.frame.mapper.IMapper;
import com.bigknow.system.entity.Org;
import com.bigknow.system.entity.User;
import com.bigknow.system.entity.UserOrgLink;

import java.util.List;

/**
 * user和org双向同步辅助查询接口，User和org的service类中嵌入此接口进行查询
 * Created by Administrator on 2015/2/28.
 */
public interface UserOrgLinkMapper extends IMapper<UserOrgLink> {

    /**
     * 查询某机构下的用户
     * @param orgId
     * @return
     */
    public List<User> selectUsersByOrgId(String orgId);

    /**
     * 查询某机构以及所有子机构下的所有用户
     * @param orgId
     * @return
     */
    public List<User> selectAllUsersByOrgId(String orgId);

    /**
     * 查询某机构下的用户总数,一般删除机构前必须要判断
     * @param id
     * @return
     */
    public int selectUsersCountByOrgId(String id);

    /**
     * 查询某用户所属机构
     * @param userId
     * @return
     */
    public List<Org> selectOrgsByUserId(String userId);

    /**
     * 根据用户id删除关联
     * @param id
     */
    public void deleteByUserId(String id);

    /**
     * 根据机构id删除关联
     * @param id
     */
    public void deleteByOrgId(String id);
}
