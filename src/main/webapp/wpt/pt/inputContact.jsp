<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<body>	
	<div class="contact_way_container">
	<h2 class="cm_head1">输入您的联系方式</h2>
	
	<div class="contact_way">
		<form action="confirmOrder" method="post" onsubmit="return toValid()">
		<div class="ctw_in">
			<span class="lt">姓名</span>
			<div class="rt">
				<input type="text" class="text lt" placeHolder="如：张小凡" name="contactMan" />
				<a href="javascript:;" class="sex_sel sex_nv hover" val="2"></a>
				<a href="javascript:;" class="sex_sel sex_nan" val="1"></a>
			</div>
			<input type="hidden" class="sex_val" value="2" name="contactSex" />
		</div>
		
		<div class="ctw_in">
			<span class="lt">用餐时间</span>
			<input type="hidden" class="text rt date_val" value="20150101" name="date" />
			<a href="javascript:;" class="text rt date_val" style="color:#757575">请选择</a>
		</div>
		
		<div class="ctw_in">
			<span class="lt">联系方式</span>
			<input type="text" class="text rt" placeHolder="如：136-0557-6245" name="contactWay" />
		</div>
		
		<div class="ctw_in">
			<span class="lt">希望的联系方式</span>
			<div class="rt">
				<a href="javascript:;" class="xw_way xw_tel nhover" val="0"></a>	
				<a href="javascript:;" class="xw_way xw_wx" val="1"></a>
				<a href="javascript:;" class="xw_way wx_qq" val="2"></a>
			</div>
			<input type="hidden" class="xw_val" value="0" name="contactType" />
		</div>
		
		<div class="ctw_in">
			<span class="lt">备注</span>
			<input type="text" class="text rt" placeHolder="请输入忌口食品，特殊要求等" name="rem" />
		</div>
		<s:token></s:token>
		</form>
	</div>
	
	<a href="#" class="cm_btn1">下一步</a>
	</div>
	<%@ include file="tselTime.jsp"%>
	<%@ include file="../messagebox.jsp"%>
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
function toValid() {
	var date = $("form input[name=date]").val();
	if(date == "") { tipbox("请选择日期"); return false;}
	var name = $("form input[name=contactMan]").val();
	if(name == "") { tipbox("请输入姓名"); return false;}
	if(name.length>10) { tipbox("联系姓名不能超过十个字"); return false;}
	var contact = $("form input[name=contactWay]").val();
	if(contact == "") { tipbox("请输入联系方式"); return false;}
	else{
		var phone = /^((13[0-9])|(147)|(15[0-3,5-9])|(171)|(17[6-8])|(18[0-3,5-9]))\d{8}$/;
		var wx = /^[a-zA-Z\d_-]{5,}$/;
		var qq = /^[1-9]\d{4,12}$/;
		var type = $("form input[name=contactType]").val();
		if(type == 0 && !phone.test(contact)){
			tipbox("电话格式不正确");
			return false;
		}
		if(type == 1 && !wx.test(contact)){
			tipbox("微信格式不正确");
			return false;
		}
		if(type == 2 && !qq.test(contact)){
			tipbox("qq格式不正确");
			return false;
		}
	}
	var rem = $("form textarea[name=rem]").val();
	return true;
}
	
</script>
<script>
var submited = false;
$(function() {
	$("form input[name=date]").val("");
	//性别的选择
	$(".sex_sel").on("click",function(){
		$(this).addClass("hover").siblings().removeClass("hover");
		$(".sex_val").val($(this).attr("val"));
	});
	
	//希望联系方式的选择
	$(".xw_way").on("click",function(){
		$(this).addClass("nhover").siblings().removeClass("nhover");
		$(".xw_val").val($(this).attr("val"));
	});
	//日期的选择
	$(".date_val").next().on("click", function() {
		$(".time_sel_container").show();
		$(".contact_way_container").hide();
	})
	//表单提交
	$(".contact_way_container .cm_btn1").click(function() {
		if(submited) {
			tipbox("此订单已提交,请勿重复提交")
			return;
		}
		if(!toValid() ) return;
		submited = true;
		$.ajax({
			url : "resource/order_confirmOrder?account.pkey=${account.pkey}",
			type : "POST",
			dataType : "json",
			data : {
				"contactMan" : $("form input[name=contactMan]").val(),
				"contactSex" : $("form input[name=contactSex]").val(),
				"date" : $("form input[name=date]").val(),
				"contactWay" : $("form input[name=contactWay]").val(),
				"contactType" : $("form input[name=contactType]").val(),
				"rem" : $("form input[name=rem]").val(), 
				"comboId" : "${comboId}",
				"banquetId" : "${banquetId}",
				"pnum" : "${pnum}",
				"perCapitaBudget" : "${perCapitaBudget}",
				"areaId" : "${areaId}",
				"services" : "${services}",
				"struts.token.name" : "struts.token", 
				"struts.token" : $("form input[name='struts.token']").val() 
			},
			success : function(result, data2) {
				if(result.success) {
					location.href = "confirmOrder?orderId=" + result.orderId +"&account.pkey=${account.pkey}";						
				} else {
					if(result.errMsg) {
						if(result.errMsg == "qrerr") {
							qrbox("${account.pkey}");
						} else {
							tipbox(result.errMsg)
						}
					}
					submit = false;
				}
			}
		})
	})
})
</script>
</html>