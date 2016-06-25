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
<link rel="stylesheet" type="text/css" href="wa/vote/1501/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1501/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1501/assets/css/style.css">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
${jsCode}
var urls = ["http://liuliangtong.cn/activity/receive.html?aid=037164",
            "http://liuliangtong.cn/activity/receive.html?aid=388144",
            "http://liuliangtong.cn/activity/receive.html?aid=192881",
            "http://liuliangtong.cn/activity/receive.html?aid=102905",
            "http://liuliangtong.cn/activity/receive.html?aid=947249",
            "http://liuliangtong.cn/activity/receive.html?aid=921220",
            "http://liuliangtong.cn/activity/receive.html?aid=135806",
            "http://liuliangtong.cn/activity/receive.html?aid=593303",
            "http://liuliangtong.cn/activity/receive.html?aid=303941",
            "http://liuliangtong.cn/activity/receive.html?aid=070745",
            "http://liuliangtong.cn/activity/receive.html?aid=757809",
            "http://liuliangtong.cn/activity/receive.html?aid=969921",
            "http://liuliangtong.cn/activity/receive.html?aid=297571",
            "http://liuliangtong.cn/activity/receive.html?aid=156923",
            "http://liuliangtong.cn/activity/receive.html?aid=673091",
            "http://liuliangtong.cn/activity/receive.html?aid=279298",
            "http://liuliangtong.cn/activity/receive.html?aid=145625",
            "http://liuliangtong.cn/activity/receive.html?aid=268244",
            "http://liuliangtong.cn/activity/receive.html?aid=348637",
            "http://liuliangtong.cn/activity/receive.html?aid=181244",
            "http://liuliangtong.cn/activity/receive.html?aid=950560",
            "http://liuliangtong.cn/activity/receive.html?aid=256540",
            "http://liuliangtong.cn/activity/receive.html?aid=241516",
            "http://liuliangtong.cn/activity/receive.html?aid=533459",
            "http://liuliangtong.cn/activity/receive.html?aid=462026",
            "http://liuliangtong.cn/activity/receive.html?aid=549814",
            "http://liuliangtong.cn/activity/receive.html?aid=372697",
            "http://liuliangtong.cn/activity/receive.html?aid=548798",
            "http://liuliangtong.cn/activity/receive.html?aid=690684",
            "http://liuliangtong.cn/activity/receive.html?aid=758561"];
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
function getUrlIndex() {
	return parseInt((new Date()-new Date(2016, 0, 15))/1000/60/60/24);
};
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
				var index = getUrlIndex() % urls.length;
				if(index < 0) 
					index += urls.length;
				showMessageUrl("投票成功，点击确定领取流量！", urls[index]);
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
				<p class="user_info_title2">参赛宣言</p>
				<p class="user_info_tt2">${bean.des}</p>
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