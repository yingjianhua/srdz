<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<link type="text/css" rel="stylesheet" href="css/css.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<!--这段JS不能删除，且前面最好别再放其它JS-->
<script type="text/javascript">
    var phoneWidth =  parseInt(window.screen.width);
    var phoneScale = phoneWidth/640;
    var ua = navigator.userAgent;
    if (/Android (\d+\.\d+)/.test(ua)){
        var version = parseFloat(RegExp.$1);
        if(version>2.3){
            document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
        }else{
            document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
        }
    } else {
        document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
    }
</script>
<title>享食光-私人定制</title>
</head>

<body class="seller_orderbg">
	
	<div class="slod_bar">
		<div class="slod_search">
			<form action="expand_wpt_WptRestaurant_sellerOrder" method ="post" onsubmit="return onSearch()">
				<input type="submit" class="sub" value="" />
				<input type="search" class="text" name="orderId" placeHolder="请输入订单编号"/>
				<input type="hidden" name="restaurantId" value="${restaurant.pkey}">
				<input type="hidden" name="account.pkey" value="${account.pkey }">
			</form>
		</div>
		<div id="esc">
			<a href="#" class="esc_a">取消</a>
		</div>
		<div class="slod_switch">
			<a>历史订单</a>
		</div>
	</div>
	<div class="slod_zw"></div>
	<div class="slod_list">
	</div>
	
	<div class="cm_flog"></div><!--半透明遮罩层-->
	
	<div class="slod_win">
		<dl>
			<dt><input type="text" class="text" placeHolder="请输入核验码" /></dt>	
			<dd>
				<a href="javascript:;" class="slod_sub">确认用餐</a>	
			</dd>
		</dl>	
	</div>
<jsp:include page="../messagebox.jsp"/>	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">	
function href(){
	location.href = "listSellerOrder?account.pkey=${account.pkey}";
}
function onSearch() {
	var isHistory = $(".slod_switch").hasClass("slod_switch_hover");
	$.ajax({
		url : "resource/seller_listOrder?account.pkey=${account.pkey}",
		type : "post",
		data : {
			restaurantId : $("input[name=restaurantId]").val(),
			orderId : $("input[name=orderId]").val(),
			isHistory: isHistory
		},
		dataType : "json",
		success : function(result) {
			var list = "";
			$.each(result, function(index, order) {
				list += '<div class="slod_item">';
				list += '<dl>';
				list += '		<dt>FUN IN FOOD美食·家-'+order.comboName+'</dt>';
				list += '		<span class="lt">订单编号　'+order.orderId+'</span>';
				list += '		<span class="rt">';
				list += '			<em class="lt">RMB</em>';
				list += '			<i class="price rt">'+order.price+'</i>';
				list += '		</span>';
				list += '</dl>';
				list += '<a href="javascript:;" class="slod_btn'+(isHistory?'':' confirmOrder')+'" pkey="'+order.orderId+'">'+(isHistory?'已完成':'确认用餐')+'</a>';
				list += '<div class="clear"></div>';
				list += '</div>';
			})
			$(".slod_list").html(list);
			//核验码弹窗
			$(".slod_btn").on("click",function(){
				pkey = $(this).attr("pkey");
				$(".slod_win").show();
				$(".cm_flog").show();
			});
		}
	})
	return false;
}
$(function() {
	//点击时旧文字隐藏
	$(".slod_search .text").focus(function(){
		if($(this).attr("placeHolder") == "请输入订单编号"){
			$(this).attr("placeHolder","");	
		}
	});
	
	$(".slod_search .text").blur(function(){
		if($(this).val() == ""){
			$(this).attr("placeHolder","请输入订单编号");	
		}
	});
	$(".slod_switch").click(function() {
		$(this).toggleClass("slod_switch_hover");
		onSearch();
	});
	var pkey;
	//核验码弹窗
	$(".confirmOrder").on("click",function(){
		pkey = $(this).attr("pkey");
		$(".slod_win").show();
		$(".cm_flog").show();
	});
	
	$(".slod_sub").on("click",function(){
	    $.ajax({
	        url:'resource/order_checkCode?account.pkey=${account.pkey}',
	        type:"POST",
	        data:{
	        	"orderId" : pkey,
	        	"checkCode" : $(".slod_win input").val(),
	        },
	        success: function(data) {
	        	if(data == "ok"){
	        		tipbox("核验码正确",href);
	        	}else
	        		tipbox(data);
	        }
	    });
		$(".slod_win").hide();
		$(".cm_flog").hide();		
	});
	
	$("#esc").click(function(){
		$("input[name='orderId']").val("");
	});	
	onSearch();
})
</script>
</html>