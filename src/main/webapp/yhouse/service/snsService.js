var resource = angular.module("resourceModule");
resource.factory("snsService",["$http","SITE_CONFIG",function(e,t){
	var o=t.API_URL,n=t.SAPI_URL;
	return{
		article:function(t){return e({method:"GET",url:n+"m/sns/share/getShareArticleById?from=h5&limitNum=10",params:{id:t}})},
		shareTagById:function(t){return e({method:"GET",url:n+"m/sns/share/getShareTagById?from=h5&limitNum=6",params:{id:t}})},
		shareTagByName:function(t){return e({method:"GET",url:n+"m/sns/share/getShareTagByName",params:{tagName:t}})},
		shareTagNew:function(t,o,a,r,i){return e({method:"GET",url:n+"m/sns/share/getShareTagContentList",params:{tagId:t,sortType:o,page:a?a:1,pageSize:r?r:6,pid:i}})},
		webcastStat:function(t){return e({method:"GET",url:n+"m/sns/share/getShareWebcastStatById?from=h5",params:{id:t}})},
		webcastLive:function(t){return e({method:"GET",url:n+"m/sns/share/getShareWebcastById?from=h5",params:{id:t}})},
		attendShareWebcast:function(t){return e({method:"GET",url:n+"m/sns/share/attendShareWebcast?from=h5",params:{id:t}})},
		webcastVideo:function(t){return e({method:"GET",url:n+"m/sns/share/getShareVideoById?from=h5",params:{id:t}})},
		webcastPreview:function(t,o){return e({method:"GET",url:n+"m/sns/share/getShareWebcastByChatId?from=h5",params:{chatId:t,pushUserId:o}})},
		webcastGetToken:function(){return e({method:"GET",url:n+"m/sns/user/getImToken"})},
		webcastForenoticeList:function(){return e({method:"GET",url:n+"m/sns/share/getWebcastForenoticeListByH5?from=h5"})},
		getEasemobUser:function(){return e({method:"GET",url:n+"m/message/getEasemobUser"})},
		getSnsCampaignUserRank:function(t,o){return e({method:"GET",url:n+"m/sns/share/getSnsCampaignUserRank",params:{pageSize:t,page:o}})},
		getSnsCampaignRestaurantRank:function(t,o,a){return e({method:"GET",url:n+"m/sns/share/getSnsCampaignRestaurantRank",params:{pageSize:t,page:o,restaurantId:a}})},
		getUpToken:function(t){return e({method:"GET",url:o+"m/getUpToken",params:{userId:t}})},
		saveShareArticle:function(t,o,a,r){return e({method:"POST",url:n+"m/sns/share/saveShareArticle",params:{picUrls:t,longitude:o,latitude:a,description:r}})},
		forenoticeRemind:function(t,o){return e({method:"GET",url:n+"m/sns/share/forenoticeRemind",params:{forenoticeId:t,isRemind:o}})},
		likeShare:function(t,o){return e({method:"GET",url:n+"m/sns/share/likeShare",params:{isLike:t,shareId:o}})}
		}
	}]
)