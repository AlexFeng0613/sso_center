	$(function(){
	var timer=5;
	$('.anew60').html(timer+'秒后可重新获取');
	
	var h=$(window).height()-$('.header').height()-$('.footer').height();
	$('.container-fluid').css('min-height',h+'px');
	$('.count').html(timer+'秒后可重新发送');
	
	
	$(window).on('resize scroll',function(){
		var h=$(window).height()-$('.header').height()-$('.footer').height();
		$('.container-fluid').css('min-height',h+'px');
		
	})
	
	$(document).on('click','.anew',function(){
		$(this).hide().siblings('.anew60').show();
		clearInterval(timee);
		var timee=setInterval(function(){
			
			if(timer>1){
				timer--;
				$('.anew60').html(timer+'秒后可重新发送');
				
			}else{
				clearInterval(timee);
				timer=5;
				$('.anew60').html(timer+'秒后可重新发送');
				$('.anew60').hide();
				$('.anew').show();
				
			}
		},1000);
	});
	
	function  time(){			
		if(timer>1){
			timer--;
			$('.count').html(timer+'秒后可重新获取');
				
		}else{
			$('.count').hide();
			$('.note').removeClass('click');			
			timer=5;	
			$('.count').html(timer+'秒后可重新获取');
			
		}			
	}
	var tis ;
	
	$(document).on('click','.note',function(){
		$(this).addClass('click');
		$('.count').show();
		
		clearInterval(tis);	
		
		tis = setInterval(time,1000)
	});
	
	$(document).on('click','.self_motion',function(){
		$(this).toggleClass('check');
		
	});
	
	
	
	
});


