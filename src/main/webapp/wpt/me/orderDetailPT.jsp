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
<title>享食光-私人定制</title>
</head>

<body>	
	
	<div class="resok_top">
		<dl>
			<dt><img src="images/hm_logo.png" /></dt>
			<dd><s:if test="order.status==1">正在处理您的订单</s:if>
				<s:elseif test="order.status==2">受理完毕</s:elseif>
				<s:elseif test="order.status==3">定金支付完成</s:elseif>
				<s:elseif test="order.status==4">付款完成</s:elseif>
				<s:elseif test="order.status==5">订单已完成</s:elseif>
				<s:elseif test="order.status==6">订单已关闭</s:elseif>
				<s:elseif test="order.status==7">申请取消订单</s:elseif>
				<s:elseif test="order.status==8">申请退款</s:elseif>
			</dd>	
		</dl>
		<div class="resok_flog"></div>
		<img src="images/user_detail.jpg" class="bg" />	
	</div>
	
	<div class="resok_info">
		<h2>订单编号：${order.orderid }</h2>	
		<ul>
			<li><span class="name">${order.contactMan }</span><i class="sex_ico <s:if test="order.contactSex==1">nav</s:if><s:elseif test="order.contactSex==2">nv</s:elseif>"></i></li><!--class="sex_ico nan"就是男图标-->
			<li><span class="<s:if test="order.contactType==0">tel_ico</s:if><s:elseif test="order.contactType==1">wx_ico</s:elseif><s:else>qq_ico</s:else>"></span><a href="tel:${order.contactWay }">${order.contactWay }</a></li>
			<s:if test="order.banquet"><li>${order.gtBanquet().name }</li></s:if>
			<li>${order.formatDate }</li>
			<s:if test="order.number"><li>${order.number }位</li></s:if>
			<s:if test="order.consumption"><li>${order.consumption.intValue() }元/位</li></s:if>
			<li><s:iterator value="orderServices" var="orderService">${orderService.name }  </s:iterator></li>
			<s:if test="order.rem!=null"><li>${order.rem }</li></s:if>			
		</ul>
	</div>
	
	<s:if test="order.status==1"><a href="javascript:;" class="cm_btn1 cancelOrder">取消订单</a></s:if>
	<s:elseif test="order.status==2"><div class="uscombo_ctrl">
		<a href="javascript:;" class="pay_btn lt payDeposit">支付定金</a>
		<a href="javascript:;" class="esc_btn rt cancelOrder">取消订单</a>
	</div></s:elseif>
	<s:elseif test="order.status==3"><div class="uscombo_ctrl">
		<a href="javascript:;" class="pay_btn lt payDeposit">支付余款</a>
		<a href="javascript:;" class="esc_btn rt cancelOrder">取消订单</a>
	</div></s:elseif>
	<s:elseif test="order.status==4"><a href="javascript:;" class="cm_btn1 cancelOrder">申请退款</a></s:elseif>
	<s:elseif test="order.status==5"><a href="javascript:;" class="cm_btn1 esc_btn">订单已完成</a></s:elseif>
	<s:elseif test="order.status==6"><a href="javascript:;" class="cm_btn1 esc_btn">订单已关闭</a></s:elseif>
	<s:elseif test="order.status==7"><a href="javascript:;" class="cm_btn1">正在为您处理</a></s:elseif>
	<s:elseif test="order.status==8"><a href="javascript:;" class="cm_btn1">正在为您处理</a></s:elseif>
	
	<div class="usdet_zw"></div>
	<jsp:include page="../messagebox.jsp"></jsp:include>
	
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
				"orderId" : "${order.orderid}"
			},
			success : function(result) {
				if(result.success) {
					tipbox(result.succMsg, function() {
						location.reload();
					})
				}
			}
		})
	}
}
function payDeposit() {
	$.ajax({
		url : "resource/order_preparePay?orderId=${order.orderid}",
		type : "POST",
		dataType : "json",
		success : function(result) {
			if(result.success) {
				wx.chooseWXPay({
					timestamp: result.timeStamp, 
				    nonceStr: result.nonceStr, 
				    package: result.package, 
				    signType: result.signType,
				    paySign: result.paySign, 
				    success: function (res) {
						tipbox("支付成功", function() {
							location.reload();
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
		messagebox("您真的要取消订单吗？",cancelOrder);
	})
	$(".payDeposit").click(function() {
		payDeposit();
	})
})
</script>
</html>