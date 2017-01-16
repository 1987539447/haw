package com.bigknow.frame.util;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/3/2.
 */
public class BaseTypeEditor extends PropertyEditorSupport {

    private Class clasz;

    public BaseTypeEditor(Class clasz){
        this.clasz = clasz;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        try{
            if(StringUtils.isNotBlank(text)){
                if(BigDecimal.class.isAssignableFrom(clasz)){
                    setValue(new BigDecimal(text));
                }else if(Integer.class.isAssignableFrom(clasz)){
                     setValue(Integer.valueOf(text));
                }else if(Long.class.isAssignableFrom(clasz)){
                    setValue(Long.valueOf(text));
                }else if(Double.class.isAssignableFrom(clasz)){
                    setValue(Double.valueOf(text));
                }else{
                    setValue(null);
                    System.out.println("test");
                }
            }else{
                setValue(null);

            }
        }catch(Throwable e){
            e.printStackTrace();
            setValue(null);
            //logger.error(e.getMessage(), e);
        }
    }
}
