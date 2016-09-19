<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="text/javascript" src="js/flexible.js"></script>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/cssm.css" />
<link type="text/css" rel="stylesheet" href="css/me_orderList.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>我的私人订制</title>
</head>

<body>
	<div class="main">
		<div class="nav">
			<ul>
				<li class="select">我的订单（${orderNum }）</li>
				<li class="">私人定制申请（${customFormNum }）</li>
			</ul>
			<div class="clean"></div>
		</div>
	</div>
	<div class="content">
		<div class="list">
			<s:iterator value="orders" var="order">
			<a href="#" class="usorder_item" orderid="${order.orderid }">
				<img src="../${order.restaurant.imgUrl }" class="photo" />
				<div class="txt">
					<div class="name">
						${order.restaurantName }
					</div>
					<div class="chuli">
					<s:if test="#order.status==0">未受理</s:if>
					<s:elseif test="#order.status==1">已受理</s:elseif>
					<s:elseif test="#order.status==2">未付款</s:elseif>
					<s:elseif test="#order.status==3">已付定金</s:elseif>
					<s:elseif test="#order.status==4">已付款</s:elseif>
					<s:elseif test="#order.status==5">已完成</s:elseif>
					<s:elseif test="#order.status==6">已关闭</s:elseif>
					<s:elseif test="#order.status==7">申请取消订单</s:elseif>
					<s:elseif test="#order.status==8">申请退款</s:elseif>
					</div>
					<ul>
						<li>${order.time }</li>
						<li>¥ <s:if test="#order.price">${order.price.intValue() }</s:if>
							<s:else>___</s:else>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
			</a>
			</s:iterator>
		</div>
		<div class="list">
		<s:iterator value="customForms" var="form">
			<a href="#" class="usform_item" formid="${form.formid }">
				<img src="images/customForm.jpg" class="photo" />
				<div class="txt">
					<div class="name">
						${form.banquet }
					</div>
					<ul>
						<li><span class="lt">预算</span>&nbsp;<span class="rt">${form.budget }</span></li>
						<li><span class="lt">人数</span>&nbsp;<span class="rt">${form.number }</span></li>
						<li><span class="lt">用餐时间</span>&nbsp;<span class="rt">
						<s:date name="#form.time" format="yyyy-MM-dd HH:mm"/>
						</span></li>
					</ul>
				</div>
				<div class="clear"></div>
			</a>
			</s:iterator>
		</div>
	</div>
	<div style="width:10rem;height:1.3rem"></div>
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
		location.href = "showOrder?account.pkey=${account.pkey}&orderid="+$(this).attr("orderid")
	})
	$(".usform_item").click(function() {
		location.href = "showCustomForm?account.pkey=${account.pkey}&formid="+$(this).attr("formid")
	})
	$(".nav li").click(function() {
		$(this).addClass("select").siblings().removeClass("select");
		var show = $("div.list:eq("+$(this).index()+")");
		show.show();
		var hide = show.siblings();
		hide.hide();
	})
	$(".nav li:eq(0)").click();
})
</script>
</html>