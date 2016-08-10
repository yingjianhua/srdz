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
	
	<div class="resok_top">
		<dl>
			<dt><img src="images/hm_logo.png" /></dt>
			<dd>期待您的光临</dd>	
		</dl>
		<div class="resok_flog"></div>
		<img src="images/reserve_bg.jpg" class="bg" />	
	</div>
	
	<div class="resok_info">
		<h2>享食光正在处理您的订单</h2>	
		<ul>
			<li><span class="name">${order.contactMan }</span><i class="sex_ico <s:if test="order.contactSex==1">nav</s:if><s:else>nv</s:else>"></i></li><!--class="sex_ico nav"就是男图标-->
			<li><span class="<s:if test="order.contactType==0">tel_ico</s:if><s:elseif test="order.contactType==1">wx_ico</s:elseif><s:elseif test="contactType==2">qq_ico</s:elseif>"></span><a href="tel:${order.contactWay }">${order.contactWay }</a></li>
			<s:if test="banquet.name!=null"><li>${banquet.name} </li></s:if>
			<li>${order.formatDate }</li>
			<s:if test="order.num"><li>${order.number }位</li></s:if>
			<s:if test="order.consumption"><li>${order.consumption }元/位</li></s:if>
			<s:if test="orderServices.size()!=0"><li><s:iterator value="orderServices" var="line">${line.name } &nbsp;</s:iterator></li></s:if>
			<s:if test="order.gtRestaurant().name!=null"><li>${order.gtRestaurant().name } </li></s:if>
			<s:if test="order.comboName!=null"><li>${order.comboName} </li><li>${order.price }元/套</li></s:if>
			<s:if test="order.rem!=null"><li>${order.rem} </li></s:if>
		</ul>
	</div>
	
	<a href="#" class="cm_btn1">确认</a>
	<div style="height:35px;"></div>
	
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
$(function(){
	$(".cm_btn1").click(function(){
		window.location.href = "listOrder?account.pkey=${account.pkey}"
	})
})
</script>
</html>