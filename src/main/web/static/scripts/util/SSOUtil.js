/**
 * @author : zga
 * @date : 2016-01-12
 *
 * SSO系统Js工具类
 *
 */
function time(o,wait,buttonVal) {
    if (wait == 0) {
        o.val(buttonVal);
        o.removeAttr("disabled");
    } else {
        o.attr("disabled", true);
        o.val("重新发送(" + wait + " s)");
        wait--;
        setTimeout(function () {
            time(o, wait, buttonVal)
        }, 1000)
    }
}


