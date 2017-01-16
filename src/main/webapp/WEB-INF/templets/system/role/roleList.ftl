<#escape x as x?html>
    <#import "../../common/list-parts.ftl" as ls>
    <#include "../../common/base.ftl">
<script type="text/javascript">

    jQuery(document).ready(function(){
        $(".scrollable-area table tr").click(function() {
            $(".scrollable-area table tr.active").removeClass("active");
            $(this).attr("class", "active");
            var _url=ctx + "/system/role/rest/"+ $(this).attr("id");
            jQuery.ajax({
                url : _url,
                type : "get",
                dataType : "json",
                success : function(data) {
                    showdata(data);
                }
            });
            return false;
        })
    });

    function showdata(data) {
        for (var s in data) {
            set_val(s, data[s]);
        }

        var menuNames="";
        var menuIds="";
        jQuery(data.menus).each(function(i,obj){
            menuNames=menuNames+obj.name+",";
            menuIds=menuIds+obj.id+",";
        });
        jQuery("#menuNames").val(menuNames);
        jQuery("#menuIds").val(menuIds);
    }


    function saveOrUpdate(form,listurl,doType){
        if(!valid()){
            myalert("<@spring.message 'system.menu.modal.valid.id'/>");
            return;
        }
        commonsaveFormWithNoModal(form,listurl,doType);
    }

    function valid(){
        if($("#name").val()==null||$("#name").val()==""){
            return false;
        }
        else return true;
    }

    function deleteRole(){

        var id=jQuery("#id").val();
        if(!id||id==""){
            myalert("请选择你要删除的记录");
            return;
        }else{
            var _url="${ctx}/system/role/rest/"+id;
            var listurl="${ctx}/system/role/list";
            mydelete(_url,listurl);
        }
    }

    var menuModal = $.scojs_modal({
        title:"选择菜单",
        target:"#modal",
        cache:true,
        remote: "${ctx}/system/menu/tree2"
        ,uuid:"menuMultiSelectTree"
        ,fun_confirm:"selectZtreeMenu"
    });

    function showMenuModal(){
        menuModal.show();
    }

    function selectZtreeMenu(uuid){
        var zTree =$.fn.zTree.getZTreeObj(uuid);

        var nodes = zTree.getCheckedNodes(true);

        var menuNames="";
        var menuIds="";

        jQuery(nodes).each(function(i,obj){
            menuNames=menuNames+obj.name+",";
            menuIds=menuIds+obj.id+",";
        });
        jQuery("#menuNames").val(menuNames);
        jQuery("#menuIds").val(menuIds);
        menuModal.close();
    }
</script>

<div class='row-fluid'>
    <div class='span12 box bordered-box orange-border' style='margin-bottom:0;'>
        <div class='box-header purple-background'><#--purple-background-->

            <div class='title'>
                <i class="icon-group"></i>
                <@spring.message "system.menu.list.title"/>
            </div>

            <div class='actions'>
                <a href="#" class="btn box-collapse btn-mini btn-link"><i></i>
                </a>
            </div>
        </div>

        <div class="box-content">
            <div style="text-align: right;">

                <button class='btn btn-success icon-plus icon-large' style="margin-bottom:5px"
                        onclick="saveOrUpdate('editForm','/system/role/list/','insert')">
                    <i style="font-weight: bold;font-style: normal">
                        <@spring.message "page.new"/>
                    </i>
                </button>

                <button class='btn btn-primary icon-edit icon-large' style="margin-bottom:5px"
                        onclick="saveOrUpdate('editForm','/system/role/list/')">
                    <i style="font-weight: bold;font-style: normal">
                        <@spring.message "page.edit"/>
                    </i>
                </button>

                <button class='btn btn-danger icon-trash icon-large' style="margin-bottom:5px">
                    <i style="font-weight: bold;font-style: normal"
                       onclick="deleteRole()">
                        <@spring.message "page.remove"/>
                    </i>
                </button>
            </div>

        </div>
        <!-- 功能操作区域结束 -->
        <!-- 列表显示区域  -->

        <div class="box" style="margin-bottom: 0;float: left;width: 50%">
<#--            <div class="box-header blue-background">
                <div class="title"><@spring.message "system.role.list.title"/></div>
            </div>-->
        <#--查询框-->
            <@ls.search url='${ctx}/system/role/list' type=2>></@ls.search>

        <#--table表单-->
            <div class='responsive-table'>
                <div class='scrollable-area' style="overflow-x: hidden">
                    <table id="table1" class='data-table-column-filter table table-bordered table-striped table-hover'
                           style='margin-bottom:0;'>
                        <thead>
                        <tr>
                            <th>
                                <@spring.message "system.role.list.name"/>
                            </th>
                            <th>
                                <@spring.message "system.role.list.state"/>
                            </th>
                            <th>
                                <@spring.message "system.role.list.remark"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (page.entitys??)&&(page.entitys?size>0)>
                                <#list page.entitys as role>
                                <tr id='${role.id}'>
                                    <td style="cursor: pointer;">${role.name!''}</td>
                                    <td style="cursor: pointer;">
                                      <#if role.state=="是">
                                          <span class='label label-success'> <@spring.message "system.role.list.state.ok"/></span>
                                      <#else>
                                          <span class='label label-warning'> <@spring.message "system.role.list.state.no"/></span>
                                      </#if>
                                    </td>
                                    <td style="cursor: pointer;">${role.remark!''}</td>
                                </tr>
                                </#list>
                            </#if>
                        </tbody>
                    </table>
                <#--分页实现-->
                    <@ls.paging '${ctx}/system/role/list'></@ls.paging>
                <#--分页实现结束-->
                </div>
            </div>
        </div>

    <#--modal显示区域-->
        <div class="box" style="margin-bottom: 0;float: right;width: 48.8%">
            <div class="box-header modal-header modal-header-style">
                <div class="modal-header-font"><@spring.message "system.role.modal.title"/></div>
            </div>
            <form id="editForm" name="editForm" action="${ctx}/system/role/rest/" method="post"
                  class="well form-horizontal clearfix">
                 <input type="hidden" id="id" name="id">
            <#--名称-->
                <div class="control-group">
                    <label class="control-label" for="name"><@spring.message "system.role.modal.name"/></label>

                    <div class="controls">
                        <input class="editField" name="name" id="name"
                               placeholder="<@spring.message "system.role.modal.name"/>"
                               type="text">
                    </div>
                </div>

            <#--父节点-->
                <div class="control-group">
                    <label class="control-label" for="menuIds"><@spring.message "system.role.modal.menuIds"/></label>

                    <div class="controls">
                        <input name="menuNames" class="" id="menuNames" type="text" readonly="readonly"/>
                        <input name="menuIds" id="menuIds" type="hidden" msg="" check="require"/>
                        <button class="btn-info icon-search icon-large"
                                style="margin-bottom:5px; height: 28px" type="button" name=""
                                onclick="showMenuModal();"/>
                    </div>
                </div>

            <#--是否可用-->
                <div class="control-group">
                    <label class="control-label" for="type"><@spring.message "system.role.modal.state"/></label>

                    <div class="controls">
                        <select class="editField" id="state" name="state">
                            <option value="是">是</option>
                            <option value="否">否</option>
                        </select>
                    </div>
                </div>

            <#--备注-->
                <div class="control-group">
                    <label class="control-label" for="icon"><@spring.message "system.role.modal.remark"/></label>

                    <div class="controls">
                        <textarea class="editField" name="remark" id="remark"
                                  placeholder="<@spring.message "system.role.modal.remark"/>">
                        </textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>
</#escape>