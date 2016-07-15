<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<link type="text/css" rel="stylesheet" href="css/css.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<!--这段JS不能删除，且前面最好别再放其它JS-->
<script type="text/javascript">
    var phoneWidth =  parseInt(window.screen.width);
    var phoneScale = phoneWidth/640;
    var ua = navigator.userAgent;
    if (/Android (\d+\.\d+)/.test(ua)){
        var version = parseFloat(RegExp.$1);
        if(version>2.3){
            document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
        }else{
            document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
        }
    } else {
        document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
    }
</script>
<title>享食光-私人定制</title>
</head>

<body>	
	<!--footer 底部导航-->
		<jsp:include page="../menu.jsp">
			<jsp:param value="2" name="currMenu"/>
		</jsp:include>
	<!--footer 底部导航-->
	<div class="find_top">
		<span><a href="listSpecial?account.pkey=${account.pkey }" id="special">专题</a></span>
		<span><a href="javascript:;" class="hover" id="hot">热销</a></span>	
	</div>
	<!-- 热销 -->
	<div class="hot">
		<s:iterator value="hots" var="line" status="st">
			<div class="hall_item">
					<a href="javascript:;" class="hall_item1" pkey="${line.restaurant}" >
					<img style="height:358px" data-original="../${line.gtRestaurant().imgUrl}" class="photo lazy" />
					<div class="item_flog"></div>
					</a> 
					<i class="addr" latitude="${line.gtRestaurant().coordinate}" longitude="${line.gtRestaurant().longitude}" name="${line.gtRestaurant().name}" address="${line.gtRestaurant().addr}">
						<dl style="float:left">
							<dt>${line.gtRestaurant().name}</dt>
							<dd>${line.gtRestaurant().addr}</dd>
						</dl>
						<dl class="dist_dl">
							<dd class="dist_dd"></dd>
						</dl>
					</i>
			</div>
		</s:iterator>
	</div>
<div style="width:640px;height:83px"></div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
function Location(longitude, latitude) {
	this.longitude = longitude;
	this.latitude = latitude;
}
Location.prototype.R = 6371;
Location.prototype.rad = function(len) {
	return len*Math.PI/180;
}
Location.prototype.getDistance = function(dest) {
	if(dest instanceof Location) {
		var radLat1 = this.rad(this.latitude);  
	    var radLat2 = this.rad(dest.latitude);  
	    var a = radLat1 - radLat2;  
	    var b = this.rad(this.longitude) - this.rad(dest.longitude);  
	    var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
	    s = s * this.R;  
	    return s;  
	}
}
//微信获取地理位置接口成功回调函数
function jsDistance(res) {
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
$(function(){
	$("img.lazy").lazyload({
		placeholder : "images/emptyHot.jpg",
		effect: "show",
		threshold:200
		});
	$(".hall_item1").click(function(){
		var pkey = $(this).attr("pkey");
		window.location.href = "showRestaurant?id="+pkey+"&account.pkey=${account.pkey}";
	})
})
</script>
</html>