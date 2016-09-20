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

<body>	

	<s:iterator value="collects" var="line">
		<a href="#" class="usct_item" pkey="${line.headline.pkey}">
		<img src="${line.headline.imgUrl}" class="photo" />
		<div class="txt">
			<div class="name">${line.headline.title}</div>
			<span class="time">${line.headline.date}</span>
		</div>
		<div class="clear"></div>
		</a>
	</s:iterator>
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="4" name="currMenu" />
	</jsp:include>
	<!--footer 底部导航-->
	<jsp:include page="../messagebox.jsp"/>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
	$(function(){
		$(".usct_item").click(function(){
			var pkey = $(this).attr("pkey");
			window.location.href = "showTop?id=" + pkey+"&account.pkey="+${account.pkey}
		})
	})
</script>
</html>