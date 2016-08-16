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
	<div class="hall_body">
		<ul>
		<s:iterator value="combos" var="line" status="st">
		<li>
		<a class="item" pkey="${line.pkey }">
			<div class="headImage"><img class="headImage lazy" data-original="../${line.imgUrl }"></div>
			<div class="desc">
				<h4 class="title">${line.name }</h4>
				<div class="addr"><p>${line.gtRestaurant().gtCityline().name }</p></div>
				<p class="price">${line.price }<em>元/套</em></p>
			</div>
		</a>
		</li>
		</s:iterator>
		</ul>
		<div class="hall_zw"></div>
	</div>
	<div class="hall_bar">
		<a href="#" class="go">不选套餐，直接私人定制</a>	
	</div>
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
//微信获取地理位置接口成功回调函数
function jsDistance(res) {
	$.ajax({
		url:"resource/combo_list?account.pkey=${account.pkey}",
		type : "POST",
		data:{
			banquetId:${banquetId},
			pnum:${pnum},
			budget:${budget},
			areaId:${areaId},
			longitude:${longitude},
			latitude:${banquetId},
		},
		
	})
	var i = new Location(res.longitude, res.latitude);
	
	$(".hot i").each(function() {
		var lon = $(this).attr("longitude");
		var lat = $(this).attr("latitude");
		if(lon && lat) {
			var dest = i.getDistance(new Location(lon, lat));
			$(this).find(".dist_dd").html(dest.toFixed(2)+" km");
		}
	})
}
</script>
<script>
function checkJsApiSuccess() {
	//调用微信的获取当前地理位置接口
	wx.getLocation({
		type : 'wgs84',
		success : jsDistance
	});
}
</script>
<script>
var longitude,latitude;
$(function() {
	$("img.lazy").lazyload({
		placeholder : "images/emptyCombo.jpg",
		effect: "show",
		threshold:200
	});
	$(".item").click(function() {
		location.href = "showCombo?account.pkey=${account.pkey}&id="+$(this).attr("pkey");
	})
	$(".go").click(function() {
		location.href = "selectService?account.pkey=${account.pkey }&banquetId=${banquetId}&pnum=${pnum}&budget=${budget}&areaId=${areaId}";
	})
})
</script>
</html>