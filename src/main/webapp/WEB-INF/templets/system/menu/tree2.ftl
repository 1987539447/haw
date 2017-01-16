<#escape x as x?html>
    <#include "../../common/base.ftl">
<script type="text/javascript">

    var menuMultiSelectSetting = {
        expandAll:true,
        check: {
            enable: true,
            chkStyle: "checkbox",
            //chkboxType:  { "Y" : "", "N" : "" }
            chkboxType: { "Y" : "ps", "N" : "ps" }
        },
        data : {
            simpleData : {
                enable : true,
                idKey : "id",
                pIdKey : "pid",
                rootPId : "null"
            }
        }
    };

    $(document).ready(function() {

        $.ajax({
            url:"${ctx}/system/menu/rest/listAll",
            type:"get",
            success:function(data){
                $.fn.zTree.init($("#menuMultiSelectTree"), menuMultiSelectSetting,data);
                checkedZtreeNodes(jQuery("#menuIds").val(),"menuMultiSelectTree");
            }
        });
    });
</script>

<div>
    <ul id="menuMultiSelectTree" class="ztree"></ul>
</div>
</#escape>