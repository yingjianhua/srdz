<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>活动报名</title>
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/style.css">
<link rel="stylesheet" type="text/css"  href="style/img.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="js/jquery.event.drag-1.5.min.js"></script>
<script src="js/jquery.touchSlider.js"></script>
<script type="text/javascript">
	${jsCode}
	function imgForm_submit() {
		var imgForm = $("#imgForm")
		if($("[name='bean.namePerson']:first").val() === "") {
			showMessage("姓名不能为空");
			return false;
		}
		if($("[name='bean.namePerson']:first").val().length > 10) {
			showMessage("姓名过长");
			return false;
		}
		if($("[name='bean.phonePerson']:first").val() === "") {
			showMessage("电话不能为空");
			return false;
		} else {
			var pattern = /^((13[0-9])|(147)|(15[0-3,5-9])|(17[6-8])|(18[0-3,5-9]))\d{8}$/;
			if(!pattern.test($("[name='bean.phonePerson']:first").val())) {
				showMessage("电话号码格式不正确");
				return false;
			}
		}
		if($("[name='bean.des']:first").val() === "") {
			showMessage("请填写年龄");
			return false;
		}
		if($("[name='mediaIds']").length == 0) {
			showMessage("请选择至少一张照片");
			return false;
		}
		showTip();
		$("#imgForm").ajaxSubmit({
			type : "post",
			url : "expand_wa_WaActVoteEntry_ajaxcute",
			dataType : "json",
			success : function(data,statusText,jqXHR) {
				hideTip();
				if(data.success) {
					showMessageUrl(data.succMsg, "expand_wa_WaActVoteEntry_showList?vote.pkey=${vote.pkey}")
				} else {
					if(data.error) {
						if(data.error == "unsbscribErr") {
							showQrcode();
						} else {
							showMessage(data.errMsg)
						}
					} else {
						showMessage("网络繁忙，请稍后再试");
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				hideTip();
				showMessage("网络繁忙，请稍后再试");
			},
		})
	}
	function showTip() {
		var tips = $(".tips");
		var uploading = $('<div class="floatingCirclesG"><div class="f_circleG frotateG_01"></div><div class="f_circleG frotateG_02"></div><div class="f_circleG frotateG_03"></div><div class="f_circleG frotateG_04"></div><div class="f_circleG frotateG_05"></div><div class="f_circleG frotateG_06"></div><div class="f_circleG frotateG_07"></div><div class="f_circleG frotateG_08"></div></div>');
		tips.append(uploading);
		var margin = tips.css("width");
		margin = margin.substring(0,margin.length-2);
		margin = Math.ceil((parseInt(margin)-50)/2);
		margin = margin+"px";
		uploading.css("margin-left", margin);
		margin = tips.css("height");
		margin = margin.substring(0,margin.length-2);
		margin = Math.ceil((parseInt(margin)-50)/2);
		margin = margin+"px";
		uploading.css("margin-top", margin);
		tips.show();
	}
	function hideTip() {
		$(".tips").hide();
	}
</script>
<style type="text/css">
.tips{width:100%; height:100%; display: none; padding:0; font-size: 2.5rem; color: #fff; position: absolute; z-index: 5; left: 0; top:0; background:rgba(0,0,0,0.8);}
</style>
</head>
<body>
	<section class="signup">
		<%@ include file="banner.jsp"%>
		<%@ include file="vote.jsp"%>

		<div class="wxhd_sign_cont">
			<p class="wxhd_news_title1">活动报名</p>
				<form id="imgForm" method="post" action="expand_wa_WaActVoteEntry_ajaxcute" >
				<div class="wxhd_form_td clearfix">
					<div class="wxhd_form_tt">姓名：</div>
					<div class="wxhd_form_input">
						<input type="text" name="bean.namePerson" />
					</div>
				</div>
				<div class="wxhd_form_td clearfix">
					<div class="wxhd_form_tt">电话：</div>
					<div class="wxhd_form_input">
						<input type="text" name="bean.phonePerson"/>
					</div>
				</div>
				<div class="wxhd_form_td clearfix">
					<div class="wxhd_form_tt">年龄：</div>
					<div class="wxhd_form_input">
						<input type="text" name="bean.des" />
					</div>
				</div>
				<div class="wxhd_form_td clearfix">
					<div class="wxhd_form_tt">参赛　<br>照片：</div>
					<div class="wxhd_form_input">
						<ul class="wxhd_sign_img_list clearfix" id="imgUL">
							<s:if test="voteEntry!=null">
								<s:iterator value="listPhoto" id="photo">
									<img src="${photo.photoUrl}">
								</s:iterator>
							</s:if>
						<li id="choseLI">
							<img id="chooseImage" src="wa/vote/1601/assets/img/wxhd_img_add.png">
						</li>
						</ul>
					</div>
				</div>
				<div class="wxhd_form_td clearfix" style="text-align: center;">
					<input type="hidden" name="vote.pkey" value="${vote.pkey}" />
					<a href="javascript:void(0)" class="wxhd_sign_ok" onclick="imgForm_submit()">确认报名</a>
				</div>
				<div class="wxhd_form_td wxhd_sign_tt clearfix">
					（若在线报名失败，可以将报名信息：姓名+联系方式+年龄 +照片 发到邮箱：huyuanyuan@lexiangapp.cn）
				</div>
			</form>
		</div>
	</section>
<script src="wa/vote/1601/assets/js/functions.js"></script>
<script>
$(function(){
	$("#banner").css("position","absolute")
	var abc = $("#banner img").height()
	var max_height=0;
	$('#banner img').each(function(){
		if( $(this).height() > max_height ){
			max_height= $(this).height();
		};
	})
	$(".wxhd_nav").css("marginTop",max_height)

	$("input").on("focus",function(){
	    this.scrollIntoView();
	})
})
</script>
<%@ include file="menu.jsp"%>
<div class="tips"></div>
</body>
</html>