package com.bigknow.frame.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2015/2/23.
 */
public abstract class BaseEntity implements IEntity {

    public String trim(String value){
        if(StringUtils.isNotBlank(value)){
            return value.trim();
        }else{
            return value;
        }
    }

    private String orderByName;

    private String orderBy;

    //模糊查找字段
    private String fuzzyField;


    public String getOrderByName() {
        return orderByName;
    }

    public void setOrderByName(String orderByName) {
        this.orderByName = orderByName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 获取模糊查找字段信息，如果为null，后续就不执行模糊查找
     * @return
     */
    public String getFuzzyField() {
        return fuzzyField;
    }

    /**
     * 设置模糊查找字段，如果设置为null，mapper层不匹配模糊模式查找
     * @param fuzzyField
     */
    public void setFuzzyField(String fuzzyField) {
        this.fuzzyField = fuzzyField;
    }
}
