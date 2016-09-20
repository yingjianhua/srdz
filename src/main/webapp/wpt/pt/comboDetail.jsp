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
<title>享食光</title>
</head>

<body>
	<div id="banner">
		<div class="hd" style="display:none">
	       	<ul></ul>
	   	</div>
		<div class="bd">
	     	<ul>
		     	<s:iterator value="combo.banners" var="b">
		     	<li>
		     	<img class="cbdet_photo" src="../${b.imgUrl}">
		     	</li>
		     	</s:iterator>
	     	</ul>
	   	</div>
   	</div>
	<h2 class="cbdet_head">${combo.name }</h2>
	
	<div class="cbdet_item">
		<s:iterator value="combo.comboLines" var="comboLine">
			<p>
				<s:if test="#comboLine.menu.name == 'space'"><br></s:if>
				<s:else><s:property value="#comboLine.menu.name" /></s:else>
				<s:if test="#comboLine.price.intValue()!=0">
				<span><s:property value="#comboLine.price.intValue()"/>元/份</span>
				</s:if>
			</p>
		</s:iterator>
	</div>
	<div class="cbdet_item cbdet_xz">
		<h3>-须知-</h3>	
		<p>适用人数：${combo.numberMin }~${combo.numberMax }人</p>
		<p>使用前请提前预约</p>
		<p>使用日期：${combo.serviceDate }</p>
		<p>使用时段：${combo.serviceTime }</p>
		<p>${combo.rem }</p>
	</div>
	
	<div class="cbdet_bar">
		<span class="price">${combo.price.intValue() }元</span>/套
		<s:if test="origPrice.intValue()!=combo.price.intValue()"><em class="old_price">${origPrice.intValue() }元</em></s:if>
		<a href="javascript:;" class="yd">预 定</a>
	</div>
	<div class="cbdet_zw"></div>
		
</body>
<script src="../wp/fif/assets/js/libs/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
//轮播图
TouchSlide({ 
	slideCell:"#banner",
	titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
	mainCell:".bd ul", 
	effect:"leftLoop", 
	autoPlay:true,//自动播放
	autoPage:true, //自动分页
});
</script>
<script>
$(function() {
	$(".yd").click(function() {
		var url = "selectService?comboId=${id}&account.pkey=${account.pkey}";
		location.href = url;
	})
})
</script>
</html>