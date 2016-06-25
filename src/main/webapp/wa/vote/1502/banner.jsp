<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="banner" id="banner">
	<div class="hd">
        <ul></ul>
    </div>
    <div class="bd">
        <ul>
        <s:iterator value="listBanner" var="banner"> <li><a href="${banner.url}"><img class="swiper-slide" src="${banner.picUrl}" des="${banner.description}" /></a></li> </s:iterator>
        </ul>
    </div>
</div>