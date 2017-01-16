(function() {
    $(document).ready(function() {
        var body, content, nav, nav_closed_width, nav_open, nav_toggler;

        nav_toggler = $("header .toggle-nav");
        nav = $("#main-nav");
        content = $("#content");
        body = $("body");
        nav_closed_width = 50;
        nav_open = body.hasClass("main-nav-opened") || nav.width() > nav_closed_width;
        $("#main-nav .dropdown-collapse").on("click", function(e) {
            var link, list;
            //preventDefault() 方法阻止元素发生默认的行为（例如，当点击提交按钮时阻止对表单的提交）。
            e.preventDefault();
            link = $(this);
            list = link.parent().find("> ul");
            if (list.is(":visible")) {
                if (body.hasClass("main-nav-closed") && link.parents("li").length === 1) {//===为恒等,如果类型不同也不会相等,==会先转化类型
                    false;
                } else {
                    link.removeClass("in");
                    //jquery的slideup方法采用滑动的方式显示元素
                    list.slideUp(300, function() {
                        return $(this).removeClass("in");
                    });
                }
            } else {
                link.addClass("in");
                //jquery的slideDown方法采用滑动的方式隐藏元素
                list.slideDown(300, function() {
                    return $(this).addClass("in");
                });
            }
            return false;
        });
        nav.swiperight(function(event, touch) {
            return $(document).trigger("nav-open");
        });
        nav.swipeleft(function(event, touch) {
            return $(document).trigger("nav-close");
        });
        nav_toggler.on("click", function() {
            if (nav_open) {
                $(document).trigger("nav-close");
            } else {
                $(document).trigger("nav-open");
            }
            return false;
        });
        $(document).bind("nav-close", function(event, params) {
            body.removeClass("main-nav-opened").addClass("main-nav-closed");
            return nav_open = false;
        });
        return $(document).bind("nav-open", function(event, params) {
            body.addClass("main-nav-opened").removeClass("main-nav-closed");
            return nav_open = true;
        });
    });

}).call(this);