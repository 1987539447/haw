package com.bigknow.frame.service;

import com.bigknow.frame.common.BaseLogger;
import com.bigknow.frame.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/3/20.
 */
public class BaseService extends BaseLogger implements IService {

    @Autowired
    protected SpringUtils springUtils;

    @Override
    public SpringUtils getSpringUtils() throws Exception {
        return springUtils;
    }

    /**
     * 获取spring Bean
     */
    @Override
    public Object getBean(String beanName) throws Exception {
        if (beanName == null)
            return null;
        return getSpringUtils().getBean(beanName);
    }
}
