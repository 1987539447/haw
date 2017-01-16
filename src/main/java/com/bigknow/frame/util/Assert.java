package com.bigknow.frame.util;

/**
 * Created by Administrator on 2015/3/26.
 */
public final class Assert {

    public static void assertNotNull(Object obj,Class<? extends RuntimeException> e) {
        if(obj==null){
            try {
                throw e.newInstance();
            } catch (Exception ee) {
                ee.printStackTrace();
                throw new RuntimeException();
            }
        }
    }
}
