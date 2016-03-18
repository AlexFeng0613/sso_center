/**
 * @author : zga
 * @date : 2016-3-10
 *
 * 新增用户
 *
 */
$(function(){
    /**
     * 新增用户
     */
    $('.build_submit li').eq(0).click(function(){
        $('form').submit();
    })
});


/**
 * @author : zga
 * @date : 2016-3-18
 *
 * 页面载入时添加选中样式
 *
 */
window.onload = function(){
    $('#new_user').addClass('selected');
};