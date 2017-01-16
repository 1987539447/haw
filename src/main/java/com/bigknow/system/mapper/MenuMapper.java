package com.bigknow.system.mapper;

import com.bigknow.frame.mapper.IMapper;
import com.bigknow.system.entity.Menu;

import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public interface MenuMapper extends IMapper<Menu> {


    /**
     * 查询所有父亲为id的menu
     * @param id
     * @return
     */
    public List<Menu> selectParent(String id);

}
