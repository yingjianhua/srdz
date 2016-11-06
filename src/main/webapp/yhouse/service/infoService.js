var resource = angular.module("resourceModule");
resource.factory("infoService",["$http","SITE_CONFIG",function(e,t){
	var o=t.API_URL;
	return{
		experience:function(t){return e({method:"GET",url:o+"m/player/item-v2.3/"+t+"?from=h5"})},
		guide:function(t){return e({method:"GET",url:o+"m/guide/item-v2.3/"+t+"?from=h5"})},
		news:function(t){return e({method:"GET",url:o+"m/life/item-v2.3/"+t+"?from=h5"})},
		getSpecialTopics:function(){return e({method:"GET",url:o+"config/specialTopics.m"})},
		topic:function(t,n,a){return e({method:"GET",url:o+"m/topic/item-v2.6/"+t+"?from=h5",params:{pos:n,coordType:a}})},
		host:function(t,n,a){return e({method:"GET",url:o+"m/host/item-v2.8/"+t+"?from=h5",params:{coordType:n,pos:a}})},
		promoGetTopic:function(t){return e({method:"GET",url:o+"m/topic/list",params:{cityId:t?t:null,from:"h5",page:1,pageSize:3}})},
		lincolnAd:function(t){return e({method:"POST",url:o+"zap/leads",params:t})}
		}
	}]
)