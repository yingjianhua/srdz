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
			<dd>${order.restaurant.name } | ${order.comboName }</dd>	
		</dl>
		<div class="resok_flog"></div>
		<img src="images/reserve_bg.jpg" class="bg" />	
	</div>
	
	<div class="cood_info">
		<div class="cood_in" style="text-align:center;">
			<span style="font-size:26px;font-weight:bold;">${order.comboName }</span>	
		</div>
		<div class="cood_in" style="text-align:center;">
		<s:iterator value="order.details" var="line">
			<p style="padding:8px 0;color: #7b7b7b;"><span>${line.name }</span></p>	
		</s:iterator>
		</div>
		<div class="cood_in">
			<span class="lt">餐厅</span>	
			<div class="rt">
				<span>${order.restaurant.name }</span>
			</div>
		</div>
		<div class="cood_in">
			<span class="lt">餐厅电话</span>	
			<div class="rt">
				<span>${order.restaurant.mobile }</span>
			</div>
		</div>
		<div class="cood_in">
			<span class="lt">地址</span>	
			<div class="rt">
				<span>${order.restaurant.addr }</span>
			</div>
		</div>
		<div class="cood_in">
			<span class="lt">数量</span>	
			<div class="rt">
				<span class="cbtn jian">-</span>	
				<input type="text" class="price_n" value="1" name="comboNumber"/>
				<span class="cbtn jia">+</span>	
			</div>
		</div>
		
		<div class="cood_in">
			<span class="lt">单价</span>	
			<div class="rt">
				<span class="price_d">${order.price.intValue() }</span>元/套	
			</div>
		</div>
		<div class="cood_in">
			<span class="lt">总计</span>	
			<div class="rt">
				<span class="price_h">${order.price.intValue() }</span>元
			</div>
		</div>
		<div class="cood_in">
			<span class="lt">姓名</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.contactMan }" readonly="readonly" />
				<a href="javascript:;" class="sex_sel <s:if test="order.contactSex==2">sex_nv</s:if><s:else>sex_nan</s:else> hover"></a>
			</div>
		</div>
		<div class="cood_in">
			<span class="lt">联系方式</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.contactWay }" readonly="readonly" />				
			</div>			
		</div>
			
	</div>
	
	<a href="javascript:chooseWxPay()" class="cm_btn1 cood_btn">支付</a>
<jsp:include page="../messagebox.jsp"/>	
<jsp:include page="../loading.jsp"/>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
function chooseWxPay() {
	$(".cm_btn1-btn.cood_btn").attr("href","javascript:;");
	$(".loading_float").show();
	$.ajax({
		url : "resource/order_preparePay?orderid=${orderid}&account.pkey=${account.pkey}",
		type : "POST",
		dataType : "json",
		data : {comboNumber : $("input[name=comboNumber]").val()},
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
	//价格和数量
	function setPrice(){
		$(".price_h").html($(".price_d").html()*1*$(".price_n").val()*1);	
	}
	
	$(".price_n").blur(function(){
		setPrice();	
	});
	
	$(".jia").on("click",function(){
		var priceN = $(".price_n").val()*1;
		priceN++;
		$(".price_n").val(priceN);	
		setPrice();
	});
	
	$(".jian").on("click",function(){
		var priceN = $(".price_n").val()*1;
		priceN--;
		if(priceN <= 1){
			priceN = 1;	
		}
		$(".price_n").val(priceN);	
		setPrice();
	});
		
</script>
</html>