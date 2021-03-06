/**
 * @author : zga
 * @date : 2016-3-18
 *
 * 注册第2步
 *
 */
$(function(){


    var conh=$('.container-fluid').height();
    var cenh=$('.container-fluid .center').height();
    var cenm=(conh-cenh)/2;
    $('.container-fluid .center').css('margin',cenm+'px auto 0');

    var sh=$('.section').height();
    var ash1=$('.aside1').height();
    var ash2=$('.aside2').height();

    $('.aside1').css('margin',(sh-ash1)/2+'px auto');
    $('.aside2').css('margin',(sh-ash2)/2+'px auto');

    $(window).on('resize scroll',function(){
        var conh=$('.container-fluid').height();
        var cenh=$('.container-fluid .center').height();
        var cenm=(conh-cenh)/2;
        $('.container-fluid .center').css('margin',cenm+'px auto 0');


    });

    var reg=/^1[3|4|5|7|8]\d{9}$/;
    var reg1=/^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i;
    var reg2=/^[a-zA-Z0-9]{2,20}$/;
    var reg3=/^[A-Z0-9]{6,10}$/;
    var reg4=/^[\w]{5}$/;
    var reg5=/^\d{4}$/;

    var iphoneNumValid, userNameValid, pwdValid, PwdConfirmValid, vcodeValid, smsValid;

    var demo = $(".regForm").Validform({
        tiptype:3,
        label:".label",
        showAllError:true,
        datatype:{
            "s6-18":/^[\w\.\s]{6,18}$/,
            "s2-10":/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,10}$/,
            "s2-20":/^[a-zA-Z0-9]{2,20}$/,
            "s5" : /^[\w]{5}$/,
            "s4" : /^\d{4}$/
            //"e" : const
        },

        callback : function(form){
            var username = $('input[name="username"]').val();
            var password = $('input[name="password"]').val();
            var email = $('input[name="email"]').val();
            var type = $('input[name="type"]').val();
            var code = $('input[name="code"]').val();
            var realName = $('input[name="realName"]').val();
            var gender = $('input[name="gender"]').val();

            var parmData = {
                'userName' : username,
                'password' : password,
                'email' : email,
                'type' : type,
                'code' : code,
                'realName' : realName,
                'gender' : gender
            }
            /**
             * 校验用户名与Email是否已经注册或存在
             */
            $.ajax({
                url : '/user/register/isExistsUserName.json',
                type : 'POST',
                data : JSON.stringify(parmData),
                contentType: 'application/json',
                dataType : 'json',
                success : function(data){
                    if(data.success){

                        $.ajax({
                            url : '/user/register/isBindEmail.json',
                            type : 'POST',
                            data : JSON.stringify(parmData),
                            contentType: 'application/json',
                            dataType : 'json',
                            success : function(data){
                                if(data.success){
                                    $.ajax({
                                        url : '/user/register/addNew.json',
                                        type : 'POST',
                                        data : JSON.stringify(parmData),
                                        contentType: 'application/json',
                                        dataType : 'json',
                                        success : function(data){
                                            if(data.success){
                                                if($('.iponeCode').is(":hidden")){
                                                    window.location.href = '/page/register/3.html?email=' + email;
                                                }else{
                                                    var data = {
                                                        'phone' : $('input[name="email"]').val(),
                                                        'smsInputCode' : $('.verify_code').val()
                                                    }
                                                    $.ajax({
                                                        url : '/sms/validateSmsCode.json',
                                                        type : 'POST',
                                                        data : JSON.stringify(data),
                                                        contentType: 'application/json',
                                                        dataType : 'json',
                                                        success : function(data){
                                                            if(data.success){
                                                                //成功跳转
                                                                window.location.href = '/page/register/5.html?email=' + $('input[name="email"]').val();
                                                            } else {
                                                                //否则提示错误
                                                                alert(ErrorMessage[data.message])
                                                            }
                                                        }
                                                    });
                                                }
                                            }else {
                                                SSOSystem.showAlertDialog(ErrorMessage[data.message])
                                            }
                                        }
                                    });
                                }else {
                                    SSOSystem.showAlertDialog(ErrorMessage[data.message]);
                                }
                            }
                        });
                    }else {
                        SSOSystem.showAlertDialog(ErrorMessage[data.message])
                    }
                }
            });
            return false;
        }
    });

    demo.tipmsg.w = {
        's6-18' : ' ', //用户名为6到18位
        's2-20' : ' ',//姓名为2到10位中文字符
        '*6-10' : ' ',//密码为6到10位
        's5' : ' ',//验证码为5位
        'e' : ' ',//邮箱格式不正确
        'm':' ',
        "s4":' ',
        "s2-10" : ' '
    };

    demo.addRule([{
        ele:".inputxt:eq(0)",
        datatype:"m|e"
    },{
        ele:".inputxt:eq(1)",
        datatype:"s2-20"
    },{
        ele:".inputxt:eq(2)",
        datatype:"*6-10"
    },{
        ele:".inputxt:eq(3)",
        datatype:"*",
        recheck:"password"
    },{
        ele:".inputxt:eq(4)",
        datatype:"s5",
    },{
        ele:".inputxt:eq(5)",
        datatype:"s4"
    },{
        ele:"inputxt:eq(6)",
        datatype:"s2-10"
    }
    ]);

    function agreeAndRegisValid(){
        return iphoneNumValid && userNameValid && pwdValid && PwdConfirmValid && vcodeValid && ($('.iponeCode').is(":hidden") || smsValid);
    }

    function next(){
        if(agreeAndRegisValid()) {
            $('.submit').removeClass('disabled');
            $('.submit').prop('disabled', false);
        } else {
            $('.submit').addClass('disabled');
            $('.submit').prop('disabled', false);
        }
    }

    $('.iponeNum').on('change',function(){
        var iponeVal=$(this).val();

        if((reg.test(iponeVal))) {
            $('.input_box.iponeCode').show();
            iphoneNumValid = true;
            demo.unignore('.inputxt.verify_code');
        }else if(reg1.test(iponeVal)  ){
            iphoneNumValid = true;
            $('.input_box.iponeCode').hide();
            demo.ignore('.inputxt.verify_code');
        }else{
            iphoneNumValid = false;
            $('.input_box.iponeCode').hide();
        }
        next();
    });

    $('.username').on('change',function(){
        if(reg2.test($(this).val())){
            userNameValid = true;
        } else {
            userNameValid = false;
        }
        next();
    })

    $('.password').on('change',function(){

        if(reg3.test($(this).val())){
            pwdValid = true;
        } else {
            pwdValid = false;
        }
        next();
    });

    $('.password2').on('change',function(){

        PwdConfirmValid = ($(this).val() === $('.password').val());
        next();
    })

    $('.vcodeValid').on('change',function(){
        if(reg4.test($(this).val())){
            vcodeValid = true;
        } else {
            vcodeValid = false;
        }
        next();
    });

    $('.verify_code').on('change',function(){
        if(reg5.test($(this).val())){
            smsValid = true;
        } else {
            smsValid = false;
        }
        next();
    });

    $('.sex li').click(function(){
        index=$(this).index();
        $(this).addClass('selected').siblings('.sex li').removeClass('selected');
        $(this).find('input').attr('checked',true).end().siblings('.sex li').find('input').attr('checked',false);

    });

    /**
     * @author : zga
     * @date : 2016-3-18
     *
     * 验证码
     *
     *
     */
    $('#vimg').click(function(){
        var timestamp = (new Date()).valueOf();
        $(this).attr('src',"/code.html?timestamp=" + timestamp);
    });

    /**
     * 短信获取click函数
     */
    $('#note').click(function(){
        $('input[name="email"]').blur();
        var phone = $('input[name="email"]').val();
        alert(phone);
        if(phone == null || phone == '' || !Constant.phonePattern.test(phone)){

            return false;
        }

        SSOSystem.time($(this),5,"获取验证码");
        var data = {
            'phone' : phone
        }

        /**
         * 发送短信
         */
        $.ajax({
            url : '/sms/sendSmsCode.json',
            type : 'POST',
            data : JSON.stringify(data),
            contentType: 'application/json',
            dataType : 'json',
            success : function(data){
                if(data.success){
                } else {
                }
            }
        });
    });
});