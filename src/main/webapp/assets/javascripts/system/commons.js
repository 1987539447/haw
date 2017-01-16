//加载select
function selectField(url, itemId) {
    $(".chosen-select").select2({tags: true});
    //$(".chosen-select").chosen({allow_single_deselect:true});

    jQuery.ajax({
        url: url,
        type: "get",
        dataType: "json",
        success: function (data) {

            jQuery(data).each(function (i, obj) {
                jQuery("#" + itemId).append("<option value='" + obj.id + "'>" + obj.name + "</option>");
            });

            //jQuery("#" + itemId).trigger("chosen:updated");
        }
    });
}

//机构树
var user_org_modal = null;
function showOrgModal() {
    if (user_org_modal != null) {
        user_org_modal.show();
        return;
    }
    user_org_modal = $.scojs_modal({
        title: '选择部门',
        remote: "/system/org/tree2",
        uuid: "orgCheckBoxTree",
        fun_confirm: "selectOrgVal"
    });
    user_org_modal.show();
}

function selectOrgVal(uuid) {

    var orgCheckBoxTree = $.fn.zTree.getZTreeObj(uuid);
    var nodes = orgCheckBoxTree.getCheckedNodes(true);
    if (nodes.length < 1) {
        // myalert("请选择一个部门!");
        return;
    }
    var orgNames = "";
    var orgIds = "";
    jQuery(nodes).each(function (i, _obj) {
        orgNames += _obj.name + ",";
        orgIds += _obj.id + ",";
    });

    jQuery("#orgNames").val(orgNames);
    jQuery("#userOrgs").val(orgIds);
    user_org_modal.close();
}

/**
 * 根据节点ID,选中多个Ztree的节点的复选框
 * @param nodeIds
 * @param ztreeId
 */
function checkedZtreeNodes(nodeIds, ztreeId) {
    if (nodeIds == null) {
        return;
    }
    var array = nodeIds.split(",");

    if (!array || array.length < 1) {
        return;
    }
    var menuMultiSelectTree = $.fn.zTree.getZTreeObj(ztreeId);
    var tempNodes = menuMultiSelectTree.transformToArray(menuMultiSelectTree.getNodes());

    for (var i = 0; i < tempNodes.length; i++) {

        if (jQuery.inArray(tempNodes[i].id, array) >= 0) {
            menuMultiSelectTree.checkNode(tempNodes[i], true, false);
        }
    }
}



//用于非modal方式的list页面
//
function commonsaveFormWithNoModal(form,listurl,doType){
    var url = $('#' + form).attr('action');
    var type = "post";
    //alert($('#' + form).serialize());
    //如果类型为insert将自动去掉id值，否则做update操作
    if(doType=='insert'){
        $("#id").val("");
    }
    $.ajax({
        type: type,
        url: url ,
        data: $('#' + form).serialize(),
        success: function (resultInfo) {
            //alert(resultInfo.code + "" + resultInfo.info);
            if (resultInfo.code == 200) {
                myalert(resultInfo.info, function () {
                    myhref(listurl);
                });
            } else {
                myalert(resultInfo.info);
            }
        },
        dataType: "json"
    });
}

//用于modal弹出的方式
// 提交保持表单，如果id为空就insert，否则update
function commonSaveForm(form,listurl,modalName) {

    //alert($('#' + form).serialize());
    var url = $('#' + form).attr('action');
    var type = "post";
/*    if(!isNullStr($('#id').val())){
        url +=$('#id').val();
        type="PUT";
    }
    alert(url);*/
    $.ajax({
        type: type,
        url: url ,
        data: $('#' + form).serialize(),
        success: function (resultInfo) {
            //alert(resultInfo.code + "" + resultInfo.info);
            if (resultInfo.code == 200) {
                $("#"+modalName).modal("hide");
                myalert(resultInfo.info, function () {
                    myhref(listurl);
                });
            } else {
                myalert(resultInfo.info);
            }
        },
        dataType: "json"
    });
}

/**
 * 列表页面删除，弹框提示用户删除结果，再刷新指定页面,一般为列表
 *
 * @param _url
 * @param listage
 */
function mydelete(url, listage) {
    myconfirm("确定删除数据?", function() {
        deleteData(url,listage);
    });
}

function deleteData(url,listUrl){
    $.ajax({
        type: "delete",
        url: url,
        success:function (resultInfo) {
            //alert(resultInfo.code + "" + resultInfo.info);
            if (resultInfo.code == 200) {
/*                myalert(resultInfo.info, function () {
                    myhref(listUrl);
                });*/
                myhref(listUrl);
            } else {
                myalert(resultInfo.info);
            }
        }
    });
}

/* 赋值 */

function set_val(name, val) {
    if ($("#" + name + " option").length > 0) {
        $("#" + name).val(val);
        return;
    }

    if (($("#" + name).attr("type")) === "checkbox") {
        if (val == 1) {
            $("#" + name).attr("checked", true);
            return;
        }
    }
    if ($("." + name).length > 0) {
        if (($("." + name).first().attr("type")) === "checkbox") {
            var arr_val = val.split(",");
            for ( var s in arr_val) {
                $("input." + name + "[value=" + arr_val[s] + "]").attr(
                    "checked", true);
            }
        }
    }
    if (($("#" + name).attr("type")) === "text") {
        $("#" + name).val(val);
        return;
    }
    if (($("#" + name).attr("type")) === "hidden") {
        $("#" + name).val(val);
        return;
    }
    if (($("#" + name).attr("rows")) > 0) {
        $("#" + name).val(val);
        return;
    }
    //最后匹配不上的就直接赋值
    $("#" + name).val(val);
}

function myconfirm(message,fun){
    bootbox.confirm(message,function(result){
        if(result&&fun){
            fun();
        }

    });
}

function isNullStr(str){
    if(str==null||str=='') return true;
    else return false;
}


