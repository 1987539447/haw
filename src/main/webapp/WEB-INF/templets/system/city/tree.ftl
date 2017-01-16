<#escape x as x?html>
    <#include "../../common/base.ftl">
<script type="text/javascript">
    var citySelectSetting = {
        callback: {
            onClick: clickCityValue
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
    function clickCityValue(event, treeId, treeNode) {
        jQuery("#pname").val(treeNode.name);
        jQuery("#cityId").val(treeNode.id);
        jQuery("#cityTreeModal").hide();
    };

    $(document).ready(function() {
        $.ajax({
            url:"${ctx}/system/city/rest/listAllVo",
            type:"get",
            success:function(data){
                alert(data);
                $.fn.zTree.init($("#cityTree"), citySelectSetting,data);
                var zTree =$.fn.zTree.getZTreeObj("cityTree");
            }
        });
    });
</script>
<div style="height: 450px;overflow:scroll; border:1px solid;">
    <ul id="cityTree" class="ztree"></ul>
</div>
</#escape>