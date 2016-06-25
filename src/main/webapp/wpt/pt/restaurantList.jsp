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
<script>
${jsCode}
</script>
<title>享食光-私人定制</title>
</head>

<body>	
	<div class="hall_top">超值私人定制套餐任选！选择套餐更划算哦！</div>
	
	<s:iterator value="mapCombo" id="restaurant">
	<div class="hall_item">
	<a href="#" class="hall_item1" pkey="<s:property value="key.pkey" />" combos="<s:iterator value="value" id="combo" status="st"><s:property value="#combo" /><s:if test="!#st.last">,</s:if></s:iterator>">
		<img src="../<s:property value="key.imgUrl" />" class="photo" />
		<div class="item_flog"></div>
	</a>
		<dl class="addr" latitude="${key.coordinate}" longitude="${key.longitude}" name="${key.name}" address="${key.addr}">
		<dt><s:property value="key.name" /></dt>
		<dd><s:property value="key.addr" /></dd>
		</dl>
	</div>
	</s:iterator>
	
	<div class="hall_bar">
		<a href="#" class="go">不选套餐，直接私人定制</a>	
	</div>
	<div class="hall_zw"></div>
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
$(function() {
	$(".hall_item1").click(function() {
		var url = "showRestaurant?id="+$(this).attr("pkey")+"&scombos="+$(this).attr("combos")+"&account.pkey=${account.pkey}";
		location.href = url;
	})
	$(".go").click(function() {
		location.href = "selectService?account.pkey=${account.pkey }&banquetId=${banquetId}&pnum=${pnum}&perCapitaBudget=${perCapitaBudget}&areaId=${areaId}";
	})
})
</script>
</html>