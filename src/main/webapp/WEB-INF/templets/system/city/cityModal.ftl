<#macro userModal>
    <#include "../../common/base.ftl">
<script type="application/javascript">


    //添加值到modal界面
    function showdata(data) {

        $('.editField').attr("disabled", "disabled");

        for (var s in data) {
            set_val(s, data[s]);
        }
    }

    //添加窗口事件
    function showModal(id, type) {
        if (id != null) {
            jQuery.ajax({
                url: "${ctx}/system/city/rest/" + id + ".json",
                type: "get",
                dataType: "json",
                success: function (data) {
                    showdata(data, type);
                }
            });
        } else {
            showdata(null, type)
        }

        $('#city-modal').modal();
    }

</script>

<div class='modal hide fade modal-style' id='city-modal' role='dialog' tabindex='-1'>
    <div class='modal-header modal-header-style box-header'>
        <a href="#" class="modal-header-btn-link" data-dismiss='modal'>
            <i class="icon-remove"></i>
        </a>
        <i class="icon-user"></i>
        <@spring.message "system.city.modal.title"/>
    </div>
    <div class='modal-body'>

        <form id="modal-form" method="post" action="" class='form form-horizontal'
              style='margin-bottom: 0;'>

        <#--cityId-->
            <div class="control-group">
                <label class="control-label" for="cityId"><@spring.message "system.city.modal.cityId"/></label>

                <div class="controls">
                    <input class="editField" name="cityId" id="cityId"
                           placeholder="<@spring.message "system.city.modal.cityId"/>"
                           type="text">
                </div>
            </div>
        <#--areaname-->
            <div class="control-group">
                <label class="control-label" for="areaname"><@spring.message "system.city.list.areaname"/></label>

                <div class="controls">
                    <input class="editField" name="areaname" id="areaname"
                           placeholder="<@spring.message "system.city.list.areaname"/>"
                           type="text">
                </div>
            </div>
        <#--shortname-->
            <div class="control-group">
                <label class="control-label" for="shortname"><@spring.message "system.city.list.shortname"/></label>

                <div class="controls">
                    <input class="editField" name="shortname" id="shortname"
                           placeholder="<@spring.message "system.city.list.shortname"/>"
                           type="text">
                </div>
            </div>

        <#--parentname-->
            <div class="control-group">
                <label class="control-label" for="parentname"><@spring.message "system.city.list.parentname"/></label>

                <div class="controls">
                    <input class="editField" name="parentname" id="parentname"
                           placeholder="<@spring.message "system.city.list.parentname"/>"
                           type="text">
                </div>
            </div>

        <#--lng-->
            <div class="control-group">
                <label class="control-label" for="lng"><@spring.message "system.city.list.lng"/></label>

                <div class="controls">
                    <input class="editField" name="lng" id="lng"
                           placeholder="<@spring.message "system.city.list.lng"/>"
                           type="text">
                </div>
            </div>

        <#--lat-->
            <div class="control-group">
                <label class="control-label" for="lat"><@spring.message "system.city.list.lat"/></label>

                <div class="controls">
                    <input class="editField" name="lat" id="lat"
                           placeholder="<@spring.message "system.city.list.lat"/>"
                           type="text">
                </div>
            </div>

        <#--level-->
            <div class="control-group">
                <label class="control-label" for="level"><@spring.message "system.city.list.level"/></label>

                <div class="controls">
                    <input class="editField" name="level" id="level"
                           placeholder="<@spring.message "system.city.list.level"/>"
                           type="text">
                </div>
            </div>

        </form>
    </div>

    <div class='modal-footer'>
        <button class='btn' data-dismiss='modal'><@spring.message "system.modal.close"/></button>
    </div>
</div>
</#macro>