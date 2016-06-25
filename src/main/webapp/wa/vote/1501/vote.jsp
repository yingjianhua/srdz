<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div class="wxhd_nav">
		<ul class="clearfix">
			<li><a href="javascript:void(0)">
					<p class="wxhd_nav_tt1">已报名</p>
					<p class="wxhd_nav_tt2">
						<span>${entrycount}</span>
					</p>
			</a></li>
			<li><a href="javascript:void(0)">
					<p class="wxhd_nav_tt1">投票数</p>
					<p class="wxhd_nav_tt2">
						<span>${votecount}</span>
					</p>
			</a></li>
			<li><a href="javascript:void(0)">
					<p class="wxhd_nav_tt1">阅读量</p>
					<p class="wxhd_nav_tt2">
						<span>${visitcount}</span>
					</p>
			</a></li>
		</ul>
	</div>