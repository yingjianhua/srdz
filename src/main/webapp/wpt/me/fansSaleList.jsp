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
<link type="text/css" rel="stylesheet" href="css/me_fansSaleList.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>享食光-私人定制</title>
</head>
<body>
	<div class="main">
		<div class="amount">
			<div class="amount-commission">
				<dt>累计奖励(元)</dt>
				<dd>￥${historyCommission }</dd>
			</div>
			<div class="amount-sale">
				<dt>粉丝销量(元)</dt>
				<dd>￥${saleAmount }</dd>
			</div>
		</div>
		<div class="search-bar" style="display: none;">
			<div class="search">
				<img class="search_icon" src="images/search.png">
				<input class="search-input" type="text" placeholder="输入订单号、粉丝ID" id="idinput"></input>
				<div class="search-submit" id=search-btn>搜索</div>
			</div>
		</div>
		<div class="list">
			<ul id="list">
			</ul>
		</div>
	</div>
	<div class="bg" style="display: none;">
		<img alt="" src="">
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script>
${jsCode}
</script>
<script>
function getFans(condition) {
	$.ajax({
		url: "resource/user_fanOrders?account.pkey=${account.pkey}",
		type: "post",
		data: {
			userid: "${sessionScope.openid}",
			orderOrFan: condition?condition:undefined,
		},
		dataType: "json",
		success: function(result) {
			var content = "";
			if(result.length == 0) {
				$(".search-bar").hide();
				$(".bg").show();
				$("body").addClass("gray");
			} else {
				$(".search-bar").show();
				$("body").removeClass("gray");
			}
			$.each(result, function(index, journal) {
				content += '<li class="order">';
				content += '<dt>';
				content += '<p class="orderId">订单编号：'+journal.orderid+'</p>';
				content += '<p class="createTime">'+journal.createTime+'</p>';
				content += '</dt>';
				content += '<dd>';
				content += '<img class="head" src="'+journal.head+'">';
				content += '<p class="nick">'+journal.nick+'</p>';
				content += '<p class="userId">ID:'+journal.fanid+'</p>';
				content += '<p class="orderStatus">已完成</p>';
				content += '<p class="price">￥'+journal.commission+'</p>';
				content += '</dd>';
				content += '</li>';
			});
			$("#list").html(content);
		}
	})
}
$(function() {
	getFans();
	$("#search-btn").click(function() {
		var condition = $("#idinput").val();
		getFans(condition);
	});
})
</script>
</html>