package com.bigknow.frame.mapper;

import com.bigknow.frame.entity.IEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/2/23.
 */
public interface IMapper<T extends IEntity> {

    public void insert(T t);

    public int update(T t);

    /**
     * 删除单条记录
     * @param id
     * @return
     */
    public int delete(String id);

    /**
     * 查询单条数据
     * @param id
     * @return
     */
    public T select(String id);

    /**
     * 支持条件查询，全查询，分组排序，模糊查询
     * @param t
     * @return
     */
    public List<T> selectAll(T t);

    /**
     * 查询所有记录
     * @return
     */
    public List<T> selectAllWithNoParm();

}
