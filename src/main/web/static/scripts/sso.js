/**
 * Created by Administrator on 2015/12/30.
 */
$(function(){
    var ch= $(window).height()-($('.header').height())-($('.footer').outerHeight());
    $('.container').css('min-height',ch+'px');
    $('.datum_r').css('min-height',ch-20+'px');

    var cr=$('.datum_r').outerHeight();
    $('.datum_l').css('min-height',cr+'px');

    $(window).on('resize scroll',function(){
        var ch= $(window).height()-($('.header').height())-($('.footer').outerHeight());
        $('.container').css('min-height',ch+'px');
        $('.datum_r').css('min-height',ch-20+'px');

        var cr=$('.datum_r').outerHeight();
        $('.datum_l').css('min-height',cr+'px');
    });

    $('.register_input').focus(function(){
        $(this).addClass('fillout');
    });

    $('.register_input').blur(function(){
        $(this).removeClass('fillout');
    });

    $('.remember li').click(function(){
        $(this).toggleClass('checked');
    });

    $('.sex li').click(function(){
        $(this).addClass('selected').siblings('.sex li').removeClass('selected');
    });


});
