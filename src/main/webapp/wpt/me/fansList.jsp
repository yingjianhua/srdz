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
<title>享食光-私人定制</title>
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
				<li>粉丝圈（${fans2Num }）</li>
				<li>粉丝圈（${fans3Num }）</li>
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
function getFans(level, fanid) {
	console.log(fanid)
	$.ajax({
		url: "resource/user_fans?account.pkey=${account.pkey}",
		type: "post",
		data: {
			userid: "${sessionScope.openid}",
			level: level,
			fanid: fanid?fanid:undefined,
		},
		datatype: "json",
		success: function(result) {
			var content = "";
			$.each(result, function(index, fan) {
				content += '<li class="fan"><ul>';
				content += '<li class="fan-detail-head"><img src="'+fan.head+'"></li>';
				content += '<li class="fan-detail-nick"><p>'+fan.nick+'</p></li>';
				content += '<li class="fan-detail-id"><p>ID:'+fan.id+'</p></li>';
				content += '<li class="fan-detail-subtime"><p>'+fan.subtime+'关注</p></li>';
				content += '</ul></li>';
			});
			$("#list").html(content);
		}
	})
}
$(function() {
	getFans(1);
	$("#search-btn").click(function() {
		var fanid = $("#idinput").val();
		if(fanid) {
			$(".nav").hide();
			getFans(undefined, fanid);
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