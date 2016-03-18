/**
 * @author : zga
 * @date : 2016/3/18
 *
 * 站点日志
 *
 */
$(function(){
    $('#fromDate').focus(function(){
        WdatePicker({
            isShowToday:false,
            isShowOK:false,
            isShowClear:false,
            onpicked:function(){
                $('#toDate').focus();
            }
        });
    });

    $('#toDate').focus(function(){
        WdatePicker({
            minDate:"#F{$dp.$D('fromDate')}"
        });
    });
});

/**
 * @author : zga
 * @date : 2016-3-18
 *
 * 页面载入时添加选中样式
 *
 */
window.onload = function(){
    $('#site_log').addClass('selected');
};