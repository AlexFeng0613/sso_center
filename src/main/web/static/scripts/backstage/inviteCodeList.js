/**
 * @author : zga
 * @date : 2016/3/14
 *
 * 邀请码管理列表
 *
 */
$(function(){
    $('#invitation_manage').addClass('selected');

    if($('input[name="inviteCode"]').val() == '0'){
        $('input[name="inviteCode"]').val('');
    }

    /**
     * @author : zga
     * @date : 2016-3-14
     *
     * 查询条件的改变事件
     *
     */
    $('.queryCondition').change(function(){
        window.location.href = returnInviteCodeManageURL();
    });

    /**
     * @author : zga
     * @date : 2016-3-14
     *
     * 点击查询事件
     *
     */
    $('.tab_fnLi').click(function(){
        window.location.href = returnInviteCodeManageURL();
    });
});

/**
 * @author : zga
 * @date : 2016-3-14
 *
 * 返回邀请码管理列表
 *
 * @returns {string}
 */
function returnInviteCodeManageURL(){
    var queryOrganization = $('select[name="organization"]').val();
    var status = $('select[name="status"]').val();
    var createTime = $('select[name="createTime"]').val();
    var queryInviteCode = $('input[name="inviteCode"]').val();
    if(SSOSystem.isEmpty(queryInviteCode)) queryInviteCode = '0';

    var url = '/page/sso/invitationManage/1,10,' + queryOrganization + ',' + status + ','  + createTime + ',' + queryInviteCode + '.html';
    return url;
}

