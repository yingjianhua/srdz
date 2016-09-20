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
<title>享食光</title>
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
		<div class="search-bar">
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
		<img alt="" src="images/emptyJ.png">
		<p>粉丝还没有腐败过</p>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script>
${jsCode}
</script>
<script>
var amount_sale = Number.parseFloat($(".amount-sale dd").text().substring(1));
if(amount_sale == 0) {
	$(".bg").show();
	$("body").addClass("gray");
	$(".search-bar").hide();
}
function getFans(condition) {
	$.ajax({
		url: "resource/member_fanOrders?account.pkey=${account.pkey}",
		type: "post",
		data: {
			userid: "${sessionScope.openid}",
			orderOrFan: condition?condition:undefined,
		},
		dataType: "json",
		success: function(result) {
			var content = "";
			$.each(result, function(index, journal) {
				content += '<li class="order">';
				content += '<dt>';
				content += '<p class="orderId">订单编号：'+journal.orderid+'</p>';
				content += '<p class="createTime">'+journal.createTime+'</p>';
				content += '</dt>';
				content += '<dd>';
				content += '<img class="head" src="'+journal.imageUrl+'">';
				content += '<p class="nick">'+journal.nickname+'</p>';
				content += '<p class="userId">ID:'+journal.fan.pkey+'</p>';
				content += '<p class="orderStatus">'+journal.status+'</p>';
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