/**
 * @author : zga
 * @date : 2016-01-12
 *
 * SSO系统Js工具类
 *
 */
function time(o,wait) {
    if (wait == 0) {
        o.val("免费获取验证码");
        o.removeAttr("disabled");
    } else {
        o.attr("disabled", true);
        o.val("重新发送(" + wait + " s)");
        wait--;
        setTimeout(function() {
            time(o,wait)
        }, 1000)
    }
}