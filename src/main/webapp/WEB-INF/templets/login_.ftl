<#escape x as x?html>
<!DOCTYPE html>
<#import "common/head.ftl" as h>
<html>
<@h.head><@spring.message "login.title"/></@h.head>
<#include "common/base.ftl">
<#include "common/js.ftl">
<script type="text/javascript">
    function reloadValidateCode() {
        document.getElementById("captchaImg").src="${ctx}/getCaptcha?date =" + new Date().getTime();
    }
</script>
<body class='contrast-red sign-in contrast-background'>
<div id='wrapper'>
    <#--标准是否为登陆界面,如果为登陆界面所有javascript请求都得全页面跳转-->
    <input type="text" id="loginPage" value="true" style="display: none">
    <div class='application'>
        <div class='application-content'>
            <a href="login">
                <div class='icon-bar-chart'></div>
                <span><@spring.message "login.title"/></span>
            </a>
        </div>
    </div>
    <div class='controls'>
        <div class='caret'></div>
        <div class='form-wrapper'>
            <h1 class='text-center'><@spring.message "login.tab"/></h1>

            <form accept-charset="UTF-8" action="${ctx}/login" method="post"/>
            <div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;"/></div>
            <div id="errorMessage">
           <#-- <#if Request["error"]??> 数据存储在request中,但采用spring mvc model添加后直接映射为 freemarker的model
           所以可以不用在request中取-->
               <#if error??>
                <p class="error">
                    ${error}
                </p>
            <#else>
            </#if>
            </div>
            <div class='row-fluid'>
                <div class='span12 icon-over-input'>
                    <input class="span12" id="username" name="username" placeholder="username" type="text" value=""/>
                    <i class='icon-user muted'></i>
                </div>
            </div>
            <div class='row-fluid'>
                <div class='span12 icon-over-input'>
                    <input class="span12" id="password" name="password" placeholder="Password" type="password"
                           value=""/>
                    <!--<i class='icon-lock muted'></i>-->
                </div>
            </div>
            <div class="row-fluid">
                <div class='span12 icon-over-input'>
                    <table>
                        <tr class = "span12">
                            <td><input id="captcha" name="captcha" type="text" value=""></td>
                            <td style="vertical-align:top">
                                <img id="captchaImg" src="${ctx}/getCaptcha"
                                                         style=" cursor: pointer;border: 1px #0180df;width: 50px; height: 29px;align:top;"
                                        onclick="reloadValidateCode()"/>
                    <span class="refresh">
                        <a style="cursor: pointer " href="javascript:reloadValidateCode();"><@spring.message "login.reflash"/></a>
                    </span></td>
                        </tr>
                    </table>

                </div>
            </div>
            <label class="checkbox" for="remember_me"><input id="remember_me" name="remember_me" type="checkbox"
                                                             value="1"/>
            <@spring.message "login.rememberme"/>
            </label>
            <button class="btn btn-block" name="button" type="submit"><@spring.message "login.tab"/></button>
            </form>
            <#--<div class='text-center'>-->
                <#--<hr class='hr-normal'/>-->
                <#--<a href="forgot_password.html"><@spring.message "login.forgot"/></a>-->
            <#--</div>-->
        </div>
    </div>
<#--    <div class='login-action text-center'>
        <a href="sign_up.html"><i class='icon-user'></i>
            New to Flatty?
            <strong>Sign up</strong>
        </a>
    </div>-->
</div>
</body>
</html>
</#escape>