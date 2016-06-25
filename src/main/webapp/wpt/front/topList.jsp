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
	<!--footer 底部导航-->
		<jsp:include page="../menu.jsp">
			<jsp:param value="3" name="currMenu"/>
		</jsp:include>
	<!--footer 底部导航-->
	
	<div class="front_bar">
		<div class="frbar_btn">
			<span><a href="javascript:;">${sessionScope.city.name}</a></span>	
		</div>	
		<div class="frbar_btn">
			<span><a href="javascript:;">主题</a></span>
		</div>
	</div>
	<div class="front_zw"></div>
	
	<!--城市下拉-->
	<div class="front_slide">
		<a href="javascript:;" pkey="0" class="cityLineList">全部</a>
		<s:iterator value="areas" id="line" status="st">
		<a href="javascript:;" pkey=${line.pkey} class="cityLineList">${line.name}</a>
		</s:iterator>
		<div class="clear"></div>	
	</div>
	
	<!--主题下拉-->
	<div class="front_slide">
		<a href="javascript:;" pkey="0" class="banquetList">全部</a>
		<s:iterator value="banquets" id="line" status="st">
		<a href="javascript:;" pkey=${line.pkey} class="banquetList">${line.name}</a>
        </s:iterator>		
		<div class="clear"></div>	
	</div>
	<!--主题下拉-->	
	<div class="front_flog"></div>
	<div class="front_list">
		<s:iterator value="tops" id="line" status="st">
			<a href="javascript:;" ur="${line.url}" pkey="${line.pkey}" class="front_item">
			<img src="../${line.imgUrl}" class="photo"/>
			<dl>${line.title}
				<dd>${line.date}</dd>
			</dl>
			<div class="clear"></div>
			</a>
	    </s:iterator>
	</div>
	<div style="width:640px;height:83px"></div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
var default_areaName = "${sessionScope.city.name}";
var default_banquetName = "主题";
var areaId = 0;
var areaName = "全部";
var banquetId = 0;
var banquetName = "主题";
function search() {
	$(".frbar_btn").removeClass("frbar_hover");
	$(".front_flog").hide();
	$(".front_slide").hide();
	$.ajax({
		url:"resource/top_list",
		data : {
			cityId : "${sessionScope.city.pkey}",
			areaId : areaId==0?"":areaId,
			banquetId : banquetId==0?"":banquetId,
			accountId : "${account.pkey}"
		},
		dataType : "json",
		success : function(result) {
			var list = "";
			$.each(result, function(index, top) {
				list += '<a href="javascript:;" ur="'+top.url+'" pkey="'+top.pkey+'" class="front_item">';
				list += '<img src="../'+top.imgUrl+'" class="photo"/>';
				list += '<dl>'+top.title;
				list += '	<dd>'+top.date+'</dd>';
				list += '</dl>';
				list += '<div class="clear"></div>';
			})
			$(".front_list").html(list);
			if(areaId == 0) {
				areaName = default_areaName;
			}
			if(banquetId == 0) {
				banquetName = default_banquetName;
			}
			$(".frbar_btn:eq(1)").html(areaName);
			$(".frbar_btn:eq(2)").html(banquetName);
			$(".front_item").click(function(){
				var pkey = $(this).attr("pkey");
				var ur = $(this).attr("ur");
				if(ur !='')
					location.href=ur;
				else
					location.href = "showTop?id=" + pkey+"&account.pkey="+${account.pkey};
			});
		}
	})
}
$(function(){
	$(".frbar_btn").click(function() {
		if($(this).hasClass("frbar_hover")) {
			$(".front_slide").hide();
			$(".front_flog").hide();	
			$(this).removeClass("frbar_hover");
		} else {
			$(".front_slide").hide();
			$(".front_slide:eq("+$(this).index()+")").show();	
			$(".front_flog").show();	
			$(".frbar_btn").removeClass("frbar_hover");
			$(this).addClass("frbar_hover");
		}
	})
	
	$(".front_flog").on("click",function(){
		$(".frbar_btn").removeClass("frbar_hover");	
		$(".front_flog").hide();	
		$(".front_slide").hide();
	});

	$(".cityLineList").click(function() {
		areaId = $(this).attr("pkey");
		areaName=$(this).html();
		search();
	})
	$(".banquetList").click(function() {
		banquetId = $(this).attr("pkey");
		banquetName = $(this).html();
		search();
	})
	
	$(".front_item").click(function(){
		var pkey = $(this).attr("pkey");
		var ur = $(this).attr("ur");
		if(ur !='')
			location.href=ur;
		else
			location.href = "showTop?id=" + pkey+"&account.pkey="+${account.pkey};
	});
})
</script>
</html>