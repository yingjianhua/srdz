<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="text/javascript" src="js/flexible.js"></script>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/me_fansList.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>享食光</title>
</head>
<body>
	<div class="main">
		<div class="search-bar">
			<div class="search">
				<img class="search_icon" src="images/search.png">
				<input class="search-input" type="text" placeholder="输入粉丝ID" id="idinput"></input>
				<div class="search-submit" id=search-btn>搜索</div>
			</div>
		</div>
		<div class="nav">
			<ul>
				<li class="select">我的粉丝（${fans1Num }）</li>
			</ul>
			<div class="clean"></div>
		</div>
		<div class="list">
			<ul id="list"></ul>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script>
${jsCode}
</script>
<script>
function toFanHtml(fan) {
	var content = "";
	content += '<li class="fan"><ul>';
	content += '<li class="fan-detail-head"><img src="'+fan.imageUrl+'"></li>';
	content += '<li class="fan-detail-nick"><p>'+fan.nickname+'</p></li>';
	content += '<li class="fan-detail-id"><p>ID:'+fan.pkey+'</p></li>';
	content += '<li class="fan-detail-subtime"><p>'+fan.subscribeTime+'关注</p></li>';
	content += '</ul></li>';
	return content;
}
function getFan(level, fanId) {
	$.ajax({
		url: "resource/member_fan?account.pkey=${account.pkey}",
		type: "post",
		data: {
			level: level,
			fanId: fanId?fanId:undefined,
		},
		dataType: "json",
		success: function(fan) {
			var content = "";
			if(fan) {
				content += toFanHtml(fan);
			}
			$("#list").html(content);
		}
	})
}
function getFans(level) {
	$.ajax({
		url: "resource/member_listFans?account.pkey=${account.pkey}",
		type: "post",
		data: {
			level: level
		},
		dataType: "json",
		success: function(result) {
			var content = "";
			console.log(result)
			if(result instanceof Array) {
				$.each(result, function(index, fan) {
					content += toFanHtml(fan);
				});
			}
			$("#list").html(content);
		}
	})
}
$(function() {
	getFans(1);
	$("#search-btn").click(function() {
		var fanId = $("#idinput").val();
		if(fanId) {
			$(".nav").hide();
			getFan(1, fanId);
		} else {
			$(".nav").show();
			getFans(1);			
		}

	});
	$(".nav li").click(function() {
		$(this).addClass("select").siblings().removeClass("select");
		getFans($(this).index()+1);
	})
})
</script>
</html>