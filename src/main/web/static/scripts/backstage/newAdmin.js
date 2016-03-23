/**
 * @author : wuyue
 * @date : 2016/3/22
 *
 * 新增管理员
 *
 */

$(function(){

});
///**
// * @author : wuyue
// * @date : 2016/3/22
// *
// * @return{string}
// *
// */
//function returnUrl(){
//    var userName =$('input[name="userName"]').val();
//    var organization = $('select[name="organization"]').val();
//    var type = $('select[name="type"]').val();
//    var status = $('select[name="status"]').val();
//    var createTime = $('select[name="createTime"]').val();
//    var realName = $('input[name="realName"]').val();
//    if(SSOSystem.isEmpty(userName)) userName = "0";
//     userName = encodeURI(userName);
//
//    var url = '/page/sso/adminList/' + userName + ',' + type + ',' + status + ',' + createTime + "," + realName + '.html';
//    return url;
//}

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