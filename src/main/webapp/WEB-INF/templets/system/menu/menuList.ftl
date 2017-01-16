<#escape x as x?html>
    <#include "../../common/base.ftl">
<script type="text/javascript">
    var menuTreesetting = {
        callback: {
            onClick: MenuzTreeOnClick
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: "null"
            }
        }
    };

    $(document).ready(function () {
        $.ajax({
            url: "${ctx}/system/menu/rest/listAll",
            type: "get",
            dataType: "json",
            success: function (data) {
                $.fn.zTree.init($("#menuListTree"), menuTreesetting, data);
                var zTree = $.fn.zTree.getZTreeObj("menuListTree");
            }
        });
    });

    function MenuzTreeOnClick(event, treeId, treeNode) {
        showdata(treeNode);
    }

    function saveOrUpdate(form,listurl){
        if(!valid()){
            myalert("<@spring.message 'system.menu.modal.valid.id'/>");
            return;
        }
        commonsaveFormWithNoModal(form,listurl);
    }

    function valid(){
        if($("#id").val()==null||$("#id").val()==""){
            return false;
        }
        else return true;
    }


    function showdata(result) {
        $("#editForm select ").each(function () {
            $(this).find('option:first').attr('selected', 'selected');
        });
        for (var s in result) {
            set_val(s, result[s]);
        }

        var _pid = result["pid"];
        if ((!_pid) || _pid == null || _pid == "null" || _pid == "") {
            jQuery("#pid").val("");
        } else {
            jQuery("#pid").val(_pid);
        }

        var _pNode = result.getParentNode();
        if (_pNode) {
            jQuery("#pname").val(_pNode["name"]);
        } else {
            jQuery("#pname").val("");
        }

    }

    function deleteMenu() {
        var id = jQuery("#id").val();
        if (!id || id == "") {
            myalert("<@spring.message 'system.menu.modal.valid.non'/>");
            return;
        } else {
            var _url = "${ctx}/system/menu/rest/"+ id;
            var listurl = "${ctx}/system/menu/list/";
            mydelete(_url, listurl);
        }
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
<#--                <button class="btn btn-success icon-plus icon-large" name="button" style="margin-bottom:5px"
                        onclick="saveOrUpdate('editForm','/system/menu/list/');">
                    <i style="font-weight: bold;font-style: normal">
                        <@spring.message "page.new"/>
                    </i>
                </button>-->

                <button class='btn btn-primary icon-edit icon-large' style="margin-bottom:5px"
                        onclick="saveOrUpdate('editForm','${ctx}/system/menu/list/')">
                    <i style="font-weight: bold;font-style: normal">
                        <@spring.message "page.addOrupdatge"/>
                    </i>
                </button>

                <button class='btn btn-danger icon-trash icon-large' style="margin-bottom:5px">
                    <i style="font-weight: bold;font-style: normal"
                            onclick="deleteMenu()">
                        <@spring.message "page.remove"/>
                    </i>
                </button>
            </div>


        </div>
        <!-- 功能操作区域结束 -->
        <!-- 列表显示区域  -->
        <div class="box col-sm-3" style="margin-bottom: 0;">
            <div class="box-header blue-background">
                <div class="title"><@spring.message "system.menu.list.tree.title"/></div>
            </div>
            <div class="box-content">
                <div class="sub_left_menu">
                    <div class="well">
                        <ul id="menuListTree" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="box" style="margin-bottom: 0;float: right;width: 73.6666%">
            <div class="box-header modal-header modal-header-style">
                <div class="modal-header-font"><@spring.message "system.menu.modal.title"/></div>
            </div>
            <form id="editForm" name="editForm" action="${ctx}/system/menu/rest/" method="post"
                  class="well form-horizontal clearfix">
                <#--code-->
                <div class="control-group">
                    <label class="control-label" for="name"><@spring.message "system.menu.modal.id"/></label>

                    <div class="controls">
                        <input class="editField" name="id" id="id"
                               placeholder="<@spring.message "system.menu.modal.id"/>"
                               type="text">
                    </div>
                </div>

                <#--名称-->
                <div class="control-group">
                    <label class="control-label" for="name"><@spring.message "system.menu.modal.name"/></label>

                    <div class="controls">
                        <input class="editField" name="name" id="name"
                               placeholder="<@spring.message "system.menu.modal.name"/>"
                               type="text">
                    </div>
                </div>

                <#--url-->
                <div class="control-group">
                    <label class="control-label" for="pageurl"><@spring.message "system.menu.modal.pageurl"/></label>

                    <div class="controls">
                        <input class="editField" name="pageurl" id="pageurl"
                               placeholder="<@spring.message "system.menu.modal.pageurl"/>"
                               type="text">
                    </div>
                </div>

                <#--父节点-->
                <div class="control-group">
                    <label class="control-label" for="pid"><@spring.message "system.menu.modal.pid"/></label>

                    <div class="controls">
                        <input name="pname" class="" id="pname" type="text" readonly="readonly"/>
                        <input name="pid" id="pid" type="hidden" msg="请选择父节点" check="require"/>
                        <button data-trigger="modal" data-title="选择父节点"
                                href="${ctx}/system/menu/tree" class="btn-info icon-search icon-large"
                                style="margin-bottom:5px; height: 28px" type="button"/>
                    </div>
                </div>

                <#--类型-->
                <div class="control-group">
                    <label class="control-label" for="type"><@spring.message "system.menu.modal.type"/></label>

                    <div class="controls">
                        <select class="editField" id="type" name="type">
                            <option value="0">按钮资源</option>
                            <option value="1">导航菜单</option>
                        </select>
                    </div>
                </div>

                <#--图标-->
                <div class="control-group">
                    <label class="control-label" for="icon0"><@spring.message "system.menu.modal.icon"/></label>

                    <div class="controls">
                        <input class="editField" name="icon0" id="icon0"
                               placeholder="<@spring.message "system.menu.modal.icon"/>"
                               type="text">
                    </div>
                </div>

                <#--排序-->
                <div class="control-group">
                    <label class="control-label" for="sort"><@spring.message "system.menu.modal.sort"/></label>

                    <div class="controls">
                        <input class="editField" name="sort" id="sort"
                               placeholder="<@spring.message "system.menu.modal.sort"/>"
                               type="text">
                    </div>
                </div>

                <#--是否可用-->
                <div class="control-group">
                    <label class="control-label" for="type"><@spring.message "system.menu.modal.state"/></label>

                    <div class="controls">
                        <select class="editField" id="state" name="state">
                            <option value="是">是</option>
                            <option value="否">否</option>
                        </select>
                    </div>
                </div>

                <#--描述-->
                <div class="control-group">
                    <label class="control-label" for="description"><@spring.message "system.menu.modal.description"/></label>

                    <div class="controls">
                        <textarea class="editField" name="description" id="description"
                               placeholder="<@spring.message "system.menu.modal.description"/>">
                        </textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>
</#escape>