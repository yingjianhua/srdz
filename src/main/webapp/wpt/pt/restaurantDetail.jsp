<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<link type="text/css" rel="stylesheet" href="css/css.css" />
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

<body class="gray-bg">	
	
	<div class="hait_top" id="banner">
		<div class="hd" style="display:none">
        	<ul></ul>
    	</div>
		<div class="bd">
	     	<ul>
		     	<s:iterator value="banners" var="b">
		     	<li>
		     	<img class="photo" src="../${b.imgUrl}">
		     	</li>
		     	</s:iterator>
	     	</ul>
    	</div>
		<div class="txt">
			<h2>${restaurant.name }</h2>	
			<p>${restaurant.des }</p>
		</div>
		<div class="clear"></div>	
	</div>
	
	<div class="hait_list">
		<s:iterator value="combos" var="combo">
		<a href="javascript:;" class="hait_li" pkey="<s:property value="#combo.pkey" />">
			<img src="../<s:property value="#combo.imgUrl" />" class="photo" />
			<dl>
				<dt><s:property value="#combo.name"/></dt>
				<dd>
					<span class="price">RMB <s:property value="#combo.price.intValue()" />元</span>
					<em class="xx">详细</em>
				</dd>
			</dl>
		</a>
		</s:iterator>
		
		<div class="clear"></div>	
	</div>
	
	<div class="hait_det">
		<s:if test="cases.size() > 0">
			<div class="title">
				私人定制的案例介绍	
			</div>
		</s:if>
		<s:iterator value="cases" var="case">
			<img src="../<s:property value="#case.imgUrl" />" class="photo" />
			<s:property value="#case.des" />
		</s:iterator>
		<div class="hait_info">
			<h2>餐厅信息</h2>	
			<p>营业时间：${restaurant.startdate }-${restaurant.stopdate }</p>
			<p>人均消费：${restaurant.consumption.intValue() }元/人</p>
			<p>餐厅地址：${restaurant.addr }</p>
			<p>餐厅电话：${restaurant.mobile }</p>
			<s:if test="restaurant.rem!=null&&restaurant.rem.length()>0"><p>贴士：${restaurant.rem }</p></s:if>
		</div>
		
		<div class="clear"></div>	
	</div>
	
	<div class="hait_ask">
		
		<div class="hait_info">
			<h2>购买须知</h2>	
			<p>如需开具发票，请联系商家；</p>
			<p>如有疑问，请联系享食光，专属热线：400-826-2257</p>
		</div>
			
	</div>
	
</body>
<script src="../wp/fif/assets/js/libs/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
function slide() {
	//轮播图
	TouchSlide({ 
		slideCell:"#banner",
		titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell:".bd ul", 
		effect:"leftLoop", 
		autoPlay:true,//自动播放
		autoPage:true, //自动分页
	});
}
$(function() {
	$(".hait_li").click(function() {
		var url = "showCombo?id="+$(this).attr("pkey")+"&account.pkey=${account.pkey }";
		location.href = url;
	})
	try {
		slide();
	} catch(e) {
		alert(e);
	}
})
</script>
</html>