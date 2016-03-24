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
    //$('.build_submit li').eq(0).click(function(){
    $('.selected').click(function(){
        var userName =$('input[name="userName"]').val();
        var realName =$('input[name="realName"]').val();
        var password =$('input[name="password"]').val();
        if((SSOSystem.isEmpty(userName)&&SSOSystem.isEmpty(password))
            ||(SSOSystem.isEmpty(userName)&&SSOSystem.isEmpty(realName))
            ||(SSOSystem.isEmpty(realName)&&SSOSystem.isEmpty(password))
            ||(SSOSystem.isEmpty(userName)&&SSOSystem.isEmpty(realName)&&SSOSystem.isEmpty(password))
        ){
            SSOSystem.showAlertDialog("请将重要信息填写完整！");
            return false;
        }
        else if(SSOSystem.isEmpty(userName)){
            SSOSystem.showAlertDialog("用户名不能为空！");
            return false;
        }
        else if(SSOSystem.isEmpty(realName)){
            SSOSystem.showAlertDialog("真实姓名不能为空！");
            return false;
        }
        else if(SSOSystem.isEmpty(password)){
            SSOSystem.showAlertDialog("密码不能为空！");
            return false;
        }

        /**
         * 判断用户名是否存在（利用注册用户时判断的controller来判断）
         * @type {{userName: (*|jQuery)}}
         */
        var paramData = {
            'userName' : userName
        };
        $.ajax({
            url : '/user/register/isExistsUserName.json',
            type : 'POST',
            data : JSON.stringify(paramData),
            contentType: 'application/json',
            dataType : 'json',
            success : function(data){
                if(data.success){
                    $('form').submit();
                } else {
                    SSOSystem.showAlertDialog("用户名已存在！");
                }
            }
        });
    });

    $('.cancel').click(function(){
        window.location.href='/page/sso/userList/1,10,0,0,0,0,0.html';
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
    $('#new_user').addClass('selected');
};