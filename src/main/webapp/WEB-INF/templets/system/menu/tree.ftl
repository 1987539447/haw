<#escape x as x?html>
    <#include "../../common/base.ftl">
<script type="text/javascript">
    var menuSelectSetting = {
        callback: {
            onClick: clickMenuValue
        },
        data : {
            simpleData : {
                enable : true,
                idKey : "id",
                pIdKey : "pid",
                rootPId :"null"
            }
        }
    };
    function clickMenuValue(event, treeId, treeNode) {
        jQuery("#pname").val(treeNode.name);
        jQuery("#pid").val(treeNode.id);

    };

    $(document).ready(function() {
        $.ajax({
            url:"${ctx}/system/menu/rest/listAll",
            type:"get",
            success:function(data){
                alert(JSON.stringify(data));
                $.fn.zTree.init($("#menuTree"), menuSelectSetting,data);
                var zTree =$.fn.zTree.getZTreeObj("menuTree");
//                selectZtreeOneNode(jQuery("#pid").val(),"menuTree");
            }
        });
    });
</script>
<div>
    <ul id="menuTree" class="ztree"></ul>
</div>
</#escape>