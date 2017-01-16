package com.bigknow.frame.service;

import com.bigknow.frame.util.SpringUtils;

/**
 * Created by Administrator on 2015/3/20.
 */
public interface IService {

    /**
     * 获取SpringUtils
     * @return
     * @throws Exception
     */
    public SpringUtils getSpringUtils() throws Exception;
    /**
     * 获取 获取 spring 中的Bean
     * @param beanName
     * @return
     * @throws Exception
     */
    public  Object getBean(String beanName) throws Exception;
}
