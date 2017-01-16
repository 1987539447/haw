package com.bigknow.frame.security;

import com.bigknow.frame.util.GlobalStatic;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 继承shiro的FormAuthenticationFilter 加入对验证码的校验支持
 * Created by Administrator on 2015/4/7.
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);


    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }

        try {
            //检验验证码是否正确
            CaptchaValidation((HttpServletRequest) request);

            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    /**
     * 验证输入的验证码是否正确
     * @param request
     * @return
     */
    private void CaptchaValidation(HttpServletRequest request){
        String code = (String) request.getSession().getAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
        if(StringUtils.isNotBlank(code)){
            code=code.toLowerCase();
        }
        String submitCode = request.getParameter(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
        if(StringUtils.isNotBlank(submitCode)){
            submitCode=submitCode.toLowerCase().trim().toString();
        }
        if (StringUtils.isBlank(submitCode) ||StringUtils.isBlank(code)||!code.equals(submitCode)) {
            throw new CaptchaValidationException();
        }
    }
}
