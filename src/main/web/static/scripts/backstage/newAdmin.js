/**
 * @author : wuyue
 * @date : 2016/3/22
 *
 * 新增管理员
 *
 */
$(function(){
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
        if(SSOSystem.isEmpty(userName)||SSOSystem.isEmpty(password)){
            SSOSystem.showAlertDialog("请输入完整信息！");
            return false;
        }
        window.location.href ='/user/adminAddNewAdmin.html?userName=' + userName + '&password=' + password + '&roleId=' + roleId;
    });

    /**
     * @author : wuyue
     * @date : 2016/3/24
     *
     * 取消时自动跳转到管理员列表
     *
     */
    $('.cancel').click(function(){
        window.location.href ='/page/sso/adminList/1,10,0.html';
    });



});

/**
 * @author : wuyue
 * @date : 2016-3-24
 *
 * 页面载入时添加选中样式
 *
 */
window.onload = function(){
    $('#new_admin').addClass('selected');
};
