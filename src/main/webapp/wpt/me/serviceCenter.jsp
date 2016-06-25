<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="format-detection" content="telephone=no" />
<link type="text/css" rel="stylesheet" href="css/cssm.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>享食光-私人定制</title>
</head>

<body>	
	
	<div class="usser_top">
		<span class="lt">客服电话</span>
		<a href="javascript:;" class="tel_btn">${serviceCenter.mobile }</a>	
	</div>
	
	<dl class="usser_code">
		<dt><strong>享食光</strong> 私人定制客服微信二维码<br />轻扫以下二维码进行添加</dt>	
		<dd>
			<img src="../${serviceCenter.qrcode }" />
		</dd>
	</dl>
		
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="4" name="currMenu" />
	</jsp:include>
	<!--footer 底部导航-->
	
	<div class="cm_flog"></div><!--遮罩层-->
	
	<div class="cm_win1">
		<div class="tip_txt">${serviceCen.mobile }</div>	
		<a href="javascript:;" class="btn close">取消</a>
		<a href="tel:400-826-6666" class="btn">呼叫</a>
	</div>
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
	
	//呼叫弹窗
	$(".usser_top .tel_btn").on("click",function(){
		$(".cm_flog").show();
		$(".cm_win1").show();
	});
	
	$(".cm_win1 .close").on("click",function(){
		$(".cm_flog").hide();
		$(".cm_win1").hide();	
	});
		
</script>
</html>