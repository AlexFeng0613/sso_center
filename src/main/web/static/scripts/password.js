/**
 * Created by Administrator on 2015/12/30.
 */
$(function(){
    var ch=$(window).height()-$('.header').outerHeight()-$('.footer').outerHeight();

    $('.container').css('min-height',ch+'px');

    $('window').on('resize','scroll',function(){
        var ch=$(window).height()-$('.header').outerHeight()-$('.footer').outerHeight();

        $('.container').css('min-height',ch+'px');
    })

});
