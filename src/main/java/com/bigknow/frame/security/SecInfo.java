package com.bigknow.frame.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用于加密,解密算法的基础参数类
 * Created by Administrator on 2015/4/3.
 */
@Component
public class SecInfo {

    @Value("${hashAlgorithmName}")
    private String hashAlgorithmName;

    @Value("${hashIterations}")
    private String hashIterations;

    public void setHashAlgorithmName(String hashAlgorithmName){
        this.hashAlgorithmName = hashAlgorithmName;
    }

    public void setHashIterations(String hashIterations){
        this.hashIterations = hashIterations;
    }

    public String getHashAlgorithmName() {
        return hashAlgorithmName;
    }

    public String getHashIterations() {
        return hashIterations;
    }
}
