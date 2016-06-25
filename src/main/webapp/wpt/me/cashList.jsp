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
<div class="cash_main">
		<div class="tx2" style="display: none;">
			<div class="content">
				<h1>提现金额(元)</h1>
				<input type="tel" placeholder="请输入提现金额" id="inp"></input>
				<p class="ytx">可提现金额:<span id="withdrawCash">0.00</span>元</p>
			</div>
			<button class="unBtn"  id="sbtn" disabled="disabled" >申请提现</button>
		</div>
		<div class="tx1">
			<div class="reward">
				<img src="images/reward.jpg">
			</div>
			<p>可提现金额(元)</p>
			<h1>0.00</h1>
			<div class="btn gray" style="background:#949494;">成为捕手后可提现</div>
			<a href="/ygg-hqbs/withdrawals/getWithdrawalsLogs"><div class="btn white" style="background:#fff;color:#000;">提现历史</div></a>
		</div>
	</div>
</div>
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