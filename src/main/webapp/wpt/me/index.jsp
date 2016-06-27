<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<title>享食光-私人定制</title>
</head>

<body>	
<jsp:include page="../messagebox.jsp"/>	
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="4" name="currMenu" />
	</jsp:include>
	<!--footer 底部导航-->
	<div class="user_face">
		<dl class="re_face">
			<dt class="face_outer"><img src="${user.imageUrl }" /></dt>	
			<dd>${user.nickname }<a href="javascript:;"></a></dd>
			<dd class="small">ID:${user.pkey }</dd>
			<dd class="small">如何成为代言人><a href="javascript:;"></a></dd>
		</dl>
		<img src="images/user_face.jpg" class="bg" />
	</div>
	
	<div class="user_links">
		<a href="showQrcode?account.pkey=${account.pkey }"><div>我的二维码<em class="icon code"></em></div></a>	
		<div class="double_line"></div>
		<a href="showCash?account.pkey=${account.pkey }">
			<div class="balance">
 	  	  	   <p>历史累计奖励(元)</p>
 	  	  	   <h1>${user.historyCommission }</h1>
 	  	  	   <p class="jl">可提现金额 <span>${user.cashableCommission }</span>元</p>
			</div>
	 	</a>
	 	<div class="double_line"></div>
		<a href="listOrder?account.pkey=${account.pkey}"><div>我的私人订单<em class="icon" id="num0">${orderNum }</em></div></a>	
		<a href="listCollect?account.pkey=${account.pkey}"><div>我的收藏<em class="icon" id="num1">${collectNum }</em></div></a>
		<a href="listFans?account.pkey=${account.pkey}"><div>我的粉丝<em class="icon text">${fansNum }人</em></div></a>
		<a href="#"><div>粉丝销量<p class="icon text">0.00元</p></div></a>	
		<div class="double_line"></div>
		<a href="#"><div>客服中心</div></a>	
		<a href="#"><div>意见反馈</div></a>	
	</div>
	<div class="user_invited">您是由 xxxxxx 推荐</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
$(function() {
	if("${orderNum}" != 0)
		$("#num0").addClass("num").text("${orderNum}");

	if("${collectNum}" != 0)
		$("#num1").addClass("num").text("${collectNum}");
		
	$(".user_links a").each(function(index) {
		if(index == 0) {
			$(this).click(function() {
				
			})
		}
	})
	$(".user_links a:eq(3)").click(function(){
		window.location.href = "serviceCenter?account.pkey="+${account.pkey};
	})
	$(".user_links a:eq(4)").click(function(){
		window.location.href = "feedBack?account.pkey="+${account.pkey};
	})
})
</script>
</html>