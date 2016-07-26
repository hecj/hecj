

$(function() {
	$(".nav>li").hover(function() {
		$(this).children('ul').stop(true, true).show(300);
	}, function() {
		$(this).children('ul').stop(true, true).hide(300);
	})
})   