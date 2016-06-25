<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="zh">
<head>
<title>今天吃什么</title>
<meta name="Keywords" content="寻味温州! ">
<meta name="Description" content="寻味温州! ">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/style.css?v=0.8">
<style type="text/css">
  a:link{
     color:white;
  } 
</style>
<script>
${jsCode}
</script>
</head>

<body>

	<section class="pic-main">
        <div class="pic-list">
        	<ul>
            	<li><img src="wp/fif/assets/img/pic_1.png">
                	<div class="pic-list-about" id="pic-list-about">
                        <p class="tt" id="title1">瓯江边上</p>
                        <p>享食光 · <span id="theme1">享女郎   </span> <i class="icon-map-marker"></i> <span id="bsn1"> P·O CLUB</span></p>
                    </div>
                    <h4># 今天吃什么 #</h4>
                </li>
            </ul>
        </div>
        <div class="pic-menu" id="pic-menu1">
        	<div class="pic-table">
        		<a href="#" id="stop"><img src="wp/fif/assets/img/pic_btn2.png"></a>
            </div>
        </div>
      
        <div class="pic-menu-about" id="pic-menu-about">
                <div class="tt1">
                <a class="url" href="#">
                    <p id="title">瓯江边上</p>
                </a>
                </div>
                <div class="tt2">
                  <a class="url" href="#">  享食光 · <span id="theme">享女郎   </span><i class="icon-map-marker"></i><span id="bsn"> P·O CLUB</span>
                  </a>
                </div>
            </div>
 
        <div class="pic-menu" id="pic-menu2">
            <div class="pic-table">
        		<a href="#" id="start"><img src="wp/fif/assets/img/pic_btn1.png"></a>
            </div>
        </div>
	</section>



</body>
<!-- Grab Google CDN's jQuery. fall back to local if necessary -->
<script src="wp/fif/assets/js/libs/jquery-1.11.0.min.js"></script>

<script src="wp/fif/assets/js/functions.js"></script>
<script>
$(function(){
	var repeat = true;
	var eat;
	$("#start").click(function() {
		$("#pic-menu2").hide();
		$("#pic-menu-about").hide();
		$("#pic-list-about").show();
		$("#pic-menu1").show();
		start();
	});
	
	$("#stop").click(function() {
		$("#pic-menu1").hide();
		$("#pic-menu-about").slideDown(1000);
		$("#pic-list-about").hide();
		$("#pic-menu2").show();
		stop();
	});
	function stop() {
		console.log("before stop")
		if(eat) {
			console.log("stop")
			clearInterval(eat)
		}
	}
	function start() {
		console.log("start")
		eat = setInterval(eat_f, 100)
	}

	function getRandom(num){
		var my_num = Math.floor(Math.random()*num);
		return my_num;
	}
	function eat_f(){
		var jsonList=${sarg1};
	    var index=getRandom(jsonList.length);
		var content=jsonList[index].title;
		var theme=jsonList[index].theme.split("##")[1]+" ";
		var bsn=" "+jsonList[index].bsn.split("##")[1];
		var title=jsonList[index].title;
		var url=jsonList[index].url;
		$("#title1").html(content);
		$("#theme1").html(theme);
		$("#bsn1").html(bsn);
		$("#pic-menu1 p").html(content);
		$("#theme").html(theme);
		$("#bsn").html(bsn);
		$("#title").html(title);
		$(".url").attr("href",url);
		$(".url").css("color","white");
		$(".url").css("text-decoration","none");
	}
});
</script>
</html>
