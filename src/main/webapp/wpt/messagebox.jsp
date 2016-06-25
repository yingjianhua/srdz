<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<div class="cm_flog" style="display:none;"></div><!--遮罩层-->
	<div class="cm_win1 sbb" style="display:none;">
		<div class="tip_txt">提示</div>
		<a href="javascript:;" class="sbtn close">确认</a>
	</div>
	<div class="cm_win1 dbb" style="display:none;">
		<div class="tip_txt">是否确认取消用餐</div>	
		<a href="javascript:;" class="btn close">取消</a>
		<a href="javascript:;" class="btn close">确认</a>
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
var CANCEL = "cancel";
var OK = "ok";
function messagebox(content, callback) {
	$(".dbb .tip_txt").html(content);
	$(".cm_flog").show();
	$(".dbb").show();
	$(".dbb a:eq(0)").click(function() {
		if(callback)
			callback(CANCEL);
		$(".cm_win1").hide();
		$(".cm_flog").hide();
	})
	$(".dbb a:eq(1)").click(function() {
		if(callback)
			callback(OK)
		$(".dbb").hide();
		$(".cm_flog").hide();
	})
}
function tipbox(content, callback) {
	$(".sbb .tip_txt").html(content);
	$(".cm_flog").show();
	$(".sbb").show();
	$(".sbb a:eq(0)").click(function() {
		if(callback) 
			callback(CANCEL);
		$(".sbb").hide();
		$(".cm_flog").hide();
	})
}
function qrbox(account) {
	$(".sbb .tip_txt").html("该功能需要先进行关注<br/>请点击确定跳转");
	$(".cm_flog").show();
	$(".sbb").show();
	$(".sbb a:eq(0)").click(function(){
		window.location.href = "serviceCenter?account.pkey="+account;
	})
}
$(function() {
	if("${errMsg}") {
		if("${errMsg}" == "qrerr") {
			qrbox("${account.pkey}")
		} else {
			tipbox("${errMsg}")
		}
	}
})
</script>