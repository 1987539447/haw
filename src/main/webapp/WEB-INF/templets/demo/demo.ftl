<!DOCTYPE html>
<html>
<#--使用内置函数html转义特殊字符-->
<#escape x as x?html>
<head>
    <#--不创建新命名空间，添加到主命名空间-->
    <#include "../common/base.ftl">
    <#include "../common/head.ftl">

    <script type="application/javascript">
        //提交保存
        function submitForm() {
            if (verify()) {
                //$('#user-modal').modal('hide');
                commonSaveForm('book-form', '${ctx}/demo/ftl/demoB', undefined);
            }

        }
        function verify() {
            return true;
        }
    </script>
    <title>freemarker</title>
</head>
<body>
<#-- freemarker注释 -->
<#-- 对象使用 -->
hello author ${book.author} <br/>
<#-- r 使用原生字符，不解释 -->
${r"${book.author}"}
<br/>
<#-- if else  大于gt 大于等于gte 小于lt 小于等于 lte -->
<#if book.price gt 0 >
book is not free
<#else>
book is free
</#if>
<br/>

<#--  list  -->
<#list [2+2,"HH"] as x >
    ${x}
</#list>
<br/>
<#list 1..3 as x >
${x}
</#list>
<br/>
<#list 5..1 as x >
${x}
</#list>
<br/>
<#list ["a","b"] + ["c","d"] as x >
${x}
</#list>
<br/>

<#list bookList[0..1] as book >
${book.bookName}
</#list>
<br/>

${bookList[0].author}${bookList[0].author}
<br/>
${bookList[0].author[0]}

${book.author+"\'s"} book <br/>


    <table>
        <thead>
        <tr>
            <th>
                书名
            </th>
            <th>
                作者
            </th>
            <th>
                价格
            </th>
            <th>
                编号
            </th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#-- ?? 判定是否存在 -->
            <#if (bookList??)>
            <#-- list -->
                <<#list bookList as book>
            <tr id='${book.bookId}'>
                <td>${book.bookName}</td>
                <td>${book.author}</td>
                <td>${book.price}</td>
                <td>${book.bookId}</td>
                <td>购买</td>
            </tr>
                </#list>
            </#if>

        </tbody>
    </table>
</body>

===================内置函数===============================<br/>
<#--  .xx 内置变量  ?xx 内置函数  -->
${book.bookName?upper_case}<br>
${"abc"?cap_first}<br>
${"A&B"?lower_case?html}<br>
${bookList?size}<br>
${1.8?int}<br>
${1.2?int}<br>
================??判定===================<br/>
<#-- ?? 判定是否存在 -->
<#if user??>
    user found
<#else >
    user not found<br/>
    Creating user.......<br/>
    <#assign user="Tom">
    hello ${user}
</#if>
<br/>
=============macro====================<br>
<#macro greet person color="red">
<#nested >
<font size="+2" color="${color}">Hello ${person}</font>
</#macro>
<@greet "Harry"> SomeOne</@greet> and <@greet "Potter" "green"/>
<br/>
<#macro repeat count>
    <#local y="test">
    <#list 1..count as x>
    ${y} ${count}/${x} : <#nested ><br>
    </#list>
</#macro>
<@repeat 3>${y!"?"} ${x!"?"} ${count!"?"}</@repeat>
<#macro repeat2 count>
    <#list 1..count as x>
        <#nested x,x/2,x==count><br/>
    </#list>
</#macro>
<@repeat2 4;c,halfc,last>
${c}.${halfc} <#if last>Last</#if>
</@repeat2>
<@repeat2 4;c,halfc>
${c}.${halfc}
</@repeat2>
<@repeat2 4;c>
${c}.
</@repeat2>

<#assign book="ring">
${book}<br/>
${.globals.book.bookName}<br/>

=============namespace===================<br/>
<#import "macro.ftl" as my>
<#assign email="jk@163.com">
<@my.copyright date="2016-11-01"/><br/>
my.email : ${my.email}<br/>
email: ${email}<br/>
<#assign email="jk@163.com" in my>
after modify my.email:
${my.email}<br/>


=============form====================<br>
<form id="book-form" method="post" action="${ctx}/demo/ftl/demoB">

    <label for="bookId">编号</label>
    <input id="bookId" name="bookId" TYPE="text">
    <label for="bookName">书名</label>
    <input id="bookName" name="bookName" TYPE="text">
    <label for="author">作者</label>
    <input id="author" name="author" TYPE="text">
    <label for="price">价格</label>
    <input id="price" name="price" TYPE="number">
    <button class="btn btn-primary" onclick="submitForm()">提交</button>

</form>

</#escape>
</html>
