<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${vote.name }</title>
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wa/vote/1601/assets/css/style.css">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
${jsCode}
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
</script>
</head>

<body>
<section>
	<%@ include file="banner.jsp"%>
	<%@ include file="vote.jsp"%>
	<!-- <div class="wxhd_news">
		<div class="wxhd_news_list"></div> -->
			<div class="wxhd_news_detail">${vote.des}<br><br><br><br></div>
	<!-- 	</div>
	</div> -->
	</section>
	<%@ include file="menu.jsp"%>

</body>
</html>