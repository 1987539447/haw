<#macro userModal>
    <#include "../../common/base.ftl">
<script type="application/javascript">

    $(document).ready(function () {


        //加载角色
        selectField("${ctx}/system/role/rest/listAll", "userRoles");

        //加载
        <#--selectField2("${ctx}/system/city/rest/listAll", "cityId");-->
        /*        //机构树0

                $("#userOrgsButton").click(function () {
                    showOrgModal();
                });*/
    });

    var cityTree = $.scojs_modal({
        title:"选择菜单",
        target:"#cityTreeModal",
        cache:true,
        remote: "${ctx}/system/city/tree",
        uuid:"cityTree"
    });

    function showCityTree(){
        cityTree.show();
    }


    function selectField2(url, itemId) {
        $(".chosen-select").select2({tags: true});
        jQuery.ajax({
            url: url,
            type: "get",
            dataType: "json",
            success: function (data) {
                jQuery("#" + itemId).append("<option value=''>空</option>");
                jQuery(data).each(function (i, obj) {
                    jQuery("#" + itemId).append("<option value='" + obj.cityId + "'>" + obj.areaname + "</option>");
                });
            }
        });
    }

    //添加窗口事件
    function showModal(id, type) {
        if (id != null) {
            jQuery.ajax({
                url: "${ctx}/system/user/rest/" + id + ".json",
                type: "get",
                dataType: "json",
                success: function (data) {
                    showdata(data, type);
                }
            });
        } else {
            showdata(null, type)
        }

        $('#user-modal').modal();
    }

    //提交保存
    function submitForm() {
        if (verify()) {
            //$('#user-modal').modal('hide');
            commonSaveForm('modal-form', '${ctx}/system/user/list/', 'user-modal');
        }

    }

    function verify() {
        if (isNullStr($("#name").val())) {
            myalert("用户名为空！");
            return false;
        } else if (isNullStr($("#account").val())) {
            myalert("账号为空！");
            return false;
        } else if (isNullStr($("#password").val())) {
            myalert("密码为空！");
            return false;
        } else if ($("#password").val() != $("#repswd").val()) {
            myalert("两次密码输入不一致！");
            return false;
        }
        return true;
    }

    //添加值到modal界面
    function showdata(data, type) {

        if (type == 'new' || type == 'edit') {

            $('.editField').removeAttr('disabled');

            jQuery("#id").val('');
            jQuery("#account").val('');
            jQuery("#name").val('');

            $('.modal-submit').show();
            jQuery("#passLook").show();

            jQuery("#name").val('');
            jQuery("#workno").val('');
            jQuery("#cardno").val('');
            jQuery("#phone").val('');
            jQuery("#mobile").val('');
            jQuery("#email").val('');
            jQuery("#address").val('');
        }
        //如果是新增就不在添加数据了
        if (type == 'new') {
            return;
        }

        jQuery("#id").val(data.id);

        jQuery("#account").val(data.account);

        jQuery("#name").val(data.name);


        if (type == 'look') {
            $('.editField').attr("disabled", "disabled");//如果是查询就将所有字段设置为不可修改
            $('.modal-submit').hide();
            jQuery("#passLook").hide();
        } else if (type == 'edit') {
            jQuery("#password").val(data.password);
            jQuery("#repswd").val(data.password);
            jQuery("#passLook").show();
        }

        jQuery("#name").val(data.name);

        $("#userRoles").find("option:selected").attr("selected", false);
        jQuery(data["userRoles"]).each(function (i, _obj) {
            jQuery("#userRoles option[value='" + _obj.id + "']").attr("selected", true);
        });
        jQuery("#userRoles").trigger("change");

/*        $("#cityId").find("option:selected").attr("selected", false);
        jQuery("#cityId option[value='" + data.cityId + "']").attr("selected", true);
        jQuery("#cityId").trigger("change");*/

        $.ajax({
            type:"get",
            url:"${ctx}/system/city/rest/"+data.cityId,
            datatype:"json",
            success:function(data){
                jQuery("#cityId").val(data.cityId);
                jQuery("#pname").val(data.areaname);
            }
        });

        jQuery("#workno").val(data.workno);

        jQuery("#cardno").val(data.cardno);

        if (data.sex == '女') {
            jQuery("#sex").val('女');
        }

        jQuery("#phone").val(data.phone);

        jQuery("#mobile").val(data.mobile);

        jQuery("#email").val(data.email);

        jQuery("#address").val(data.address);

        if (data.state == '否') {
            jQuery("#state").val('否');
        }

    }

</script>

