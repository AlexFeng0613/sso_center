/**
 * @author : wuyue
 * @date : 2016/3/22
 *
 * 新增管理员
 *
 */

$(function(){

});

/**
 * @author : wuyue
 * @date : 2016/3/22
 *
 * 保存时自动跳转到管理员列表
 *
 */

$('.selected').click(function(){

    var userName =$('input[name="userName"]').val();
    var password =$('input[name="password"]').val();
    var roleId = $('select[name="roleId"]').val();
    console.log(userName);
    console.log(password);
    console.log(roleId);
    window.location.href ='/user/adminAddNewAdmin.html?userName=' + userName + '&password=' + password + '&roleId=' + roleId;
});

$('#new_admin').addClass('selected');

/**
 * @author : zga
 * @date : 2016-3-18
 *
 * 页面载入时添加选中样式
 *
 */
window.onload = function(){
    $('#new_admin').addClass('selected');
};
