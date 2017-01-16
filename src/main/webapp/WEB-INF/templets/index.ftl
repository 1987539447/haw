<#escape x as x?html>
    <#import "common/head.ftl" as h>
    <#import "common/index-parts.ftl" as n>
    <#import "common/base.ftl" as b>
<!DOCTYPE html>
<html>
    <@h.head><@spring.message "login.title"/></@h.head>
    <#include "common/js.ftl">
<body class='contrast-red'>

    <@n.header/>

<div id='wrapper'>
    <div id='main-nav-bg'></div>
    <nav class='' id='main-nav'>
    <#--这个是左边的导航菜单-->
    <@n.navigation/>
    </nav>
    <section id='content'>
        <div class='container-fluid'>
            <div class='row-fluid' id='content-wrapper'>
                <div class='span12'>

                <#--右边mainForm大框的标题头-->
                    <@n.mainFormHeader/>
                <#--这个form用于jquery ajaxsubmit提交将数据覆盖到
                target元素内(<div id="mainPage" class='row-fluid'>)这这是一种使用技巧-->
                    <form id="mainForm" method="post">
                    </form>

                    <div id="mainPage" class='row-fluid'>
                        <#--<#if user.cityId == 100000>-->
                            <#--<#include "dashboard.ftl">-->
                        <#--<#else>-->
                            <#--<#include "dashboard-city.ftl">-->
                        <#--</#if>-->
                    </div>
                    <hr class='hr-drouble'/>

                </div><#--div class='span12'-->
            </div>
    </section>
</div><#--div id='wrapper'-->
</body>
</html>
</#escape>