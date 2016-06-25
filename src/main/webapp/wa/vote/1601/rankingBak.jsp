<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>排行榜</title>
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/style.css">
<link rel="stylesheet" type="text/css" href="style/dropload.css">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script src="js/dropload.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
${jsCode}
var rank = 0;
var limit = 2;
var start = 0;
var dropload;
var imgs = [];
$(function(){
	var _errMsg = '${errMsg}';
	if(_errMsg != '') {
		$("#layer_ok p").html(_errMsg);
		$("#layer_ok").fadeIn(200);
	}
	start = $(".wxhd_conts .clearfix li").length;
	
	if(${entryFlag})
		$(".wxhd_sign").hide();
	dropload = $('.wxhd_conts').dropload({
        scrollArea : window,
        loadDownFn : function(me){
        	loadImage();
        }
    });
	$("wxhd_conts_li_btn a").click(function() {
		/* 给TA投票 按钮点击事件  */
		var entrypkey = $(this).parent().parent().attr("entry");
		vote(entrypkey);
	});
	$(".wxhd_tabs li").click(function() {
		$(this).addClass("curr").siblings("li").removeClass("curr");
		rank = $(this).index();
		$(".wxhd_search input").val("");
		removeImage();
		loadImage();
	});
	$(".wxhd_search.clearfix a").click(function() {
		/*搜索 按钮点击事件  */
		removeImage();
		loadImage();
		
	})
});
function removeImage() {
	/*清除报名的图片的列表  */
	$(".wxhd_conts .clearfix").children().remove();
	start = 0;
	dropload.resetload();
};
function loadImage() {
	$.ajax({
        type: 'post',
        url: 'expand_wa_WaActVoteEntry_loadImage',
        data : {
        	limit : limit,
        	start : start,
        	rank : rank,
        	sarg1 : $(".wxhd_search.clearfix input").val(),
        	"vote.pkey" : ${vote.pkey},
        },
        dataType: 'json',
        success: function(data){
            var result = '';
            if(data.length && data.length > 0) {
	            start += data.length;
                $.each(data, function(i, img) {
	            	if($(".wxhd_conts .clearfix li[entry='"+img.entry+"']").length == 0) {
	                	result += "<li entry="+img.entry+">";
	                	result += "<img src='"+img.photoUrl+"' onclick='window.location.href=\'expand_wa_WaActVoteEntry_showInfo?pkey="+img.entry+"\'  >";
	                	result += "<p class='wxhd_conts_li_num'>编号"+img.number+"</p>";
	                	result += "<div class='wxhd_conts_li_btn'>";
	                	result += "<span>"+img.voteCount+"票</span>";
	                	result += "<a href='javascript:void(0)' >给TA投票</a>";
	                	result += "</div>";
	                	result += "</li>";
	            	}
                });
            }
            setTimeout(function(){
                $('.wxhd_conts .clearfix').append(result);
                dropload.resetload();
            },1000);
        },
        error: function(xhr, type){
        	dropload.resetload();
        }
    });
};
function vote(entrypkey) {
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
				$(".wxhd_conts .clearfix li[entry="+entrypkey+"] div span").html(voteCount+"票");
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
		<div class="wxhd_sign">
			<a href="expand_wa_WaActVoteEntry_show?vote.pkey=${vote.pkey}">我要报名</a>
		</div>
		<%@ include file="vote.jsp"%>
		
		<form action="expand_wa_WaActVoteEntry_showList">
			<div class="wxhd_search clearfix">
				<input name="sarg1" type="text" placeholder="请输入名字或编号">
				<a href="javascript:void(0)"><img src="wa/vote/1601/assets/img/search_btn2.png"></a>
			</div>
		</form>

		<div>
			<div class="wxhd_tabs">
				<ul class="clearfix">
					<li class="curr"><a href="javascript:void(0)">最新参赛</a></li>
					<li><a href="javascript:void(0)">投票排行</a></li>
				</ul>
			</div>
			<div class="wxhd_conts" style="display:block;">
				<ul class="clearfix">
				<s:iterator status="i" value="listEntry" id="entry">
					<li entry=${entry.pkey}>
						<img src="${listPhoto.get(i.index).photoUrl}" onclick="window.location.href='expand_wa_WaActVoteEntry_showInfo?pkey=${entry.pkey}'">
						<p class="wxhd_conts_li_num">编号${entry.number}</p>
						<div class="wxhd_conts_li_btn">
							<span>${entry.voteCount}票</span>
							<a href="javascript:void(0);" >给TA投票</a>
						</div>
					</li>
				</s:iterator>
				</ul>
			</div>
		</div>
	</section>
	<%@ include file="menu.jsp"%>
</body>
</html>