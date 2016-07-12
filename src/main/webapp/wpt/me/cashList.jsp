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
	<div class="cash_list">
		<div class="reward">
			<img src="images/reward.png">
		</div>
		<p>可提现金额(元)</p>
		<p class="price">￥0.00</p>
		<div class="btn gray">成为代言人后可提现</div>
		<div class="btn black" style="display: none;">立即提现</div>
		<div class="btn white"><a href="listCashHistory?account.pkey=${account.pkey }">提现历史</a></div>
	</div>
	<div class="cash_done" style="display: none;">
		<div class="content">
			<p class="des">提现金额(元)</p>
			<input type="number" placeholder=" | 请输入提现金额" id="cashinput"></input>
			<p class="tip">可提现金额:<span id="cashLimit">0.00</span>元</p>
		</div>
		<div class="btn black" id="cash">申请提现</div>
	</div>
	<jsp:include page="../messagebox.jsp"></jsp:include>
	<jsp:include page="../loading.jsp"/>
</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
var cashLimit = 0.00;
var reg=/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
$(function() {
	 $("#cashinput").focus(function(){
		    
		  $(this).css("color","#000");
		
		  $("#cashinput").bind("input propertychange",function(){
			   var val = $.trim($(this).val());
			   if(val.length==0){
				   $(".tip").html("可提现金额:<span id='cashLimit'>"+cashLimit+"</span>元");
				   $("#sbtn").attr("disabled","diabled");
			   }else if(val>cashLimit){
				   $(".tip").html("<span style='color:red;font-weight:normal;'>输入金额超过可提现金额</span>");
				   $("#sbtn").attr("disabled","diabled");
/* 			   }else if(val<50){
				   $(".tip").html("<span style='color:red;font-weight:normal;'>提现最低50元起</span>");
				   $("#sbtn").attr("disabled","diabled"); */
			   }else if(!reg.test(val)){
				   $(".tip").html("<span style='color:red;font-weight:normal;'>请输入正确金额</span>");
				   $("#tip").attr("disabled","diabled");
			   }else if(reg.test(val)){
				   $("#sbtn").removeAttr("disabled");
				   $(".tip").html("可提现金额:<span id='cashLimit'>"+cashLimit+"</span>元");  
				  
			   }  
		  });
	 });
	$.ajax({
		url : "resource/user_cashDetail?account.pkey=${account.pkey}",
		type : "POST",
		dataType : "json",
		success : function(result) {
			cashLimit = new Number(result.commission).toFixed(2);
			$(".price").text("￥"+ cashLimit);
			$("#cashLimit").text(cashLimit);
			if(result.isMember) {
				$(".btn.black").show();
				$(".btn.gray").hide();
			} else {
				$(".btn.black").hide();
				$(".btn.gray").show();
			}
		}
	})
	$(".black").click(function() {
		$(".cash_done").show();
		$(".cash_list").hide();
	})
	$("#cash").click(function() {
		if(reg.test($("#cashinput").val())) {
			$(".loading_float").show();
			$.ajax({
				url : "resource/user_cash?account.pkey=${account.pkey}",
				type : "POST",
				dataType : "json",
				data : {
					amt : $("#cashinput").val()
				},
				success : function(result) {
					$(".loading_float").hide();
					if(result.success) {
						tipbox("提现成功<br>将通过微信红包的方式发送到您的微信上", function() {location.href = location.href});
					}
				}
			})
		}
	})
})
</script>
</html>