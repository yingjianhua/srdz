var resource =  angular.module("resourceModule");
resource.factory("specialService",["yHttp","SITE_CONFIG",function(e,t){
	return{
		deviceIdAndSign:function(t,o,n){e.get("m/user/deviceIdAndSign",null,t,o,n)},
		verifyCode:function(t,o,n,a){e.get("m/smscode/"+t[0],null,o,n,a)},
		recruit:function(t,o,n,a){e.get("m/player/enlist",{nickname:t[0],weixin:t[1],email:t[2],interest:t[3]},o,n,a)},
		ads:function(o,n,a,r){
			e.get("rocket/launch?siteId="+t.SITE_CODE,{
				type:o[0],cityId:o[1],catalogId:o[2],refresh:o[3]},n,a,r)},
		topicList:function(t,o,n,a){e.get("m/topic/list?from=h5",{hasPromocode:t[0],cityId:t[1],catalogId:t[2],page:t[3],pageSize:t[4]},o,n,a)}
		}
	}]
)