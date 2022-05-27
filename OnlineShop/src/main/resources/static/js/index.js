
$(function(){
    $(window).scroll(function(){
        if($(document).scrollTop()>$(window).height()){
            $('.scrolltotop').show();
        }else{
            $('.scrolltotop').hide();
        }
    });
    $('.scrolltotop').click(function(){
        $('html,body').animate({scrollTop: 0}, 1000);
    });
});

