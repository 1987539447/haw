package com.bigknow.system.service.impl;

import com.bigknow.frame.service.BaseService;
import com.bigknow.frame.vo.Page;
import com.bigknow.system.entity.City;
import com.bigknow.system.mapper.CityMapper;
import com.bigknow.system.service.ICityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bigknow.frame.util.GlobalStatic.DEFAULT_PAGE_NUMBER;
import static com.bigknow.frame.util.GlobalStatic.DEFAULT_PAGE_SIZE;

/**
 * Created by Administrator on 2015/8/24.
 */
@Service("cityService")
public class CityServiceImpl extends BaseService implements ICityService  {


    @Autowired //spring 注解可以准确判断
    protected CityMapper mapper;

    public void setMapper(CityMapper mapper){
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void add(City entity) {
        mapper.insert(entity);
    }

    @Override
    @Transactional
    public int update(City entity) {
        return mapper.update(entity);
    }

    @Override
    @Transactional
    public int delete(String id) {
        return mapper.delete(Integer.parseInt(id));
    }

    @Override
    @Transactional
    public Page<City> findAll(City t,int pageNum, int pageSize) {
        //首先设置分页参数，再查询，每设置一次查询一次
        if(pageSize<=0) pageSize = DEFAULT_PAGE_NUMBER;
        if(pageNum<=0) pageNum = DEFAULT_PAGE_SIZE;
        PageHelper.startPage(pageNum, pageSize);
        Page<City> page = new Page<City>();

        page.setEntitys(mapper.selectAll(t));
        //由于pagehelper有时候不准,所以再重新设置下page信息;
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setFuzzyField(t.getFuzzyField());
        return page;
    }

    @Override
    @Transactional
    public List<City> findAll(){
        return mapper.selectAllWithNoParm();
    }

    @Override
    @Transactional
    public City findById(String id) {
        return ((City)mapper.select(Integer.parseInt(id)));
    }
}
