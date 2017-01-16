package com.bigknow.frame.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/2/23.
 */
public interface IEntity extends Serializable {

    public String getId();

    public void setId(String id);

    public String getFuzzyField();

    public void setFuzzyField(String fuzzyField);


}
