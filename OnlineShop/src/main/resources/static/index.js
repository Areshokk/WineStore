// $(function(){
//     $(window).scroll(function(){
//         if($(window).scrollTop() > 100) {
//             $('#scroll_top').show();
//         } else {
//             $('#scroll_top').hide();
//         }
//     });
//
//     $('#scroll_top').click(function(){
//         $('html, body').animate({scrollTop: 0}, 600);
//         return false;
//     });
// });



window.onscroll = function() {
    var scrollElem = document.getElementById("scrollToTop");
    if (document.body.scrollTop > document.documentElement.clientHeight) {
        scrollElem.style.opacity = "1";
    } else {
        scrollElem.style.opacity = "0";
    }
}

var timeOut;
function goUp() {
    var top = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
    if(top > 0) {
        window.scrollBy(0,-100);
        timeOut = setTimeout('goUp()',20);
    } else clearTimeout(timeOut);
}