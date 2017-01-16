<#--分页-->
<#macro paging actionUrl>

<script type="application/javascript">
    <#--生成分页1,2,3,4,5...点击按钮-->
    var totalPage = ${page.pages};

    var currentPage = ${page.pageNum};

    var url = '${actionUrl}';

    <#--
    alert("totalPage: " + totalPage + " currentPage:" + currentPage + " pageSize: ${page.pageSize}");
    -->
    jQuery("document").ready(function () {
        addPageUls();
        addEvent();
    });

    function addPageUls() {
        var uls = $('#pageUls');
    <#--添加上一页按钮-->
        if (currentPage == 1) {
            uls.append('<li id="prev" class="prev disabled"><a><@spring.message "page.sPrevious"/></a></li>');
        } else {
            uls.append('<li id="prev" class="prev"><a style="cursor:pointer"><@spring.message "page.sPrevious"/></a></li>');
        }
    <#--添加中间的导航页按钮-->
        var temp_num;
        for (var i = 1, num = currentPage; i <= 8; i++, num--) {
            if (num >= 1) {
                temp_num=i;            temp_num=i;
                if (num == currentPage) {//当为当前页面的时候采用不同的样式
                    uls.append('<li class="active"><a href="#">' + num + '</a></li>');
                } else {
                    $('#prev').after('<li><a style="cursor: pointer;" onclick="toPage(' + num + ')">' + num + '</a></li>');
                }
            } else{
                break;
            }
        }
        if (currentPage < totalPage) {
            for (var n = currentPage + 1, j = 1; j <= 10-temp_num; n++, j++) {
                if (n > totalPage) break;
                uls.append('<li><a style="cursor: pointer;" onclick="toPage(' + n + ')">' + n + '</a></li>');
            }
        }

    <#--添加下一页按钮-->
        if (currentPage == totalPage) {
            uls.append('<li id="next" class="next disabled"><a><@spring.message "page.sNext"/></a></li>');
        } else {
            uls.append('<li id="next" class="next"><a style="cursor:pointer"><@spring.message "page.sNext"/></a></li>');
        }
    }

    function toPage(n) {
    <#--myhref(url,n,${page.pageSize});-->
        $("#pageNum").val(n);
        listhref(url);
    }

    function addEvent() {

        //前一页
        if ($('#prev').hasClass('disabled') == false) {
            $('#prev > a').bind("click", function () {
            <#--myhref(url,currentPage-1,${page.pageSize});-->
                $("#pageNum").val(currentPage - 1);
                listhref(url);
            });
        }


        //后一页
        if ($('#next').hasClass('disabled') == false) {
            $('#next > a').bind("click", function () {
            <#--myhref(url,currentPage+1,${page.pageSize});-->
                $("#pageNum").val(currentPage + 1);
                listhref(url);
            });
        }
    }

</script>
<div class="row-fluid">
    <div class="span6">
        <div class="dataTables_info"
             id="DataTables_Table_1_info"><@spring.message "page.sInfo1"/> ${page.total} <@spring.message "page.sInfo2"/>
        </div>
    </div>
    <div class="span6 text-right">
        <div class="dataTables_paginate paging_bootstrap pagination pagination-small">
            <ul id="pageUls">
            </ul>
        </div>
    </div>
</div>

</#macro>
<#--如果type=1,代表是modal样式的查询栏,有新增按钮,如果type=2代表为平面样式的查询栏,没有新增按钮-->
<#macro search url type>
<script type='text/javascript'>
    var url = $[url];
    $("document").ready(function () {

        $(".box .box-collapse").click(function (e) {
                    var box;
                    box = $(this).parents(".box").first();
                    box.toggleClass("box-collapsed");
                    return e.preventDefault();
                }
        );``

        //每页显示多少数据的功能
        $("#tableSize").val(${page.pageSize});  //赋值

        $("#tableSize").change(function () {
            var selectValue = $("#tableSize option:selected").val();
            myhref(url, 1, selectValue);
        });

        $("#searchButton").on("click", function () {
            $("#fuzzyField").val($("#searchStr").val());
            $("#pageNum").val(1);//将页码执为1
            listhref(url);//$.parseJSON(dataStr)
        });
    });
</script>

<#--from用于ajax提交以及search用-->
<form id="listForm" style="display: none" method="post">
    <input id="fuzzyField" name="fuzzyField" value="${page.fuzzyField!''}">
    <input id="pageNum" name="pageNum" value="${page.pageNum!''}">
    <input id="pageSize" name="pageSize" value="${page.pageSize!''}">
</form>
<div class="box-content">
    <div style="float: left">
        <label style="vertical-align:middle">
            <span><@spring.message "page.size"/></span>
            <select size="1" id="tableSize" style="width: 70px;margin: 0;">
                <option value="5">5</option>
                <option value="10" selected="selected">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
        </label>
    </div>
    <div style="text-align: right;">
        <input id="searchStr" class="" style="margin: 0;" type="text" value="${page.fuzzyField!''}">
        <button id="searchButton" class="btn icon-search icon-large" name="button" style="margin-bottom:5px"
                type="button"></button>
        <#if type=1>
        <button class="btn btn-success icon-plus icon-large" name="button" style="margin-bottom:5px" onclick="showModal(null,'new')">
            <i style="font-weight: bold;font-style: normal">
                <@spring.message "page.new"/>
            </i>
        </button>
        </#if>
    </div>
</div>
</#macro>