<div class='modal hide fade modal-style' id='user-modal' role='dialog' tabindex='-1'>
    <div class='modal-header modal-header-style box-header'>
        <a href="#" class="modal-header-btn-link" data-dismiss='modal'>
            <i class="icon-remove"></i>
        </a>
        <i class="icon-user"></i>
        <@spring.message "system.user.modal.title"/>
    </div>
    <div class='modal-body'>

        <form id="modal-form" method="post" action="${ctx}/system/user/rest/" class='form form-horizontal'
              style='margin-bottom: 0;'>

        <#--id-->
            <input name="id" id="id" value="" style="display:none ">
        <#--account-->
            <div class="control-group">
                <label class="control-label" for="account"><@spring.message "system.user.modal.account"/></label>

                <div class="controls">
                    <input class="editField" name="account" id="account"
                           placeholder="<@spring.message "system.user.modal.account"/>"
                           type="text">
                </div>
            </div>
        <#--name-->
            <div class="control-group">
                <label class="control-label" for="name"><@spring.message "system.user.modal.name"/></label>

                <div class="controls">
                    <input class="editField" name="name" id="name"
                           placeholder="<@spring.message "system.user.modal.name"/>"
                           type="text">
                </div>
            </div>
            <hr class="hr-normal">
        <#--password-->
            <div id="passLook">
                <div class="control-group">
                    <label class="control-label" for="password"><@spring.message "system.user.modal.password"/></label>

                    <div class="controls">
                        <input class="editfield" name="password" id="password"
                               placeholder="<@spring.message "system.user.modal.password"/>"
                               type="password">
                    </div>
                </div>
            <#--re password-->
                <div class="control-group" id="repswdlook">
                    <label class="control-label" for="repswd"><@spring.message "system.user.modal.repswd"/></label>

                    <div class="controls">
                        <input class="editfield" id="repswd" placeholder="<@spring.message "system.user.modal.repswd"/>"
                               type="password"
                               value="top secret!">
                    </div>
                </div>
                <hr class="hr-normal">
            </div>
        <#--        &lt;#&ndash; orgs&ndash;&gt;
                    <div class="control-group">
                        <label class="control-label" for="orgNames"><@spring.message "system.user.modal.orgs"/></label>

                        <div class="controls">
                            <input id="orgNames" readonly placeholder="<@spring.message "system.user.modal.orgs"/>" type="text">
                            <input type="hidden" msg="请选择部门" id="userOrgs" name="userOrgs" value="">
                            <button id="userOrgsButton" class="editField btn-info icon-search icon-large" name="userOrgsButton"
                                    style="margin-bottom:5px; height: 28px"/>
                        </div>

                    </div>-->
        <#--roles-->
            <div class="control-group">
                <label class="control-label" for="userRoles"><@spring.message "system.user.modal.userRoles"/></label>

                <div class="controls">
                    <select id="userRoles" name="userRoles" class="editField chosen-select" multiple="multiple"
                            style="width: 215px;">
                    </select>
                </div>
            </div>

        <#--city-->
<#--            <div class="control-group">
                <label class="control-label" for="cityId"><@spring.message "system.user.modal.cityId"/></label>

                <div class="controls">
                    <select id="cityId" name="cityId" class="editField chosen-select"
                            style="width: 215px;">
                    </select>
                </div>
            </div>-->
        <#--city-->
            <div class="control-group">
                <label class="control-label" for="cityId"><@spring.message "system.user.modal.cityId"/></label>

                <div class="controls">
                    <input name="pname" class="" id="pname" type="text" readonly="readonly"/>
                    <input name="cityId" id="cityId" type="hidden" msg="" check="require"/>
                    <button class="editfield btn-info icon-search icon-large"
                            style="margin-bottom:5px; height: 28px" type="button" name=""
                            onclick="showCityTree();"/>
                </div>
            </div>

            <hr class="hr-normal">
        <#--work on-->
            <div class="control-group">
                <label class="control-label" for="workno"><@spring.message "system.user.modal.workno"/></label>

                <div class="controls">
                    <input class="editField" name="workno" id="workno"
                           placeholder="<@spring.message "system.user.modal.workno"/>"
                           type="text">
                </div>
            </div>

        <#--card no-->
            <div class="control-group">
                <label class="control-label" for="cardno"><@spring.message "system.user.modal.cardno"/></label>

                <div class="controls">
                    <input class="editField" name="cardno" id="cardno"
                           placeholder="<@spring.message "system.user.modal.cardno"/>"
                           type="text">
                </div>
            </div>

        <#--sex-->
            <div class="control-group">
                <label class="control-label" for="sex"><@spring.message "system.user.modal.sex"/></label>

                <div class="controls">
                    <select class="editField" id="sex" name="sex">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
            </div>

            <hr class="hr-normal">

        <#--phone-->
            <div class="control-group">
                <label class="control-label" for="phone"><@spring.message "system.user.modal.phone"/></label>

                <div class="controls">
                    <input class="editField" name="phone" id="phone"
                           placeholder="<@spring.message "system.user.modal.phone"/>"
                           type="text">
                </div>
            </div>

        <#--mobile-->
            <div class="control-group">
                <label class="control-label" for="mobile"><@spring.message "system.user.modal.mobile"/></label>

                <div class="controls">
                    <input class="editField" name="mobile" id="mobile"
                           placeholder="<@spring.message "system.user.modal.mobile"/>"
                           type="text">
                </div>
            </div>

        <#--email-->
            <div class="control-group">
                <label class="control-label" for="email"><@spring.message "system.user.modal.email"/></label>

                <div class="controls">
                    <input class="editField" name="email" id="email"
                           placeholder="<@spring.message "system.user.modal.email"/>"
                           type="text">
                </div>
            </div>

        <#--address-->
            <div class="control-group">
                <label class="control-label" for="address"><@spring.message "system.user.modal.address"/></label>

                <div class="controls">
                    <input class="editField" name="address" id="address"
                           placeholder="<@spring.message "system.user.modal.address"/>"
                           style="width: 350px"
                           type="text">
                </div>
            </div>
            <hr class="hr-normal">

        <#--state-->
            <div class="control-group">
                <label class="control-label" for="state"><@spring.message "system.user.modal.state"/></label>

                <div class="controls">
                    <select class="editField" id="state" name="state"
                            class="form-control col-10">
                        <option value="是">是</option>
                        <option value="否">否</option>
                    </select>
                </div>
            </div>
        </form>
    </div>

    <div class='modal-footer'>
        <button class='btn' data-dismiss='modal'><@spring.message "system.modal.close"/></button>
        <button class='modal-submit btn btn-primary'
                onclick="submitForm()"><@spring.message "system.modal.save"/></button>
    </div>
</div>
</#macro>