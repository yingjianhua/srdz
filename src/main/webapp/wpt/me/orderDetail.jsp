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
	
	<div class="uscombo_head">
		<strong>订单编号：${order.orderid }</strong>
		<span class="type">
			<s:if test="order.status==0">未受理</s:if>
			<s:elseif test="order.status==1">已受理</s:elseif>
			<s:elseif test="order.status==2">未付款</s:elseif>
			<s:elseif test="order.status==3">已付定金</s:elseif>
			<s:elseif test="order.status==4">已付款</s:elseif>
			<s:elseif test="order.status==5">已完成</s:elseif>
			<s:elseif test="order.status==6">已关闭</s:elseif>
			<s:elseif test="order.status==7">申请取消订单</s:elseif>
			<s:elseif test="order.status==8">申请退款</s:elseif>
		</span>	
	</div>
	
	<ul class="uscombo_info">
		<li>${order.restaurantName }</li>	
		<li>${order.comboName }</li>
		<s:if test="order.services.size()!=0">
			<li>
			<s:iterator value="order.services" var="line">
				${line.name }&nbsp;
			</s:iterator>
			</li>
		</s:if>
		<li><a href="tel:${order.restaurant.mobile }" class="tel">${order.restaurant.mobile }</a></li>
		<i class="addr" latitude="${order.restaurant.latitude}" longitude="${order.restaurant.longitude}" name="${order.restaurant.name}" address="${order.restaurant.addr}"><li><span class="pos">${order.restaurant.addr }</span></li></i>
		<s:if test="order.checkcode != null"><li>核验码　${order.checkcode }</li></s:if>
	</ul>	
	
	<div class="uscombo_food">
		
		<div class="cbdet_title">
			<span>菜品内容</span>	
		</div>
		
		<div class="cbdet_item">
			<s:iterator value="order.details" var="line">
			<p>${line.name }
			<s:if test="#line.price.intValue()!=0">
			<span>${line.price.intValue() }元/份</span>
			</s:if>
			</p>
			</s:iterator>
		</div>
			
	</div>
	
	<s:if test="order.status==0">
		<a href="javascript:;" class="cm_btn1">正在为您处理</a>
	</s:if>
	<s:elseif test="order.status==2">
		<div class="uscombo_ctrl">
			<a href="javascript:;" class="pay_btn lt payOrder">
				<s:if test="order.deposit.intValue()!=0">支付定金</s:if>
				<s:else>立即付款</s:else>
			</a>
			<a href="javascript:;" class="esc_btn rt cancelOrder">取消订单</a>
		</div>
	</s:elseif>
	<s:elseif test="order.status==3">
		<div class="uscombo_ctrl">
			<a href="javascript:;" class="pay_btn lt payOrder">支付余款</a>
			<a href="javascript:;" class="esc_btn rt cancelOrder">取消订单</a>
		</div>
	</s:elseif>
	<s:elseif test="order.status==4">
		<div class="uscombo_ctrl">
			<a href="javascript:;" class="pay_btn lt payOrder">支付完成</a>
			<a href="javascript:;" class="esc_btn rt cancelOrder">取消订单</a>
		</div>
	</s:elseif>
	<s:elseif test="order.status==5"><a href="javascript:;" class="cm_btn1">订单已完成</a></s:elseif>
	<s:elseif test="order.status==6"><a href="javascript:;" class="cm_btn1">订单已关闭</a></s:elseif>
	<s:elseif test="order.status==7"><a href="javascript:;" class="cm_btn1">正在为您处理</a></s:elseif>
	
	<div class="usdet_zw"></div>
	<jsp:include page="../messagebox.jsp"></jsp:include>
	<jsp:include page="../loading.jsp"/>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
function cancelOrder(clicked) {
	if(clicked == OK) {
		$.ajax({
			url : "resource/order_cancel?account.pkey=${account.pkey}",
			type : "POST",
			dataType : "json",
			data : {
				"orderid" : "${order.orderid}"
			},
			success : function(result) {
				if(result.success) {
					tipbox("提交成功", function() {
						location.reload();
					})
				}
			}
		})
	}
}
function payOrder() {
	$(".loading_float").show();
	$.ajax({
		url : "resource/order_preparePay?orderid=${order.orderid}&account.pkey=${account.pkey}",
		type : "POST",
		dataType : "json",
		success : function(result) {
			$(".loading_float").hide();
			if(result.success) {
				wx.chooseWXPay({
					timestamp: result.timeStamp, 
				    nonceStr: result.nonceStr, 
				    package: result.package, 
				    signType: result.signType,
				    paySign: result.paySign, 
				    success: function (res) {
						tipbox("支付成功", function() {
							location.href = "listOrder?account.pkey=${account.pkey}";
						})
				    	// 支付成功后的回调函数
				    }
				})
			}
		}
	})
}
$(function() {
	var status = "${order.status}";
	$(".cancelOrder").click(function() {
		messagebox("若取消订单  定金将无法退回",cancelOrder);
	})
	$(".payOrder").click(function() {
		payOrder();
	})
	$(".addr").click(function() {
		wx.openLocation({
			latitude : parseFloat($(this).attr('latitude')),
			longitude : parseFloat($(this).attr('longitude')),
			name : $(this).attr('name'),
			address : $(this).attr('address'),
			scale : 14
		});
	});
})
</script>
</html>