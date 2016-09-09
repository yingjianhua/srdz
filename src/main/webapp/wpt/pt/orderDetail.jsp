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
			<dd>期待您的光临</dd>	
		</dl>
		<div class="resok_flog"></div>
		<img src="images/reserve_bg.jpg" class="bg" />	
	</div>
	
	<div class="resok_info">
		<h2>享食光正在处理您的订单</h2>
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
				<a href="javascript:;" class="<s:if test="order.contactType==0">tel_ico</s:if><s:elseif test="order.contactType==1">wx_ico</s:elseif><s:elseif test="contactType==2">qq_ico</s:elseif>"></a>
			</div>
		</div>
		<s:if test="banquet.name!=null">
		<div class="cood_in">
			<span class="lt">宴会类型</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${banquet.name}" readonly="readonly" />
			</div>
		</div>
		</s:if>
		<div class="cood_in">
			<span class="lt">用餐时间</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.time }" readonly="readonly" />
			</div>
		</div>
		<s:if test="order.num">
		<div class="cood_in">
			<span class="lt">用餐人数</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.number }位" readonly="readonly" />
			</div>
		</div>
		</s:if>
		<s:if test="order.consumption">
		<div class="cood_in">
			<span class="lt">人均消费</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.consumption }元/位" readonly="readonly" />
			</div>
		</div>
		</s:if>
		<s:if test="order.gtRestaurant().name!=null">
		<div class="cood_in">
			<span class="lt">餐厅</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.restaurantName }" readonly="readonly" />
			</div>
		</div>
		</s:if>
		<s:if test="order.comboName!=null">
		<div class="cood_in">
			<span class="lt">套餐</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.comboName }" readonly="readonly" />
			</div>
		</div>
		</s:if>
		<s:if test="order.services.size()!=0">
		<div class="cood_in">
			<span class="lt">定制服务</span>	
			<div class="rt">
				<s:iterator value="order.services" var="line">${line.name } &nbsp;</s:iterator>
			</div>
		</div>
		</s:if>
		<s:if test="order.rem!=null">
		<div class="cood_in">
			<span class="lt">备注</span>	
			<div class="rt">
				<input type="text" class="text lt" value="${order.rem }" readonly="readonly" />
			</div>
		</div>
		</s:if>
		
	</div>
	<div class="hait_ask">
		
		<div class="hait_info">
			<p>如有疑问，请联系享食光，专属热线：400-826-2257</p>
		</div>
			
	</div>
	<a href="#" class="cm_btn1">确认</a>
	<div style="height:35px;"></div>
	
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
$(function(){
	$(".cm_btn1").click(function(){
		window.location.href = "listOrder?account.pkey=${account.pkey}"
	})
})
</script>
</html>