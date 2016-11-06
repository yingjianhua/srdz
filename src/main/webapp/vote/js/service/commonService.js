angular.module("resourceModule")
.factory("commonService", ["$http", function(h) {
	return {
		getBanners:function() {
			return [{"id":1, "linkUrl":"", "picUrl":"http://localhost:8080/srdz/vote/img/banner-1.jpg", "title":"享食光，一个优质的生活美食馆", "sort":1},
			        {"id":2, "linkUrl":"", "picUrl":"http://localhost:8080/srdz/vote/img/banner-2.jpg", "title":"寻找最美精油代言人选秀大赛", "sort":2},
			        {"id":3, "linkUrl":"", "picUrl":"http://localhost:8080/srdz/vote/img/banner-3.jpg", "title":"杭州鑫航影视传媒有限公司", "sort":3},
			        {"id":4, "linkUrl":"", "picUrl":"http://localhost:8080/srdz/vote/img/banner-4.jpg", "title":"一百年不许变，家和家天下", "sort":4},
			        {"id":5, "linkUrl":"", "picUrl":"http://localhost:8080/srdz/vote/img/banner-5.jpg", "title":"健康的精油才是好精油", "sort":5}]
		},
		getMenus:function() {
			return [{id:1, name:"首頁", icon:"float_nav1", url:"/home/"},
		            {id:2, name:"排名", icon:"float_nav2", url:"/rank/"},
		            {id:3, name:"投票", icon:"float_nav3", url:"/entry/"},
		            {id:4, name:"享食光", icon:"float_nav4", url:"/srdz/"}];
		},
		getHomeDes:function() {
			return '<img class="lazy-img" src="http://localhost:8080/srdz/vote/img/banner-1.jpg"/>\
			<img class="lazy-img" y-lazy-src="http://localhost:8080/srdz/vote/img/banner-2.jpg"/>\
			<img class="lazy-img" src="http://localhost:8080/srdz/vote/img/banner-3.jpg"/>\
			<img class="lazy-img" src="http://localhost:8080/srdz/vote/img/banner-4.jpg"/>\
			<img class="lazy-img" src="http://localhost:8080/srdz/vote/img/banner-5.jpg"/>';
		},
		getVoteInfo:function() {
			return {
				entrycount: 80,
				votecount:1000,
				visitcount:12310
			}
		}
	}
}])