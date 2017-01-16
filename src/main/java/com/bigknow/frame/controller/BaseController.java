package com.bigknow.frame.controller;

import com.bigknow.frame.common.BaseLogger;
import com.bigknow.frame.util.BaseTypeEditor;
import com.bigknow.frame.util.DateTypeEditor;
import com.bigknow.frame.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/3.
 */
public class BaseController extends BaseLogger {

    public String messageurl = "/common/message";

    public String message = "message";

    public String redirect = "redirect:";

    public String forward = "forward:";

    @Autowired
    protected SpringUtils springUtils;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //data类型的转换
        binder.registerCustomEditor(Date.class, new DateTypeEditor());
        //基本类型转换
        binder.registerCustomEditor(Integer.class,new BaseTypeEditor(Integer.class));
        binder.registerCustomEditor(Long.class,new BaseTypeEditor(Long.class));
        binder.registerCustomEditor(Double.class,new BaseTypeEditor(Double.class));
        binder.registerCustomEditor(BigDecimal.class,new BaseTypeEditor(BigDecimal.class));
    }


    /**
     * 默认采用 forward方式转发页面,如果是rest需要返回
     * body体内的消息就看
     * @see com.bigknow.frame.controller.BaseRestController 的处理方式
     */
    @ExceptionHandler
    public ModelAndView exp(HttpServletRequest request, Exception e) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("error",e);
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return new ModelAndView("/error",map);
    }

    /**
     * 公共下载方法
     *
     * @param response
     * @param file
     *            下载的文件
     * @param fileName
     *            下载时显示的文件名
     * @return
     * @throws Exception
     */
    public HttpServletResponse downloadFile(HttpServletResponse response, File file, String fileName,
                                        boolean delFile) throws Exception {
        response.setContentType("application/x-download");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        OutputStream out = null;
        InputStream in = null;
        // 下面一步不可少
        fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);// 设定输出文件头

        try {
            out = response.getOutputStream();
            in = new FileInputStream(file);
            int len = in.available();
            byte[] b = new byte[len];
            in.read(b);
            out.write(b);
            out.flush();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception("下载失败!");
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (delFile) {
                file.delete();
            }
        }
        return response;
    }

    /**
     * 通用下载二进制文件方法
     * @param response
     * @param data
     * @param fileName
     * @return
     */
    public HttpServletResponse downloadBytes(HttpServletResponse response,byte[] data,String fileName) {
        response.setContentType("application/x-download");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        OutputStream out = null;
        InputStream in = null;
        // 下面一步不可少
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);// 设定输出文件头
        try {
            out = response.getOutputStream();
            in = new ByteArrayInputStream(data);
            int len = in.available();
            byte[] b = new byte[len];
            in.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
//            throw new Exception("下载失败!");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return response;
    }
}
