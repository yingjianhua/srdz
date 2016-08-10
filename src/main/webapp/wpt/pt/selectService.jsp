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
	<jsp:include page="../messagebox.jsp"/>
	<h2 class="cm_head1">私人定制服务</h2>
	
	<div class="category_items">
		<s:iterator value="services" var="service" >
		<a href="javascript:;" class="cate_item" pkey="<s:property value="#service.pkey" />">
			<i class="ico1" style="background-image:url(../<s:property value="#service.icon" />)"></i>
			<span><s:property value="#service.name" /></span>	
		</a>
		</s:iterator>
		
		<div class="clear"></div>	
	</div>
	
	<a href="#" class="cm_btn1">不需要服务 直接提交</a>
	
	
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
$(function() {
	//cate_item的选中效果
	$(".cate_item").on("click",function(){
		$(this).toggleClass("cate_hover");
		if($(".cate_item.cate_hover").length == 0) {
			$(".cm_btn1").html("不需要服务 直接提交");
		} else {
			$(".cm_btn1").html("下一步");
		}
	});
	$(".cm_btn1").click(function() {
		var services = "";
		$(".cate_item.cate_hover").each(function(index) {
			if(index == 0) services = services + $(this).attr("pkey");
			else services = services +","+ $(this).attr("pkey");
		})	
		location.href = "inputContact?services="+services+"&account.pkey=${account.pkey}&comboId=${comboId}&banquetId=${banquetId}&pnum=${pnum}&perCapitaBudget=${perCapitaBudget}&areaId=${areaId}";
	})
})	
	
</script>
</html>