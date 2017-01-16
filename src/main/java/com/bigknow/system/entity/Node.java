package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;

import java.util.Date;

/**
 * Created by TwelveFoldEr on 2015/8/24.
 */
public class Node extends BaseEntity {
    private String id;

    private String code;
    private String name;
    private String ip;
    private int port;
    private String cityId;
    private String cityName;
    private byte[] keyStore;
    private String remark;
    private Date updateTime;
    private String updateUserId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public byte[] getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(byte[] keyStore) {
        this.keyStore = keyStore;
    }
}
