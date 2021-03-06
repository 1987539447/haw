package com.bigknow.frame.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Log基类,所有的类默认继承此类,可以直接使用 logger 记录日志,例如 logger.error("error");
 */
public class BaseLogger {
	public Logger logger = LoggerFactory.getLogger(getClass());
}
