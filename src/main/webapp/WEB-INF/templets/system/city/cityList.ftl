<#escape x as x?html>
    <#import "../../common/list-parts.ftl" as ls>
    <#import "cityModal.ftl" as mod>
    <#include "../../common/base.ftl">
<div class='row-fluid'>
    <div class='span12 box bordered-box orange-border' style='margin-bottom:0;'>
        <div class='box-header purple-background'><#--purple-background-->

            <div class='title'>
                <i class="icon-group"></i>
                <@spring.message "system.city.list.title"/>
            </div>

            <div class='actions'>
                <a href="#" class="btn box-collapse btn-mini btn-link"><i></i>
                </a>
            </div>
        </div>
        <div class='box-content box-no-padding'>

        <#--查询框-->
            <@ls.search url='${ctx}/system/city/list' type=2></@ls.search>

        <#--table表单-->
            <div class='responsive-table'>
                <div class='scrollable-area' style="overflow-x: hidden">
                    <table id="table1" class='data-table-column-filter table table-bordered table-striped table-hover'
                           style='margin-bottom:0;'>
                        <thead>
                        <tr>
                            <th>
                                <@spring.message "system.city.list.areaname"/>
                            </th>
                            <th>
                                <@spring.message "system.city.list.shortname"/>
                            </th>
                            <th>
                                <@spring.message "system.city.list.parentname"/>
                            </th>
                            <th>
                                <@spring.message "system.city.list.lng"/>
                            </th>
                            <th>
                                <@spring.message "system.city.list.lat"/>
                            </th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (page.entitys??)&&(page.entitys?size>0)>
                                <#list page.entitys as city>
                                <tr id='${city.cityId}'>
                                    <td style="cursor: pointer;" onclick="showModal('${city.cityId}','look')">${city.areaname!''}</td>
                                    <td style="cursor: pointer;" onclick="showModal('${city.cityId}','look')">${city.shortname!''}</td>
                                    <td style="cursor: pointer;" onclick="showModal('${city.cityId}','look')">${city.parentname!''}</td>
                                    <td style="cursor: pointer;" onclick="showModal('${city.cityId}','look')">${city.lng!''}</td>
                                    <td style="cursor: pointer;" onclick="showModal('${city.cityId}','look')">${city.lat!''}</td>
                                    <td style="">
                                        <div class='text-right'>
                                            <a class='btn btn-primary btn-mini' href='#'
                                               title='<@spring.message "page.edit"/>'>
                                                <i class='icon-edit'
                                                   style="font-weight: bold" onclick="showModal('${city.cityId}')"> <@spring.message "page.show"/></i>
                                            </a>
<#--                                            <a class='btn btn-danger btn-mini' href='#'
                                               title='<@spring.message "page.remove"/>'>
                                                <i class='icon-trash'
                                                   style="font-weight: bold" onclick="mydelete('${ctx}/system/city/rest/${city.cityId}','${ctx}/system/city/list/')"> <@spring.message "page.remove"/></i>
                                            </a>-->
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                            </#if>
                        </tbody>
                    </table>
                <#--分页实现-->
                    <@ls.paging '${ctx}/system/city/list'></@ls.paging>
                <#--分页实现结束-->
                </div>
            </div>
        </div>
    </div>
</div>

<#--模态窗口-->
    <@mod.userModal></@mod.userModal>
</#escape>