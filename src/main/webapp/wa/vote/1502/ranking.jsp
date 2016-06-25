<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh">
<head>

<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="Keywords" content="寻味温州! ">
<meta name="Description" content="寻味温州! ">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">

<title>排行榜</title>
<link rel="stylesheet" type="text/css" href="wa/vote/1501/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1501/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1501/assets/css/style.css">
<link rel="stylesheet" type="text/css" href="style/dropload.css">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script src="js/dropload.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<style>
.item{
	width:48%;
	margin:0 1% 0.5rem;
	position:relative;
	float:left;
}
</style>
<script type="text/javascript">
${jsCode}
</script>
</head>

<body>
	<section>
		<%@ include file="banner.jsp" %>
		<div class="wxhd_sign">
			<a href="expand_wa_WaActVoteEntry_show?vote.pkey=${vote.pkey}">我要报名</a>
		</div>
		<%@ include file="vote.jsp"%>
		
		<form action="expand_wa_WaActVoteEntry_showList" method="post">
			<div class="wxhd_search clearfix">
				<input type="text" name="sarg1" placeholder="请输入名字或编号" value="${sarg1 }">
				<input type="hidden" name="rank" value="${rank }">
				<input type="hidden" name="vote.pkey" value="${vote.pkey }">
				<a href="javascript:void(0)"><img src="wa/vote/1501/assets/img/search_btn2.png"></a>
			</div>
		</form>
		
		<div>
			<div class="wxhd_tabs">
				<ul class="clearfix">
					<li><a href="javascript:void(0);">最新参赛</a></li>
					<li><a href="javascript:void(0);">投票排行</a></li>
				</ul>
			</div>
			<div id="wx_container" class="wxhd_conts " style="display:block; visibility:visible">
			<s:iterator status="i" value="listEntry" id="entry">
            	<div class="item" entry=${entry.pkey }>
                    <a href="javascript:void(0)" onclick="window.location.href='expand_wa_WaActVoteEntry_showInfo?pkey=${entry.pkey}'">
                    	<img src="${listPhoto.get(i.index).photoUrl}">
                    </a>
                    <p class="wxhd_conts_li_num">编号${entry.number}</p>
                    <div class="wxhd_conts_li_btn">
                        <span>${entry.voteCount}票</span>
                        <a href="javascript:void(0);">给TA投票</a>
                    </div>
                </div>
			</s:iterator>
			</div>
			<div class="wxhd_conts_footer"></div>
		</div>
	</section>
	<%@ include file="menu.jsp" %>
</body>
<script src="wa/vote/1501/assets/js/libs/masonry.pkgd.min.js"></script>
<script src="wa/vote/1501/assets/js/libs/imagesloaded.pkgd.min.js"></script>
<script type="text/javascript">
var rank = ${rank};
var limit = 6;
var start = $(".wxhd_conts .item").length;
var dropload;
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
	if(${entryFlag})
		$(".wxhd_sign").hide();
	
	$(".wxhd_tabs li:eq(${rank})").addClass("curr");
	$(document).on("click", ".wxhd_conts_li_btn a", function() {
		/* 给TA投票 按钮点击事件  */
		var entrypkey = $(this).parent().parent().attr("entry");
		vote(entrypkey);
	})
	$(".wxhd_tabs li").click(function() {
		/*最新参赛 投票排行 按钮点击事件  */
		rank = $(this).index();
		$("input[name='rank']").val(rank)
		$(this).addClass("curr").siblings("li").removeClass("curr");
		dropload.lock();
		removeImage();
		loadImage();
	});
	$(".wxhd_search.clearfix a").click(function() {
		/*搜索 按钮点击事件  */
		$("form").submit();
	});
	var masonryContainer = document.querySelector('#wx_container');
	var maray = new Masonry(masonryContainer, {
		itemSelector : ".item",
		isAnimated : true,
	})
	dropload = $('.wxhd_conts_footer').dropload({
        scrollArea : window,
        loadDownFn : function(me){
        	loadImage();
        }
    });
	function removeImage() {
		/*清除报名的图片的列表  */
		$(".wxhd_conts").children().remove();
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
	            	console.log("start:"+start)
	            	console.log("data.length:"+data.length)
		            start += data.length;
	                $.each(data, function(i, img) {
		            	if($(".wxhd_conts .item[entry='"+img.entry+"']").length == 0) {
			                result += "<div class='item' entry="+img.entry+">";
		                    result += "<a href=\"javascript:void(0)\" onclick=\"window.location.href=\'expand_wa_WaActVoteEntry_showInfo?pkey="+img.entry+"\'\">";
		                    result += "<img src='"+img.photoUrl+"'>";
		                    result += "</a>";
		                    result += "<p class='wxhd_conts_li_num'>编号"+img.number+"</p>";
		                    result += "<div class='wxhd_conts_li_btn'>";
		                    result += "<span>"+img.voteCount+"票</span>";
		                    result += "<a href='javascript:void(0);'>给TA投票</a>";
		                    result += "</div>";
		                	result += "</div>";
		            	}
	                });
	                $('.wxhd_conts').append(result);
	            }
	            setTimeout(function(){
	            	var maray = new Masonry(masonryContainer, {
	            		itemSelector : ".item",
	            		isAnimated : true,
	            	});
	        		dropload.unlock();
	                dropload.resetload();
	            },1000);
	        },
	        error: function(xhr, type){
	        	dropload.unlock();
	        	dropload.resetload();
	        }
	    });
	};
	function getUrlIndex() {
		return parseInt((new Date()-new Date(2016, 0, 15))/1000/60/60/24);
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
					$(".wxhd_conts .item[entry="+entrypkey+"] span").html(voteCount+"票");
					$(".wxhd_nav li:eq(1) span").html(amountVoteCount);
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
});
</script>
</html>