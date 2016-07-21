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
	<!--footer 底部导航-->
		<jsp:include page="../menu.jsp">
			<jsp:param value="2" name="currMenu"/>
		</jsp:include>
	<!--footer 底部导航-->
	<div class="find_top">
		<span><a href="javascript:;" class="hover" id="special">专题</a></span>
		<span><a href="listHot?account.pkey=${account.pkey }" id="hot">热销</a></span>	
	</div>
	<div class="special">
	<s:iterator value="specials" var="line" >
	<a href="javascript:;" class="find_spe" pkey="${line.pkey}">
		<div class="txt">${line.title }</div>
		<div class="findspe_flog"></div>
		<img data-original="../${line.baseImgUrl}" class="lazy"/>	
	</a>
	<div class="find_line"></div>
	</s:iterator>
	</div>
<div style="width:640px;height:83px"></div>
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
		placeholder : "images/emptySpecial.jpg",
		effect: "show",
		threshold:200
		});
	$(".find_spe").click(function(){
		var pkey = $(this).attr("pkey");
		window.location.href="showSpecial?id="+pkey+"&account.pkey=${account.pkey}";
	})
})
</script>
</html>