<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我是${bean.number}号${bean.namePerson} 正在参加${vote.name}</title>
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/style.css">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
${jsCode}
$(function(){
	var _errMsg = '${errMsg}';
	if(_errMsg != '') {
		if(_errMsg == 'qrerr') {
			showQrcode();
		} else {
			showMessage(_errMsg);
		}
	}
	$(".user_btns a:first").click(function() {
		vote();
	})
});
function vote() {
	var entrypkey = ${bean.pkey}
	$.ajax({
		url : "expand_wa_WaActVote_ajaxcute",
		type : "post",
		dataType : "json",
		data : {
			"entry.pkey" : entrypkey
		},
		success : function(data, statusText, jqXHR) {
			if(data.success) {
				var voteCount = data.voteCount;
				var amountVoteCount = data.amountVoteCount;
				var rank_html = "";
				if(data.rank) {
					rank_html = "排名:"+data.rank;
				}
				$(".user_detail.clearfix div p:eq(3)").html("票数:" + voteCount + "票 " + rank_html);
				$(".wxhd_nav .clearfix li:eq(1) a p:eq(1) span").html(amountVoteCount);
				showMessage(data.succMsg);
			} else {
				if(data.error) {
					if(data.error === "unsbscribErr") {
						showQrcode();
					} else {
						showMessage(data.errMsg);
					}
				} else {
					showMessage("网络繁忙，请稍后再试");
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage("网络繁忙，请稍后再试");
		},
		
	})
};
</script>
</head>

<body>
	<section>
		<%@ include file="banner.jsp"%>
		<%@ include file="vote.jsp"%>

		<div class="user_detail clearfix">
			<div class="user_info">
				<p class="user_info_title">选手信息</p>
				<p class="user_info_tt1">姓名:${bean.namePerson}</p>
				<p class="user_info_tt1">编号:${bean.number}</p>
				<p class="user_info_tt1">票数:${bean.voteCount}<s:if test="rank!=0"> 排名:${rank}</s:if></p>
			</div>
			<div class="user_img" id="userimg">
				<div class="hd">
	                <ul></ul>
	            </div>
	            <div class="bd">
	                <ul>
					<s:iterator value="listPhoto" var="photo">
	                    <li><a href="#"><img src="${photo.photoUrl}"/></a></li>
					</s:iterator>
	                </ul>
	            </div>
			</div>
			<div class="user_img swiper-container">
				<div class="swiper-wrapper">
				</div>
				<div class="banner swiper-pagination"></div>
			</div>
		</div>

		<div class="user_btns">
			<a href="javascript:void(0)">给TA投票</a>
			<a href="expand_wa_WaActVoteEntry_show?vote.pkey=${bean.vote}">我要参加</a>
		</div>
	</section>
	<%@ include file="menu.jsp"%>
</body>
<script type="text/javascript">
TouchSlide({ 
	slideCell:"#userimg",
	titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
	mainCell:".bd ul", 
	effect:"leftLoop", 
	autoPlay:true,//自动播放
	autoPage:true, //自动分页
});
</script>
</html>