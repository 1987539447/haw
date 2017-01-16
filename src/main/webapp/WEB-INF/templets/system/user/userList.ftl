<#escape x as x?html>
    <#import "../../common/list-parts.ftl" as ls>
    <#import "userModal.ftl" as mod>
    <#include "../../common/base.ftl">
<div class='row-fluid'>
    <div class='span12 box bordered-box orange-border' style='margin-bottom:0;'>
        <div class='box-header purple-background'><#--purple-background-->

            <div class='title'>
                <i class="icon-group"></i>
                <@spring.message "system.user.list.title"/>
            </div>

            <div class='actions'>
                <a href="#" class="btn box-collapse btn-mini btn-link"><i></i>
                </a>
            </div>
        </div>
        <div class='box-content box-no-padding'>

        <#--查询框-->
            <@ls.search url='${ctx}/system/user/list' type=1></@ls.search>

        <#--table表单-->
            <div class='responsive-table'>
                <div class='scrollable-area' style="overflow-x: hidden">
                    <table id="table1" class='data-table-column-filter table table-bordered table-striped table-hover'
                           style='margin-bottom:0;'>
                        <thead>
                        <tr>
                            <th>
                                <@spring.message "system.user.list.account"/>
                            </th>
                            <th>
                                <@spring.message "system.user.list.name"/>
                            </th>
                            <th>
                                <@spring.message "system.user.list.workno"/>
                            </th>
                            <th>
                                <@spring.message "system.user.list.state"/>
                            </th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (page.entitys??)&&(page.entitys?size>0)>
                                <#list page.entitys as user>
                                <tr id='${user.id}'>
                                    <td style="cursor: pointer;" onclick="showModal('${user.id}','look')">${user.account!''}</td>
                                    <td style="cursor: pointer;" onclick="showModal('${user.id}','look')">${user.name!''}</td>
                                    <td style="cursor: pointer;" onclick="showModal('${user.id}','look')">${user.workno!''}</td>
                                    <td>
                                        <#if user.state=="是">
                                            <span class='label label-success'> <@spring.message "system.user.list.state.ok"/></span>
                                        <#else>
                                            <span class='label label-warning'> <@spring.message "system.user.list.state.no"/></span>
                                        </#if>
                                    </td>
                                    <td style="">
                                        <div class='text-right'>
                                            <a class='btn btn-primary btn-mini' href='#'
                                               title='<@spring.message "page.edit"></@spring.message>'>
                                                <i class='icon-edit'
                                                   style="font-weight: bold" onclick="showModal('${user.id}','edit')"> <@spring.message "page.edit"/></i>
                                            </a>
                                            <a class='btn btn-danger btn-mini' href='#'
                                               title='<@spring.message "page.remove"></@spring.message>'>
                                                <i class='icon-trash'
                                                   style="font-weight: bold" onclick="mydelete('${ctx}/system/user/rest/${user.id}','${ctx}/system/user/list/')"> <@spring.message "page.remove"/></i>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                            </#if>
                        </tbody>
                    </table>
                <#--分页实现-->
                    <@ls.paging '${ctx}/system/user/list'></@ls.paging>
                <#--分页实现结束-->
                </div>
            </div>
        </div>
    </div>
</div>

<#--模态窗口-->

    <@mod.userModal></@mod.userModal>
</#escape>