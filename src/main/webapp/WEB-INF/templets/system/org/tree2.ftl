<#escape x as x?html>
    <#include "../../common/base.ftl">
<SCRIPT type="text/javascript">
<!--
	var orgcheckBoxSetting = {
		check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType:  { "Y" : "", "N" : "" }
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid",
				rootPId : "null",
			}
		}
	};

	$(document).ready(function() {
		jQuery.get("${ctx}/system/org/rest/listAll",
				function(data) {
					$.fn.zTree.init($("#orgCheckBoxTree"), orgcheckBoxSetting,data);
					var zTree =$.fn.zTree.getZTreeObj("orgCheckBoxTree");
					zTree.expandAll(true);
					checkedZtreeNodes(jQuery("#userOrgs").val(),"orgCheckBoxTree");
				});
	});

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
//-->
</SCRIPT>

<div>
	<ul id="orgCheckBoxTree" class="ztree"></ul>
</div>
</#escape>