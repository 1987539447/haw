package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseController;
import com.bigknow.frame.security.CaptchaValidationException;
import com.bigknow.frame.security.exception.AuthenticationNotUseException;
import com.bigknow.frame.util.CaptchaUtils;
import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Administrator on 2015/3/3.
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 界面提交登陆调用的后台方法
     * @param request
     * @param response
     * @param model  springMvc的model,在model中添加的对象会直接映射到request中并映射到freemarker中，
     *               使其可以直接调用
     * @return       controller注册的其他url
     */
    @RequestMapping(value = {"/login"})
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        //授权部分在shiro框架中完成,见 securityRealm
        //验证码验证在CaptchaFormAuthenticationFilter中
        //这里只要判断是否登陆就行了
        clearError(request);//清除上一次请求的错误信息!

        //登陆页面标识,用于界面上jquery等ajax框架判断页面类型,作为session超时等页面跳转用
        ((HttpServletResponse)response).setHeader("PageNameValue","login");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            addUserInfoToPage(model);
            return "index";
        } else {
            //shiro 配置的formAuthenticationFilter会将错误类型放入request 的shiroLoginFailure元素中,采用如下方式可以获取异常类型
            addErrorMessage((String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME), model);
            return "login_";
        }
    }


    /**
     * 主页,如果没有登陆或者没有remember就弹到登陆页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,Model model) {
        clearError(request);
        //如果已经登录,就不能再弹出登录界面了
        if(SecurityUtils.getSubject().isAuthenticated()||SecurityUtils.getSubject().isRemembered()){
            addUserInfoToPage(model);//将user信息加入到model，避免记住当没登陆用户没法显示页面信息。
            return "index";
        }
        return "login_";
    }

    /**
     * 当登陆后出现超时，或者remembered后需要重新登陆时，这个时候页面处在main区域
     * navigationBar.js通过jquery ajaxSetup将整个页面定位到此，跳过页面认证判断，
     * 避免造成认证问题。
     * @param request
     * @return
     */
    @RequestMapping(value = "/goToLogin")
    public String goToLogin(HttpServletRequest request){
        clearError(request);
        return "login_";
    }

/*    @RequestMapping("/test")
    public String test() {
        logger.info("this is a test!");
        return "system/user/test";
    }*/

    @RequestMapping("/error")
    public String error() {
        return "error";
    }

    /**
     * 退出登陆
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
    /**
     * 生成验证码
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpSession session,HttpServletResponse response) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        CaptchaUtils tool = new CaptchaUtils();
        StringBuffer code = new StringBuffer();
        BufferedImage image = tool.genRandomCodeImage(code);
        session.removeAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
        session.setAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM, code.toString());

        // 将内存中的图片通过流动形式输出到客户端
        ImageIO.write(image, "JPEG", response.getOutputStream());
        return;
    }

    private void clearError(HttpServletRequest request){
        request.setAttribute("error",null);
    }

    private void addErrorMessage(String exceptionType, Model model) {
        logger.info("error: " + exceptionType);
        if (AuthenticationException.class.getName().equals(exceptionType)) {
            model.addAttribute("error", springUtils.getMessage(GlobalStatic.AuthenticationException));
        } else if (ExcessiveAttemptsException.class.getName().equals(exceptionType)) {
            model.addAttribute("error", springUtils.getMessage(GlobalStatic.ExcessiveAttemptsException));
        } else if(exceptionType==null){
            model.addAttribute("error",springUtils.getMessage(GlobalStatic.SessionTimeoutExeption));
        } else if(CaptchaValidationException.class.getName().equals(exceptionType)){
            model.addAttribute("error",springUtils.getMessage(GlobalStatic.CaptchaValidationException));
        }else if(AuthenticationNotUseException.class.getName().equals(exceptionType)){
            model.addAttribute("error", springUtils.getMessage(GlobalStatic.AuthenticationException_notUse));
        }

        else {
            //没有匹配的异常
            model.addAttribute("error", springUtils.getMessage(GlobalStatic.UnknownAuthenticationException));
        }
    }

    private void addUserInfoToPage(Model model){
        model.addAttribute("user",(User)SecurityUtils.getSubject().getPrincipal());
    }

}
