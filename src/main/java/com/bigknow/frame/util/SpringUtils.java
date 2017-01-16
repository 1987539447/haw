
package com.bigknow.frame.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Spring 工具类,通过继承applicationContextAware,spring可以自动
 * 注入 applicationContext,一般情况下,spirng是不允许随便注入
 * applicationContext的,所以必须继承applicationContextAware
 */

@Component("springUtils")
public class SpringUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    public SpringUtils() {

    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    /**
     * 根据beanName 获取 spring bean
     *
     * @param beanName
     * @return Object
     */
    public Object getBean(String beanName) {
        if (beanName == null) return null;
        return applicationContext.getBean(beanName);
    }

    /**
     * 返回所有bean的定义名字（id）
     *
     * @return String[]
     */
    public String[] getBeanDefinitionNames() {
        return applicationContext.getBeanDefinitionNames();
    }

    /**
     * 根据bean type 获取springBean
     *
     * @param clazz
     * @return
     */
    public <T> T getBeanByType(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取 Spring applicationContext
     *
     * @return
     */
    public ApplicationContext getContext() {
        return applicationContext;
    }


    /**
     * 获取messageResouce指定的配置文件中的消息,由于applicationContext继承了
     * MessageSource接口,在pring配置文件中配置如下信息:
     *
     * <pre>
     * {@code
     *  <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
     *      <property name="basenames">
     *          <list>
     *              <value>messages/messages</value>
     *          </list>
     *      </property>
     *  </bean>
     * }
     * </pre>
     后,就可以采用applicationContext获取消息了.
     * @param key
     * @return
     */
    public String getMessage(String key,Locale locale){
        return applicationContext.getMessage(key, null, locale);
    }

    /**
     * 获取中文消息
     * @see #getMessage(String key, Locale locale)
     * @param key 消息的key messages配置文件中对应的key
     * @return String消息
     */
    public String getMessage(String key) {
        return getMessage(key,Locale.CHINA);
    }

}
