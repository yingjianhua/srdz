<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<div id="float_nav">
		<ul class="clearfix">
		<li>
		<s:if test="menu != null && menu['首页']['pkey'] != null">
			<a href="expand_wa_WaActVote_show?pkey=${vote.pkey}" id="float_nav1" style="background:url(<s:if test='%{currPage.line.key == 1}'>${menu['首页']['currImgUrl']}</s:if><s:else>${menu['首页']['imgUrl']}</s:else>) no-repeat top center; background-size:auto 1.8rem;"> ${menu['首页']['name']} </a>
		</s:if>
		<s:else>
			<a href="expand_wa_WaActVote_show?pkey=${vote.pkey}" id="float_nav1" <s:if test="%{currPage.line.key == 1}">class="curr"</s:if>> 首页 </a>
		</s:else>
		</li>
		<li>
		<s:if test="menu != null && menu['排行页']['pkey'] != null">
			<a href="expand_wa_WaActVoteEntry_showList?vote.pkey=${vote.pkey}" id="float_nav1" style="background:url(<s:if test='%{currPage.line.key == 3}'>${menu['排行页']['currImgUrl']}</s:if><s:else>${menu['排行页']['imgUrl']}</s:else>) no-repeat top center; background-size:auto 1.8rem;"> ${menu['排行页']['name']} </a>
		</s:if>
		<s:else>
			<a href="expand_wa_WaActVoteEntry_showList?vote.pkey=${vote.pkey}" id="float_nav2" <s:if test="%{currPage.line.key == 3}">class="curr"</s:if>> 排名 </a>
		</s:else>
		</li>
		<li>
		<s:if test="menu != null && menu['报名页']['pkey'] != null">
			<a href="expand_wa_WaActVoteEntry_show?vote.pkey=${vote.pkey}" id="float_nav1" style="background:url(<s:if test='%{currPage.line.key == 2}'>${menu['报名页']['currImgUrl']}</s:if><s:else>${menu['报名页']['imgUrl']}</s:else>) no-repeat top center; background-size:auto 1.8rem;"> ${menu['报名页']['name']} </a>
		</s:if>
		<s:else>
			<a href="expand_wa_WaActVoteEntry_show?vote.pkey=${vote.pkey}" id="float_nav3" <s:if test="%{currPage.line.key == 2}">class="curr"</s:if>> 报名 </a>
		</s:else>
		</li>
		<li>
		<s:if test="menu != null && menu['自定义页']['pkey'] != null">
			<a href="expand_wa_WaActVote_showCustom?pkey=${vote.pkey}" id="float_nav1" style="background:url(<s:if test='%{currPage.line.key == 99}'>${menu['自定义页']['currImgUrl']}</s:if><s:else>${menu['自定义页']['imgUrl']}</s:else>) no-repeat top center; background-size:auto 1.8rem;"> ${menu['自定义页']['name']} </a>
		</s:if>
		<s:else>
			<a href="expand_wa_WaActVote_showCustom?pkey=${vote.pkey}" id="float_nav4" <s:if test="%{currPage.line.key == 99}">class="curr"</s:if>> 下载享食光 </a>
		</s:else>
		</li>
		</ul>
	</div>
	
	<div id="layer_qrcode">
		<div class="layer_qrcode_cont">
			<div id="layer_qrcode_close"><img src="wa/vote/1501/assets/img/layer_close.png"></div>
			<p class="layer_qrcode_tt">长按下方图片,识别二维码　<br>
进入公众号，点击下方菜单栏进行报名或投票。</p>
			<div id="layer_qrcode_img"><img src="wa/vote/1501/assets/img/qrcode/${account.pkey }.jpg"></div>
		</div>
	</div>
	
	<div id="layer_ok">
		<div class="layer_qrcode_cont">
			<p class="layer_qrcode_tt">--------------------<br>--------------------</p>
			<div id="layer_ok_btn">
				<a href="javascript:void(0);">确定</a>
			</div>
		</div>
	</div>
	
<script src="wa/vote/1501/assets/js/libs/TouchSlide.1.1.js"></script>
<script>
$(function(){
	var _errMsg = '${errMsg}';
	if(_errMsg != '') {
		if(_errMsg == 'qrerr') {
			showQrcode();
		} else {
			showMessage(_errMsg);
		}
	}
});
$(function(){
	$("#layer_qrcode_close").click(function(){
		$("#layer_qrcode").hide();
	});
	$("#layer_ok_btn a").click(function(){
		$("#layer_ok").hide();
	});
});
function showQrcode() {
	$("#layer_qrcode").fadeIn(200);
}
function showMessage(message){
	$("#layer_ok p").html(message);
	$("#layer_ok").fadeIn(200);
}
function showMessageUrl(message, url) {
	$("#layer_ok p").html(message);
	$("#layer_ok").fadeIn(200);
	$("#layer_ok a").attr("href", url);

}
TouchSlide({ 
	slideCell:"#banner",
	titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
	mainCell:".bd ul", 
	effect:"leftLoop", 
	autoPlay:true,//自动播放
	autoPage:true, //自动分页
});
</script>