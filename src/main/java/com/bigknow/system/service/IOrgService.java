package com.bigknow.system.service;

import com.bigknow.frame.service.ICRUDService;
import com.bigknow.system.entity.Org;

import java.util.List;

/**
 * Created by Administrator on 2015/2/27.
 */
public interface IOrgService extends ICRUDService<Org> {

    /**
     * 根据用户id查询机构列表
     * @param id
     * @return
     */
    public List<Org> selectOrgByUserId(String id);
}
