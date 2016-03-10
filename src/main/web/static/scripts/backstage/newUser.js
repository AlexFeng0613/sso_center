/**
 * @author : zga
 * @date : 2016-3-10
 *
 * 新增用户
 *
 */
$(function(){
    $('#new_user').addClass('selected');

    /**
     * 新增用户
     */
    $('.build_submit li').eq(0).click(function(){
        $('form').submit();
    })
});