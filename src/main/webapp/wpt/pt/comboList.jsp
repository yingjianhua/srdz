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
<link type="text/css" rel="stylesheet" href="css/pt_comboList.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>享食光</title>
</head>
<body>	
	<div class="hall_top">超值私人定制套餐任选！选择套餐更划算哦！</div>
	<ul>
	<li>
	<a class="item" pkey="1">
		<div class="headImage"><img class="headImage" src="/uploads/20160812/2.jpg"></div>
		<div class="desc">
			<h4 class="title">沉浸于北美乡村分为酒馆细品加拿大家庭11313222212式</h4>
			<div class="addr"><p>大南门</p></div>
			<p class="price">45<em>元/套</em></p>
		</div>
	</a>
	</li>
	<li>
	<a class="item" pkey="2">
		<div class="headImage"><img class="headImage" src="/uploads/20160812/2.jpg"></div>
		<div class="desc">
			<h4 class="title">沉浸于北美乡村分为酒馆细品加拿大家庭11313222212式</h4>
			<div class="addr"><p>大南门</p></div>
			<p class="price">45<em>元/套</em></p>
		</div>
	</a>
	</li>
	</ul>
	<div class="hall_bar">
		<a href="#" class="go">不选套餐，直接私人定制</a>	
	</div>
	<div class="hall_zw"></div>
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
$(function() {
	$(".item").click(function() {
		location.href = "showCombo?account.pkey=${account.pkey}&id="+$(this).attr("pkey");
	})
	$(".go").click(function() {
		location.href = "selectService?account.pkey=${account.pkey }&banquetId=${banquetId}&pnum=${pnum}&perCapitaBudget=${perCapitaBudget}&areaId=${areaId}";
	})
})
</script>
</html>