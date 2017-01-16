
/**
 * 
 */
package com.bigknow.frame.service;

import com.bigknow.frame.entity.IEntity;
import com.bigknow.frame.vo.Page;

import java.util.List;


/**
 *
 * @see ICRUDService
 */
public interface ICRUDService<T extends IEntity> extends IService {

    /**
     * 保存对象
     * @param entity
     */
    public void add(T entity);

    /**
     * 更新对象
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 删除对象
     * @param id
     */
    public int delete(String id);

    /**
     * 分页查询对象
     * @return
     */
    public Page<T> findAll(T t, int pageNum, int pageSize);

    /**
     * 查询所有对象
     * @return
     */
    public List<T> findAll();

    /**
     * 通过ID查询对象
     * @param id
     * @return
     */
    public T findById(String id);

}
