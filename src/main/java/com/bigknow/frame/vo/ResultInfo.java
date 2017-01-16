package com.bigknow.frame.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/17.
 */
public class ResultInfo implements Serializable {

    public static final int SUCCESS=200;

    public static final int ERROR=500;

    public static final int NON=0;

    public int getCode() {
        return code;
    }

    /**
     * err        500
     * success    200
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    private int code;


    public ResultInfo(int code,String info) {
        this.code=code;
        this.info = info;
    }

    public ResultInfo() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String info;

}
