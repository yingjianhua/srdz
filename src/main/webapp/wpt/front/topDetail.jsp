<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/page/appmsg/page_mp_article_improve2d1390.css">
<link rel="stylesheet" type="text/css" href="css/cssm.css">
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>享食光-私人定制</title>
</head>
<body id="activity-detail" class="zh_CN mm_appmsg">
	<div class="cm_flog"></div><!--遮罩层-->
    <div class="frdet_head">
    <s:push value="top.top">
		<h2>${top.title}</h2>	
		<span class="time">${top.date} </span>
	</s:push>
		<a href="javascript:;" class="collect_a<s:if test="isCollect"> collect_over</s:if>"></a ><!--加上collect_over就是收藏后， 去了就是未收藏-->
		<div class="clear"></div>
	</div>
	
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="3" name="currMenu"/>
	</jsp:include>
	<!--footer 底部导航-->
	<jsp:include page="../messagebox.jsp"/>
	
	<div class="frdet_content">
		<s:push value="top.top">
		<input type="hidden" pkey=${top.pkey}>
		<div id="js_article" class="rich_media">
	        <div class="rich_media_inner">
	            <div id="page-content">
	                <div id="img-content" class="rich_media_area_primary">
	                    <div class="rich_media_content " id="js_content">
	                        ${top.content }
	                    </div>
					</div>
	            </div>
	        </div>
    	</div>
    	</s:push>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
$(function() {
	//收藏效果
	$(".collect_a").on("click",function(){
		var _this = this;
		$.ajax({
			url : "resource/top_collect?account.pkey=${account.pkey}",
			type : "POST",
			data : {"id" : $("input").attr("pkey")},
			success : function(data){
				if($(_this).hasClass("collect_over")){
					$(_this).removeClass("collect_over");
				}else{
					$(_this).addClass("collect_over");		
				}
			}
		})
	});
})
</script>
</html>
