<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="text/javascript" src="js/flexible.js"></script>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/find_specialDetail.css"/>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>享食光</title>
</head>
<body>
	<a href="javascript:;" class="find_spe">
		<div class="txt">${special.title }</div>
		<div class="findspe_flog"></div>
		<img src="../${special.topImgUrl }"/>	
	</a>
	
	<div class="fddet_intro">
		<h2>${special.title}</h2>
		<p>${special.intro}</p>
	</div>
	
	<s:iterator value="special.lines" var="line" status="x">
		<a href="javascript:;" class="fddet_item" pkey="${line.restaurant.pkey}">
		<dl><dt>${line.restaurant.name}</dt> <dd>${line.restaurant.des}</dd></dl>
		<span class="xq">查看详情</span>
		<div class="fddet_flog"></div>
		<img data-original="../${line.restaurant.imgUrl}" class="lazy"/>
		</a>
	</s:iterator>
	<jsp:include page="../tip.jsp"/>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
$(function(){
	$("img.lazy").lazyload({
		placeholder : "images/emptySpecialDetail.jpg",
		effect: "show",
		threshold:200
		});
	$(".fddet_item").click(function(){
		var pkey = $(this).attr("pkey");
		location.href = "showRestaurant?id="+pkey+"&account.pkey=${account.pkey}";
	})
})
</script>
</html>