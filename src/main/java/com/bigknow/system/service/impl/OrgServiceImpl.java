package com.bigknow.system.service.impl;

import com.bigknow.frame.service.BaseCRUDService;
import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.system.entity.Org;
import com.bigknow.system.mapper.OrgMapper;
import com.bigknow.system.mapper.UserOrgLinkMapper;
import com.bigknow.system.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2015/2/27.
 */
@Service("orgService")
class OrgServiceImpl extends BaseCRUDService<Org,OrgMapper> implements IOrgService{

    @Autowired
    private UserOrgLinkMapper userOrgLinkMapper;

    @Override
    public List<Org> selectOrgByUserId(String id) {
        return userOrgLinkMapper.selectOrgsByUserId(id);
    }

    @Override
    @Transactional
    /**
     * 需要覆盖delete方法,delete org的时候必须删除相关的link信息
     */
    public int delete(String id) {
        assertUserExists(id);
        userOrgLinkMapper.deleteByOrgId(id);
        return super.delete(id);
    }

    /**
     * 判断部门下是否有关联用户
     * @param id
     */
    private void assertUserExists(String id ){
        if(userOrgLinkMapper.selectUsersCountByOrgId(id)!=0){
            throw new RuntimeException(springUtils.getMessage(GlobalStatic.ERR_DELETE_ORG_USER_EXIST));
        }
    }
}
