
function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime) return;
    }
}

function ajaxSync(urlStr) {
    var arr = [];
    //alert("url = " + ctx + "/" + urlStr)
    $.ajax({
        type: "get",
        async: false, //同步执行
        url: ctx + "/" + urlStr,
        data: {},
        dataType: "json", //返回数据形式为json
        success: function (result) {
            arr = result
        },
        error: function (errorMsg) {
            console.log("不好意思，请求数据失败啦!", errorMsg.currentText);
            alert("不好意思，请求数据失败啦!", errorMsg.currentText);
            //myChart.hideLoading();
        }
    })

    return arr;
}
