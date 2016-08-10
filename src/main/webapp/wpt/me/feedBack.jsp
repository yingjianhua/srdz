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
	
	<textarea class="usba_mes" placeHolder="请在这里写下对享食光·私人定制的感受，我们将不断进步，为你做得更好~"  ></textarea>
	
	<div class="ctw_in usba_in">
		<span class="lt">希望的联系方式</span>
		<div class="rt">
			<a href="javascript:;" class="xw_way xw_tel nhover" val="0"></a>	
			<a href="javascript:;" class="xw_way xw_wx" val="1"></a>
			<a href="javascript:;" class="xw_way wx_qq" val="2"></a>
		</div>
		<input type="hidden" class="xw_val" value="0" id="contactType" />
	</div>
	
	<div class="ctw_in usba_in">
		<span class="lt">联系方式</span>
		<input type="text" class="text rt" placeHolder="请输入电话号码" id="contactWay" />
	</div>		
	
	<div class="usba_zw"></div>
	<a href="#" class="cm_btn1">提 交</a>
		
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="4" name="currMenu"/>
	</jsp:include>
	<!--footer 底部导航-->
	<jsp:include page="../messagebox.jsp"/>
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
$(function(){
	//希望方式的选择
	$(".xw_way").on("click",function(){
		$(".xw_way").removeClass("nhover");	
		$(this).addClass("nhover");
		$(".xw_val").val($(this).attr("val"));
	});
	$(".cm_btn1").click(function(){
		var content = $(".usba_mes").val();
		if(content == ""){
			tipbox("请输入反馈内容");
			return false;
		}
		var contact = $("#contactWay").val();
		if(contact == "") { tipbox("请输入联系方式"); return false;}
		else{
			var phone = /^((13[0-9])|(147)|(15[0-3,5-9])|(171)|(17[6-8])|(18[0-3,5-9]))\d{8}$/;
			var wx = /^[a-zA-Z\d_-]{5,}$/;
			var qq = /^[1-9]d{4,12}$/;
			var type = $("#contactType").val();
			if(type == 0 && !phone.test(contact)){
				tipbox("电话格式不正确");
				return false;
			}
			if(type == 1 && !wx.test(contact)){
				tipbox("微信格式不正确");
				return false;
			}
			if(type == 2 && !qq.test(contact)){
				tipbox("qq格式不正确");
				return false;
			}
		}
		$.ajax({
			url : "resource/feedBack_suggest?account.pkey=${account.pkey}",
			type : "POST",
			data : {
				"content" : $(".usba_mes").val(),
				"contactWay" : $("#contactWay").val(),
				"contactType" : $("#contactType").val(),
				 "account.pkey" : "${account.pkey}"},
		   success : function(result){
			   if(result.success)
				   window.location.href ="showMe?account.pkey=${account.pkey}";
		   }
		})
	})
})
		
</script>
</html>