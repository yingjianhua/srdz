/**
 * 
 */
/** Begin GErr **/
//alert(res);
/** End GErr **/

//localIds imgs choseLI imgList imgCount chooseImage
/** Begin crtImgs **/
localIds = res.localIds;  
var i = 0;
var length = localIds.length;
for (var j = 0; j < length; j++) {
	var img = $('<li class="imgs"></li>');
	var uploading = $('<div class="floatingCirclesG"><div class="f_circleG frotateG_01"></div><div class="f_circleG frotateG_02"></div><div class="f_circleG frotateG_03"></div><div class="f_circleG frotateG_04"></div><div class="f_circleG frotateG_05"></div><div class="f_circleG frotateG_06"></div><div class="f_circleG frotateG_07"></div><div class="f_circleG frotateG_08"></div></div>');
	img.css('background-image', "url("+ localIds[j] +")");
	img.append(uploading);
	$('#choseLI').before(img);
	img.css("height",img.css("width"));
	img.css("margin-bottom", "3px");
	var margin = img.css("width");
	margin = margin.substring(0,margin.length-2);
	margin = Math.ceil((parseInt(margin)-50)/2);
	margin = margin+"px";
	uploading.css("margin-top", margin);
	uploading.css("margin-left", margin);
	imgList.push(img);
	if(length == imgCount){
		$('#chooseImage').parent().css('display', 'none');
	}
}
upload(i, length, imgList);
/** End crtImgs **/

/** Begin imgClick **/
img.remove();
input.remove();
imgList.splice(jQuery.inArray(img, imgList), 1); 
mediaIds.splice(jQuery.inArray(input, mediaIds), 1);
imgCount++;
$('#chooseImage').parent().css('display', 'block');
/** End imgClick **/
