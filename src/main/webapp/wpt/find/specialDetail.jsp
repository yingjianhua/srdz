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
<title>享食光-私人定制</title>
</head>

<body>	
		
	<a href="javascript:;" class="find_spe">
		<div class="txt">${special.title }</div>
		<div class="findspe_flog"></div>
		<img src="../${special.topImgUrl }" style="height:359px"/>	
	</a>
	
	<div class="fddet_intro">
		<h2>${special.title}</h2>
		<p>${special.intro}</p>
	</div>
	
	<s:iterator value="restaurants" var="line" status="x">
		<a href="javascript:;" class="fddet_item" pkey="${line.pkey}">
		<dl><dt>${line.name}</dt> <dd>${line.des}</dd></dl>
		<span class="xq">查看详情</span>
		<div class="fddet_flog"></div>
		<img data-original="../${line.imgUrl}" class="lazy"/>
		</a>
	</s:iterator>
			
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
$(function(){
	$("img.lazy").lazyload({
		placeholder : "images/emptySpecialDetail.jpg",
		effect: "show",
		threshold:200
		});
	$(".fddet_item").click(function(){
		var pkey = $(this).attr("pkey");
		location.href = "showRestaurant?id="+pkey+"&account.pkey=${account.pkey}";
	})
})
</script>
</html>