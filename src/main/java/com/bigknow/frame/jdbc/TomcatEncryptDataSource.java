package com.bigknow.frame.jdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/4/6.
 */
public class TomcatEncryptDataSource extends DataSource {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void setPassword(String password) {
        //logger.info("database password is :"+password);
        //logger.info("database password is :"+SecUtils.decrypt(password, GlobalStatic.SECURITY_DEFULT_KEY));
        //super.setPassword(SecUtils.decrypt(password, GlobalStatic.SECURITY_DEFULT_KEY));
        /*先不加密了*/
        super.setPassword(password);
    }
}
