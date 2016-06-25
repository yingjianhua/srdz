/**
 * 
 */
//7 imgList mediaIds imgs mediaIds imgForm imgCount i length
/** Begin uploadSuccess **/
 function(res) {
				var img = imgList[mediaIds.length];
				i++;
				var delImg = img.children(".del");
				var media = $('<input type="hidden" name="mediaIds" />');
				$("#imgForm").append(media);
				media.val(res.serverId);
				mediaIds.push(media);// 赋值mediaId到hidden控件用于后台下载
				img.empty() //删除表示上传进度条的div
				var delImg = $('<img class="del" style="border:none;" src="images/delete.png" />'); 
				var margin = img.css("width");
				margin = margin.substring(0, margin.length-2);
				//delImg.attr("style", "border:none;");
				delImg.css("height", Math.ceil(parseInt((margin*3)/10)) + "px");
				delImg.css("width", Math.ceil(parseInt((margin*3)/10)) + "px");
				margin = Math.ceil(parseInt(margin) - Math.ceil(parseInt((margin*3)/10)));
				delImg.css("margin-left", margin + "px");
				img.append(delImg);
				$(delImg).click(function() {
					imgClick(img, media);
				});
				imgCount--;
				if (i < length)
					upload(i, length, imgList);
 				}
/** End uploadSuccess **/
/** Begin chkApi **/
 function(res) {
	 			var errMsg = res.errMsg.substring(11);
	 			if (errMsg != "ok") {
	 				$("#layer_ok p").html("您的微信版本可能不支持部分功能，<br>请升级微信客户端！");
	 				$("#layer_ok").fadeIn(200);
	 			} else {
	 				if(typeof checkJsApiSuccess == "function") {
	 					checkJsApiSuccess();
	 				}
	 			}
			}
/** End chkApi **/
