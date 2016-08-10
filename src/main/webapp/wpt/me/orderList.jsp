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
	<s:iterator value="orders" var="order">
	<a href="#" class="usorder_item" pkey="${order.pkey }">
		<img src="../${order.gtRestaurant().imgUrl }" class="photo" />
		<div class="txt">
			<div class="name">
				<s:if test="#order.gtRestaurant()">
					${order.gtRestaurant().name }
				</s:if>
				<s:else>
					等待人工处理
				</s:else>
			</div>
			<div class="chuli">${order.gtStatus().line.name }</div>
			<ul>
				<li>${order.formatDate }</li>
				<li>¥ <s:if test="#order.price">${order.price.intValue() }</s:if>
					<s:else>___</s:else>
				</li>
			</ul>
		</div>
		<div class="clear"></div>
	</a>
	</s:iterator>
	<div style="width:640px;height:83px"></div>
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="4" name="currMenu" />
	</jsp:include>
	<!--footer 底部导航-->
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
$(function() {
	$(".usorder_item").click(function() {
		location.href = "showOrder?account.pkey=${account.pkey}&id="+$(this).attr("pkey")
	})
})
</script>
</html>