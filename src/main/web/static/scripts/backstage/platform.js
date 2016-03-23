/**
 * @author : zga
 * @date : 2016-1-28
 *
 * 平台列表
 *
 */

$(function(){
    /**
     * 全选与单选事件
     */
    $('.checkAll').click(function(){
        $(".clientList").prop("checked",$(this).prop("checked"));
    });
    $(".clientList").click(function(){
        $('.checkAll').prop('checked',($(".clientList").length == $(".clientList:checked").length));
    })

    /**
     * 修改基本信息
     */
    $('.modify_info').click(function(){
        $('.section').css('background-color','#e5e5e5');
        $('.modifiedBox').show();

        var platform_name = $(this).parent().siblings().eq(1).html();
        var contactorName = $(this).parent().siblings().eq(2).html();
        var contactorPhone = $(this).parent().siblings().eq(3).html();
        var platform_publicKey = $(this).parent().siblings().eq(4).html();
        var platform_clientId = $(this).parent().siblings().eq(5).html();
        var platform_clientSecret = $(this).parent().siblings().eq(6).html();
        var platform_ssoPassword = $(this).parent().siblings().eq(7).html();
        var platform_callbackUrl = $(this).parent().siblings().eq(8).html();

        $('.platform_name').html(platform_name);
        $('input[name="contactorName"]').val(contactorName);
        $('input[name="contactorPhone"]').val(contactorPhone);
        $('.platform_publicKey').html(platform_publicKey);
        $('.platform_clientId').html(platform_clientId);
        $('.platform_clientSecret').html(platform_clientSecret);
        $('.platform_ssoPassword').html(platform_ssoPassword);
        $('input[name="callbackUrl"]').val(platform_callbackUrl);
    });

    /**
     * 删除记录
     */
    $('.del_record').click(function(){
        var third_name = $(this).parent().siblings().eq(1).html();
        if(window.confirm('确定删除 《' + third_name + '》 吗?')){
            var data = {
                'clientId' : $(this).parent().siblings().eq(5).html()
            };
            $.ajax({
                url : '/thirdClients/deleteThirdClient.json',
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
        }
        return false;
    });

    /**
     * 弹出窗取消事件
     */
    $('.butR').click(function(){
        $('.modifiedBox').hide();
        $('.section').css('background-color','#ffffff');
    });

    $('.cancel').click(function(){
        $('.addBox').hide();
        $('.section').css('background-color','#ffffff');
    });

    /**
     * 弹出窗保存事件
     */
    $('.butL').click(function(){
        var data = {
            'clientId' : $('.platform_clientId').html(),
            'contactorName' : $('form[name="modifyForm"] input[name="contactorName"]').val(),
            'contactorPhone' : $('form[name="modifyForm"] input[name="contactorPhone"]').val(),
            'callbackUrl' : $('form[name="modifyForm"] input[name="callbackUrl"]').val()
        };

        $.ajax({
            url : '/thirdClients/updateThirdClientInfo.json',
            type : 'POST',
            data : JSON.stringify(data),
            contentType: 'application/json',
            dataType : 'json',
            success : function(data){
                if(data.success){
                    $('.modifiedBox').hide();
                    $('.section').css('background-color','#ffffff');
                    window.location.reload();
                }else {
                    SSOSystem.showAlertDialog(ErrorMessage[data.message],'','111');
                }
            }
        });
    });


    /**
     * 新增弹出窗
     */
    $('.handle li').eq(0).click(function(){
        $('.addBox').show();
        $('.section').css('background-color','#e5e5e5');
    });

    /**
     * 新增平台
     */
    $('.add_new').click(function(){
        var description = $('input[name="description"]').val();
        var briefName = $('input[name="briefName"]').val();
        var contactorName = $('form[name="addForm"] input[name="contactorName"]').val();
        var contactorPhone = $('form[name="addForm"] input[name="contactorPhone"]').val();
        var callbackUrl = $('input[name="callbackUrl"]').val();

        if(SSOSystem.isEmpty(description) || SSOSystem.isEmpty(briefName)
            ||SSOSystem.isEmpty(contactorName) || SSOSystem.isEmpty(contactorPhone)
            || SSOSystem.isEmpty(callbackUrl)){

            console.log('description:' + SSOSystem.isEmpty(description));
            console.log('briefName:' + SSOSystem.isEmpty(briefName));
            console.log('contactorName:' + SSOSystem.isEmpty(contactorName));
            console.log('contactorPhone:' + SSOSystem.isEmpty(contactorPhone));
            console.log('callbackUrl:' + SSOSystem.isEmpty(callbackUrl));
            return false;
        }

        var data = {
            'description' : description,
            'briefName' : briefName,
            'contactorName' : contactorName,
            'contactorPhone' : contactorPhone,
            'callbackUrl' : callbackUrl
        };

        $.ajax({
            url : '/thirdClients/addNewThirdClient.json',
            type : 'POST',
            data : JSON.stringify(data),
            contentType: 'application/json',
            dataType : 'json',
            success : function(data){
                if(data.success){
                    $('.modifiedBox').hide();
                    $('.section').css('background-color','#ffffff');
                    window.location.reload();
                }else {
                    SSOSystem.showAlertDialog(ErrorMessage[data.message],'','111');
                }
            }
        });
    });
})

/**
 * @author : zga
 * @date : 2016-3-18
 *
 * 页面载入时添加选中样式
 *
 */
window.onload = function(){
    $('#platform_list').addClass('selected');
};