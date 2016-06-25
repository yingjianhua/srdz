<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>福利报名</title>
<meta name="Keywords" content="公众号活动! ">
<meta name="Description" content="公众号活动! ">
<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
<link rel="stylesheet" type="text/css" href="wax/lady/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wax/lady/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wax/lady/assets/css/style.css">
<link rel="stylesheet" type="text/css"  href="style/img.css" />
<style>#layer_qrcode,#layer_ok{
	position:fixed;
	top:0;
	left:0;
	right:0;
	bottom:0;
	background:rgba(0,0,0,.45);
	display:none;
	z-index:3;
}
.layer_qrcode_cont{
	width:80%;
	background:#fff;
    margin:45% auto 0;
	border:3px solid #f0ba00;
	border-radius:3px;
}
.layer_qrcode_cont img{
	display:block;
}
#layer_qrcode_close{
	width:0.6rem;
	float:right;
	margin-top:6px;
	margin-right:6px;
}
.layer_qrcode_tt{
	margin-top:0.5rem;
	text-align:center;
	line-height:0.8rem;
	font-size:16px
}
#layer_qrcode_img{
	width:4rem;
	margin:0 auto;
	padding-top:0.1rem;
	padding-bottom:0.2rem;
}
#layer_ok_btn{
	text-align:center;
	padding-top:0.3rem;
	padding-bottom:0.5rem;
}
#layer_ok_btn a{
	text-align:center;
	display:inline-block;
	text-decoration:none;
	color:#fff;
	padding-left:0.6rem;
	padding-right:0.6rem;
	line-height:0.8rem;
	border:1px solid #f0ba00;
	border-radius:4px;
	background:#323232;
	font-size:16px
}
.wxhd_sign_img_list li{
	float:left;
	width:23%;
	margin-right:2%;
	margin-bottom:0.4rem;
}
.wxhd_sign_img_list li img{
	display:block;
	border:1px solid #323232;
}
.myForm{
	display:none;
}
</style>
<script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
	${jsCode}
	function imgForm_submit() {
		var imgForm = $("#imgForm");
		if($("[name='bean.name']:first").val() === "") {
			showMessage("姓名不能为空");
			return false;
		}
		if($("[name='bean.name']:first").val().length > 10) {
			showMessage("姓名过长");
			return false;
		}
		if($("[name='bean.mobile']:first").val() === "") {
			showMessage("电话不能为空");
			return false;
		} else {
			var pattern = /^((13[0-9])|(147)|(15[0-3,5-9])|(17[6-8])|(18[0-3,5-9]))\d{8}$/;
			if(!pattern.test($("[name='bean.mobile']:first").val())) {
				showMessage("电话号码格式不正确");
				return false;
			}
		}
		if($("[name='mediaIds']").length == 0) {
			showMessage("请选择至少一张照片");
			return false;
		}
		imgForm.submit();
	}
	
	$(function(){
		$("#layer_ok_btn a").click(function(){
			$("#layer_ok").hide();
		});
	});
	function showQrcode() {
		$("#layer_qrcode").fadeIn(200);
	}
	function showMessage(message){
		$("#layer_ok p").html(message);
		$("#layer_ok").fadeIn(200);
	}
	$(function(){
		var _succMsg = '${SuccMsg}';
		$("#layer_qrcode_close").addClass("ok");
		if(_succMsg != ''){
			showMessage(_succMsg);
			$("#ok").addClass("ok");
		}
		if("${errMsg}" != "")
			$("#layer_qrcode").show();
		if("${bnftEntry.name}" == ''){
			$(".myForm").show();
			$(".entry").hide();
		}
			
	});
</script>
<script>
	var deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth > 750){
		deviceWidth = 750
	};
	document.documentElement.style.fontSize = deviceWidth/7.5 + 'px';
</script>
</head>

<body>
	
	<div class="banner">
		<img src="${bnft.imgUrl}">
	</div>
	

	
	<div class="hdjs">
			<div class="hdjs-tt">
				<p>活动介绍</p>
				<p>${bnft.des}</p>
			</div>
			<div class="myForm">
			<form  id="imgForm" action="expand_wax_WaxBnftEntry_xcute" method="post">
				<div class="hdjs-form">
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">您的姓名：</div>
						<div class="hdjs-form-right"><input type="text" name="bean.name"/></div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">联系电话：</div>
						<div class="hdjs-form-right"><input type="text" name="bean.mobile"/></div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">朋友圈截图：</div>
						<div class="hdjs-form-img1">
							<ul id="imgUL" class="wxhd_sign_img_list clearfix">
								<li id="choseLI"><img id="chooseImage"  src="wax/bnft/assets/img/wxhd_img_add.png"></li>							
							</ul>
						</div>
					</div>
					<input type="hidden" name="bnft.pkey" value="${bnft.pkey}">
					<input type="hidden" name="account.pkey" value="${account.pkey}">
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-btn"><a href="javascript:void(0)" onclick="imgForm_submit()">提 交</a></div>
					</div>
				</div>
			</form>
			</div>
			<div class="entry">
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">姓名：</div>
						<div class="hdjs-form-right">${bnftEntry.name}</div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">电话：</div>
						<div class="hdjs-form-right">${bnftEntry.mobile}</div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">商家：</div>
						<div class="hdjs-form-right">${bsn.name}</div>
					</div>
					<div class="hdjs-form-tr clearfix" >
						<div class="hdjs-form-left">地址：</div>
						<div class="hdjs-form-right" id="add">${bsn.addr} <i class="icon-map-marker" style="height:50px;" latitude="${bsn.latitude}" longitude="${bsn.longitude}" name="${bsn.name}" address="${bsn.addr}"></i></div>
					</div>
			</div>
		</div>
	<div id="layer_qrcode">
			<div class="layer_qrcode_cont">
				<div id="layer_qrcode_close"><img src="wax/lady/assets/img/close.jpg"></div>
				<p class="layer_qrcode_tt">长按下方图片,识别二维码　<br>
	进入公众号，点击下方菜单栏进行报名或投票。</p>
				<div id="layer_qrcode_img"><img src="uploads/qrcode/1.jpg"></div>
			</div>
		</div>
		
	<div id="layer_ok">
		<div class="layer_qrcode_cont">
			<p class="layer_qrcode_tt">--------------------<br>--------------------</p>
			<div id="layer_ok_btn">
				<a href="javascript:void(0);" id="ok" >确定</a>
			</div>
		</div>
	</div>
</body>
	<!-- Grab Google CDN's jQuery. fall back to local if necessary -->
	<script src="wax/lady/assets/js/libs/jquery-1.11.0.min.js"></script>
	<script src="wax/lady/assets/js/functions.js"></script>
</html>