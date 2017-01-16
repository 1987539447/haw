package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import com.bigknow.frame.entity.IEntity;

/**
 * Created by Administrator on 2015/8/24.
 */
public class City extends BaseEntity implements IEntity {

    private int cityId;

    private String areaname;

    private int parentid;

    /**
     * 为了方便页面展示,将父级节点查询出来
     */
    private String parentname;

    private String shortname;

    private String lng;

    private String lat;

    private int level;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //没用的东西,由于加入了insertOrupdate代码,弄恶心了
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {
    }
}
