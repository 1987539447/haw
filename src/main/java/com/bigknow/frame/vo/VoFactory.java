package com.bigknow.frame.vo;

/**
 * Created by Administrator on 2015/3/17.
 */
public final class VoFactory {

   public static IVo newInstace(Class clasz) {
       try{
           return (IVo) clasz.newInstance();
       }catch(Exception e){
           throw new RuntimeException(e);
       }
   }

    public static ResultInfo newResultInfo(int code,String info){
        return new ResultInfo(code,info);
    }
}
