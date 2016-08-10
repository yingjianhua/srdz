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
<title>享食光</title>
</head>

<body>	
	
	<h2 class="cm_head1">开启你的私人定制之旅</h2>
	
	<div class="service_apply">
		<form action="listRestaurant?account.pkey=${account.pkey }" method="post" onsubmit="return onValid()">
			<div class="sap_in">
				<span class="select_txt">宴会类型</span>
				<select name="banquetId">
					<option value="" selected="true" disabled="true" style="display:none">选择宴会类型</option>
				<s:iterator value="banquets" var="banquet">
					<option value="<s:property value="#banquet.pkey" />"><s:property value="#banquet.name" /></option>
				</s:iterator>
				</select>
			</div>
			<div class="sap_in">
				<input type="text" class="text" placeHolder="参会人数" name="pnum" />	
			</div>
			<div class="sap_in">
				<input type="text" class="text" placeHolder="人均预算" name="perCapitaBudget" />	
			</div>
			<div class="sap_in sap_nb">
				
				<div class="sap_city sap_cityleft lt">
					<span><s:property value="#session.city.name" /></span>	
				</div>
				
				<div class="sap_city rt">
					<span class="select_txt">区域</span>
					<select name="areaId">
						<option value="" selected="true" disabled="true" style="display:none">选择区域</option>
						<s:iterator value="areas" var="area">
							<option value="<s:property value="#area.pkey" />"><s:property value="#area.name" /></option>
						</s:iterator>
					</select>
				</div>
					
			</div>
		</form>	
	</div>
	
	<a href="#" class="cm_btn1">下一步</a>
	
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="" name="currMenu"/>
	</jsp:include>
	<!--footer 底部导航-->
	<jsp:include page="../messagebox.jsp"/>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
	
	//select和date两个特殊情况为了IOS下做的数据同步
	$(".sap_in select").change(function(){
		var _par = $(this).parents(".sap_in");
		$(".select_txt",_par).html($(this).find("option:selected").text()).addClass("select_txth");
	});
	
	$(".dateType").on("click",function(){
		var _par = $(this).parents(".sap_in");
		$(this).css("opacity",1);
		$(".date_txt",_par).hide();
	});
	
</script>
<script>
function onValid() {
	if(!$("form select[name=banquetId]").val()) {
		tipbox("请选择宴会类型")
		return false;		
	} 
	var pnum = $("form input[name=pnum]").val(); 
	if(isNaN(pnum)) {
		tipbox("请输入正确的宴会人数")
		return false;
	}
	var perCapitaBudget = $("form input[name=perCapitaBudget]").val();
	if(isNaN(perCapitaBudget)) {
		tipbox("请输入正确的人均预算");
		return false;		
	}
	if(!$(".sap_city.rt select").val()) {
		tipbox("请选择区域");
		return false;
	}
	return true;
}
</script>
<script>
$(function() {
	$("form select[name=banquetId]").val(null)
	$(".sap_city.rt select").val(null)
	$(".cm_btn1").click(function() {
		$(".service_apply form").submit();
	})
})
</script>
</html>