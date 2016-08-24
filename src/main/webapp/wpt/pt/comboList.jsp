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
		</ul>
		<div class="hall_zw"></div>
	</div>
	<div class="hall_bar">
		<a href="#" class="go">不选套餐，直接私人定制</a>	
	</div>
	
</body>
<script type="text/javascript">
${jsCode}
</script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/location.js"></script>
<script>
//微信获取地理位置接口成功回调函数
function jsDistance(res) {
	findCombo(res.longitude, res.latitude)
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
function findCombo(longitude,latitude) {
	$.ajax({
		url:"resource/combo_search?account.pkey=${account.pkey}",
		type : "POST",
		data:{
			banquetId:"${banquetId}",
			pnum:"${pnum}",
			budget:"${budget}",
			areaId:"${areaId}",
			longitude:longitude,
			latitude:latitude,
		},
		dataType:"json",
		success:function(result) {
			var i = new Location(longitude, latitude);
			var items = "";
			$.each(result, function(index, record) {
				items += '<li>';
				items += '<a class="item" pkey="'+record.pkey+'">';
				items += '	<div class="headImage"><img class="headImage lazy" data-original="../'+record.imgUrl+'"></div>';
				items += '	<div class="desc">';
				items += '		<h4 class="title">'+record.name+'</h4>';
				items += '		<div class="addr"><p>'+record.restaurant.cityline.name;
				if(record.restaurant.longitude && record.restaurant.latitude) {
					var dest = i.getDistance(new Location(record.restaurant.longitude, record.restaurant.latitude));
					items += '		| '+dest.toFixed(2)+' km';
				}
				items += '</p></div>';
				items += '		<p class="price">'+record.price+'<em>元/套</em></p>';
				items += '	</div>';
				items += '	</a>';
				items += '</li>';
			})
			$("ul").html(items);
			$(".item").click(function() {
				location.href = "showCombo?account.pkey=${account.pkey}&id="+$(this).attr("pkey");
			})
			$("img.lazy").lazyload({
				placeholder : "images/emptyCombo.jpg",
				effect: "show",
				threshold:200
			});
 		}
	})
}
$(function() {
	$("img.lazy").lazyload({
		placeholder : "images/emptyCombo.jpg",
		effect: "show",
		threshold:200
	});
	$(".go").click(function() {
		location.href = "selectService?account.pkey=${account.pkey }&banquetId=${banquetId}&pnum=${pnum}&budget=${budget}&areaId=${areaId}";
	})
	findCombo(120.732107,27.973482);
})
</script>
</html>