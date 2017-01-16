/**
 *
 */
package com.bigknow.frame.service;

import com.bigknow.frame.entity.IEntity;
import com.bigknow.frame.mapper.IMapper;
import com.bigknow.frame.vo.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bigknow.frame.util.GlobalStatic.*;

/**
 * 基础的Service父类,所有的Service都必须继承此类,每个数据库都需要一个实现.</br>
 * 例如 demo数据的实现类是org.springrain.springrain.service.BasedemoServiceImpl,demo2数据的实现类是org.springrain.demo2.service.Basedemo2ServiceImpl</br>
 *
 * @author springrain<Auto generate>
 * @version 2013-03-19 11:08:15
 * @copyright {@link 9iu.org}
 * @see .frame.service.BaseService
 */
@SuppressWarnings("ALL")
public class BaseCRUDService<T extends IEntity,E extends IMapper> extends BaseService implements
        ICRUDService<T> {

/*    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;*/


    @Autowired //spring 注解可以准确判断
    protected E mapper;

    public void setMapper(E mapper){
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void add(T entity) {
        mapper.insert(entity);
    }

    @Override
    @Transactional
    public int update(T entity) {
        return mapper.update(entity);
    }

    @Override
    @Transactional
    public int delete(String id) {
       return mapper.delete(id);
    }

    @Override
    @Transactional
    public Page<T> findAll(T t,int pageNum, int pageSize) {
        //首先设置分页参数，再查询，每设置一次查询一次
        if(pageSize<=0) pageSize = DEFAULT_PAGE_NUMBER;
        if(pageNum<=0) pageNum = DEFAULT_PAGE_SIZE;
        PageHelper.startPage(pageNum,pageSize);
        Page<T> page = new Page<T>();

        page.setEntitys(mapper.selectAll(t));
        //由于pagehelper有时候不准,所以再重新设置下page信息;
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setFuzzyField(t.getFuzzyField());
        return page;
    }

    @Override
    @Transactional
    public List<T> findAll(){
        return mapper.selectAllWithNoParm();
    }

    @Override
    @Transactional
    public T findById(String id) {
        return ((T)mapper.select(id));
    }
}
