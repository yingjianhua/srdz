<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="footer">
	<a href="#"><i class="ft_ico ft_ico1"></i><span>发现</span></a>
	<a href="#"><i class="ft_ico ft_ico2"></i><span>订制</span></a>
	<a href="#"><i class="ft_ico ft_ico3"></i><span>头条</span></a>
	<a href="#"><i class="ft_ico ft_ico4"></i><span>我的</span></a>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
$(function() {
	var hrefs = [
	             "listSpecial?account.pkey=${account.pkey}",
	             "startJourney?account.pkey=${account.pkey}",
	             "listHeadline?account.pkey=${account.pkey}",
	             "showMe?account.pkey=${account.pkey}",
	             ];
	$(".footer a").each(function(index) {
		if((index+1) == "${param.currMenu}") 
			$(this).addClass("hover").siblings().removeClass("hover");
		$(this).click(function() {
			location.href = hrefs[index];
		})
	})
})
</script>