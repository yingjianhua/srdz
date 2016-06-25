<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登陆</title>
<link href="<%=request.getContextPath()%>/mvc/login.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/extfile/js/checkCode.js" type="text/javascript"></script>
</head> 
<body>

<div id="container">
<div id="header">
<div class="help_bg"><a title="设为首页" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.fxc.com');" href="javascript:;">设为首页</a>｜ <a href="#">反馈</a>｜ <a href="#">帮助</a></div>
</div>
<div id="login_main">
<form method="post" action="<%=request.getContextPath()%>/sys_SysUser_login" onsubmit="return validateCheck();"/>
<div class="main_center">
<div class="login_box">
<div class="login_err" id="login_err">${sarg1}</div>
  	<table width="309" border="0" cellpadding="0" cellspacing="15" >
    <tr>
      <td width="79" align="right"><span class="yh">用户名：</span></td>
      <td colspan="2" width="100"><input name="bean.loginName" value="${bean.loginName}" type="text" style="width:150px;height:20px;line-height:20px;"/></td>
      </tr>
    <tr>
      <td width="79" align="right"><span class="yh">密&nbsp;&nbsp;&nbsp;码：</span></td>
      <td colspan="2" width="100"><input name="mmCheck" value="${mmCheck}" type="password" style="width:150px;height:20px;line-height:20px;"/></td>
    </tr>
    <tr>
      <td width="79" align="right"><span class="yh">验证码：</span></td>
      <td colspan="2" width="100">
      	<input type="text" id="checkCode" name="checkCode" style="width:95px;height:20px;line-height:20px;"/>
      	<img src="<%=request.getContextPath()%>/servlet/verify.img" style="vertical-align:top;width:53px;height:21px;line-height:21px;"/>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td ><input type="submit" class="goto" value=""/></td>
      <td >&nbsp;&nbsp;&nbsp;</td>
    </tr>
  	</table>
</div>
<div class="box_bg">
Copyright@ irille</br>
IRILLE.COM ALL RIGHTS RESERVED 
</div>
</div>
<div class="main_right"></div>
</div>
</div>
</body>
</html>
