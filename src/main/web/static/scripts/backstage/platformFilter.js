/**
 * @author : zga
 * @date : 2016-1-28
 *
 * 平台过滤列表
 *
 */

$(function(){
    /**
     * 全选与单选事件
     */
    $('.checkAll').click(function(){
        $(".filterList").prop("checked",$(this).prop("checked"));
    });
    $(".filterList").click(function(){
        $('.checkAll').prop('checked',($(".filterList").length == $(".filterList:checked").length));
    })

    /**
     * 删除事件
     */
    $('.del_clientFilter').click(function(){
        if(window.confirm('确定要删除吗?')){
            var filterId = $(this).parent().siblings().eq(0).find('input[name="filterId"]').val();
            var paramData = {
                'filterId' : filterId
            };
            $.ajax({
                url : '/thirdClientFilter/deleteThirdClientFilter.json',
                type : 'POST',
                data : JSON.stringify(paramData),
                contentType: 'application/json',
                dataType : 'json',
                success : function(data){
                    if(data.success){
                        window.location.reload();
                    }else {
                        SSOSystem.showAlertDialog(ErrorMessage[data.message],'','111');
                    }
                }
            });
        }
        return false;
    })

    /**
     * 新增弹出窗
     */
    $('.handle li').eq(0).click(function(){
        $('.addBox').show();

    });

    $('.cancel').click(function(){
        $('.addBox').hide();
        $('.section').css('background-color','#ffffff');
    });

    /**
     * 新增事件
     */
    $('.add_new').click(function(){
        var clientId = $('select[name="clientId"]').val();
        var organizationCode = $('select[name="organizationCode"]').val();
        var tstudent = $('select[name="tstudent"]').val();

        if(SSOSystem.isEmpty(clientId || SSOSystem.isEmpty(organizationCode)
                || SSOSystem.isEmpty(tstudent))){
            return false;
        }

        var data = {
            'clientId' : clientId,
            'organizationCode' : organizationCode,
            'tstudent' : tstudent
        };
        $.ajax({
            url : '/thirdClientFilter/addNewThirdClientFilter.json',
            type : 'POST',
            data : JSON.stringify(data),
            contentType: 'application/json',
            dataType : 'json',
            success : function(data){
                if(data.success){
                    window.location.reload();
                }else {
                    SSOSystem.showAlertDialog(ErrorMessage[data.message],'','111');
                }
            }
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
    $('#platformfilter_list').addClass('selected');
};