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
	
	<div class="uscombo_head">
		<strong>订单编号：${order.orderid }</strong>
		<span class="type">${order.gtStatus().line.name }</span>	
	</div>
	
	<ul class="uscombo_info">
		<li>${order.gtRestaurant().name }</li>	
		<li>${order.comboName }</li>
		<li><a href="tel:${order.gtRestaurant().mobile }" class="tel">${order.gtRestaurant().mobile }</a></li>
		<i class="addr" latitude="${order.gtRestaurant().coordinate}" longitude="${order.gtRestaurant().longitude}" name="${order.gtRestaurant().name}" address="${order.gtRestaurant().addr}"><li><span class="pos">${order.gtRestaurant().addr }</span></li></i>
		<s:if test="order.checkcode != null"><li>核验码　${order.checkcode }</li></s:if>
	</ul>	
	
	<div class="uscombo_food">
		
		<div class="cbdet_title">
			<span>菜品内容</span>	
		</div>
		
		<div class="cbdet_item">
			<s:iterator value="listLine" id="line">
			<p>${line.name }<span>${line.price.intValue() }元/份</span></p>
			</s:iterator>
		</div>
			
	</div>
	
	<s:if test="order.status==0"><div class="uscombo_ctrl">
		<a href="javascript:;" class="pay_btn lt payOrder">立即付款</a>
		<a href="javascript:;" class="esc_btn rt cancelOrder">取消订单</a>
	</div></s:if>
	<s:elseif test="order.status==4"><a href="javascript:;" class="cm_btn1 cancelOrder">申请退款</a></s:elseif>
	<s:elseif test="order.status==5"><a href="javascript:;" class="cm_btn1">订单已完成</a></s:elseif>
	<s:elseif test="order.status==6"><a href="javascript:;" class="cm_btn1">订单已关闭</a></s:elseif>
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
			url : "resource/order_cancel",
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
function payOrder() {
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
		messagebox("您真的要取消订单吗？",cancelOrder);
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