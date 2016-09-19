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

<body style="background-color:#eaeaea">

		<div class="resok_top">
		<dl>
			<dt><img src="images/hm_logo.png" /></dt>
			<dd>期待您的光临</dd>	
		</dl>
		<img src="images/reserve_bg.jpg" class="bg" />	
	</div>
	<p style="text-align:center;line-height:70px;">${customForm.createTime }</p>
	<div style="border: 1px solid #000;border-radius: 15px;margin: 20px auto;width: 600px;background-color:#fff">
		<div class="resok_info">
			<h2 style="height:140px;line-height: 70px;text-align:center;">私人订制表单&nbsp;&nbsp;<br>
				<p style="font-size: 22px;font-weight: normal;">客服人员将尽快与您联系</p>
			</h2>
			<div class="cood_in">
				<span class="lt">表单编号</span>	
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.formid }" readonly="readonly" />
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">姓名</span>	
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.contactMan }" readonly="readonly" />
					<a href="javascript:;" class="sex_sel <s:if test="customForm.contactSex==2">sex_nv</s:if><s:else>sex_nan</s:else> hover"></a>
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">联系方式</span>	
				<div class="rt">
					<s:property value="customForm.contactWay"/>
					<input type="text" class="text lt" value="${customForm.contactWay }" readonly="readonly" />
					<a href="javascript:;" class="<s:if test="customForm.contactType==0">tel_ico</s:if><s:elseif test="customForm.contactType==1">wx_ico</s:elseif><s:elseif test="contactType==2">qq_ico</s:elseif>"></a>
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">宴会类型</span>	
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.banquet}" readonly="readonly" />
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">用餐时间</span>
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.time }" readonly="readonly" />
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">用餐人数</span>	
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.number }" readonly="readonly" />
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">预算</span>	
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.budget }" readonly="readonly" />
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">定制服务</span>	
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.customServices }" readonly="readonly" />
				</div>
			</div>
			<div class="cood_in">
				<span class="lt">备注</span>	
				<div class="rt">
					<input type="text" class="text lt" value="${customForm.rem }" readonly="readonly" />
				</div>
			</div>
		</div>
		<div class="hait_ask">
			<div class="hait_info">
				<p>如有疑问，请联系享食光，专属热线：400-826-2257</p>
			</div>
		</div>
	</div>
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