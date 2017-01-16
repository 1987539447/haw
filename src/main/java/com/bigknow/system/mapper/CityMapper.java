package com.bigknow.system.mapper;

import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface CityMapper<City> {

    public void insert(City t);

    public int update(City t);

    /**
     * 删除单条记录
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 查询单条数据
     * @param id
     * @return
     */
    public City select(Integer id);

    /**
     * 支持条件查询，全查询，分组排序，模糊查询
     * @param t
     * @return
     */
    public List<City> selectAll(City t);

    /**
     * 查询所有记录
     * @return
     */
    public List<City> selectAllWithNoParm();

    /**
     * 根据简称查询城市信息。
     * @param cityName
     * @return
     */
    public List<City> selectByCityName(String cityName);
}
