jQuery("document").ready(function () {
    ajaxSetup();
    menu();
});

/**
 * 获取所有导航资源
 */
function menu() {

    jQuery.ajax({
        url: ctx + "/index/menu",
        type: "post",
        cache: false,
        async: false,
        scriptCharset: "utf-8",
        dataType: "json",
        success: function (_json) {
            buildModule(_json);
        }
    });
}

function buildModule(data) {
    if (data != null && typeof (data) != "undefined") {

        for (var i = 0; i < data.length; i++) {
            var html = '';

            html = getParentModule(data[i]);

            jQuery("#navigationBar").append(html);
        }
    }
}

function getParentModule(json) {

    var _leaf = json["leaf"];

    var t = '<li><a class="dropdown-collapse" ';
    if (_leaf && _leaf.length > 0) {
        t = t + ' href="#">';
    } else {
        t = t + '  id="href_' + json["id"] + '  href="\\' + ctx + json["pageurl"] + '"' + '>';
    }

    t = t + '<i class="'+json["icon0"]+'"></i><span>' + json["name"] + '</span>';

    if (_leaf && _leaf.length > 0) {
        t = t + '<i class="icon-angle-down angle-down"></i></a>';
        var m = "<ul class='nav nav-stacked'>";

        for (var i = 0; i < _leaf.length; i++) {
            var n = getChindModule(_leaf[i], '');
            m = m + n;
        }
        t = t + m + "</ul>";

    } else {

        t = t + "</a>";

    }

    t = t + "</li>";

    return t;
}

function getChindModule(json, html) {

    var _leaf = json["leaf"];
    var t = '<li class = ""><a  ';
    if (_leaf && _leaf.length > 0) {
        t = t + ' class="dropdown-toggle" href="#">';
    } else {
        var url = '\'' + ctx + json["pageurl"] + '\'';
        t = t + '  id=\'href_' + json["id"] + '\' href="javascript:mynavhref(' + url + ')">';
        // t =t+ '  id="href_' +json["id"] + '"  href=javascript:mynavhref('+url+')'+'>';
    }
    t = t + '<i class="icon-caret-right"></i>' + '<span>' + json["name"] + '</span>';

    if (_leaf && _leaf.length > 0) {
        t = t + '<i class="icon-angle-down angle-down"></i></a>';
        var m = "<ul class='nav nav-stacked'>";
        for (var i = 0; i < _leaf.length; i++) {
            var n = getChindModule(_leaf[i], '');
            m = m + n;
        }
        m = m + "</ul>";
        t = t + m + "</li>";
    } else {
        t = t + '</a></li>';

    }
    html = html + t;
    return html;

}

function mynavhref(url, name) {

    myhref(url,1,10);

    jQuery('#mainFormName').html(name);

}

function myhref(url, pageNum, pageSize) {
    jQuery('#mainForm').ajaxSubmit({
        url: url,
        type: "POST",
        data: {
            'pageNum': pageNum,
            'pageSize': pageSize
        },
        /*        success: function(data){
         jQuery('#mainPage').html(data);
         }*/
        target: '#mainPage'
    });
}

function myalert(message,fun){
    if(fun){
        bootbox.alert(message,fun);
    }else{
        bootbox.alert(message);
    }
}

function listhref(url){
    jQuery('#listForm').ajaxSubmit({
        url: url,
        type: "POST",
        target: '#mainPage'
    });
}

/*
function listhref(url,searchName,searchValue){

}
*/

function ajaxSetup() {
    $.ajaxSetup({
        complete: function (XMLHttpRequest, ts) {
            var value = XMLHttpRequest.getResponseHeader("PageNameValue");
            // alert(XMLHttpRequest.getAllResponseHeaders());
            if (value == "login") {
                window.location.replace("/goToLogin");
            }
        }
    });
}

