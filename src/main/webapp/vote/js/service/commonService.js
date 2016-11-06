angular.module("resourceModule")
.factory("commonService", ["$http", function(h) {
	return {
		getBanners:function() {
			return [{"id":1, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-1.jpg", "title":"享食光，一个优质的生活美食馆", "sort":1},
			        {"id":2, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-2.jpg", "title":"寻找最美精油代言人选秀大赛", "sort":2},
			        {"id":3, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-3.jpg", "title":"杭州鑫航影视传媒有限公司", "sort":3},
			        {"id":4, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-4.jpg", "title":"一百年不许变，家和家天下", "sort":4},
			        {"id":5, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-5.jpg", "title":"健康的精油才是好精油", "sort":5}]
		},
		getMenus:function() {
			return [{id:1, name:"首頁", icon:"float_nav1", url:"/home/"},
		            {id:2, name:"排名", icon:"float_nav2", url:"/rank/"},
		            {id:3, name:"投票", icon:"float_nav3", url:"/entry/"},
		            {id:4, name:"享食光", icon:"float_nav4", url:"/srdz/"}];
		},
		getHomeDes:function() {
			return '<img class="lazy-img" src="http://www.51xiangshiguang.com/wx/uploads/20161105/Sys/1151478316358381.jpg">\
					<img class="lazy-img" src="http://www.51xiangshiguang.com/wx/uploads/20161103/Sys/1131478142350222.jpg">\
					<img class="lazy-img" src="http://www.51xiangshiguang.com/wx/uploads/20161104/Sys/1141478223553516.jpg">\
					<img class="lazy-img" y-lazy-src="http://www.51xiangshiguang.com/wx/uploads/20161104/Sys/1141478223553516.jpg">';
		},
		getVoteInfo:function() {
			return {
				entrycount: 80,
				votecount:1000,
				visitcount:12310
			}
		},
		getEntryList:function(page) {
			var data = [{"id":1, "number":1, "record":10,"picUrl":"http://lorempixel.com/400/600/abstract"},
					       {"id":2, "number":2, "record":10,"picUrl":"http://lorempixel.com/400/300/technics"},
					       {"id":3, "number":3, "record":10,"picUrl":"http://lorempixel.com/400/500/sports"},
					       {"id":4, "number":4, "record":10,"picUrl":"http://lorempixel.com/400/300/nightlife"},
					       {"id":5, "number":5, "record":10,"picUrl":"http://lorempixel.com/450/700/city"},
					       {"id":6, "number":6, "record":10,"picUrl":"http://lorempixel.com/410/350/sports"},
					       {"id":7, "number":7, "record":10,"picUrl":"http://lorempixel.com/400/300/nature"},
					       {"id":8, "number":8, "record":10,"picUrl":"http://lorempixel.com/400/300/people"},
					       {"id":9, "number":9, "record":10,"picUrl":"http://lorempixel.com/400/300/transport"},
					       {"id":10, "number":10, "record":10,"picUrl":"http://lorempixel.com/480/300/technics"},
					       {"id":11, "number":11, "record":10,"picUrl":"http://lorempixel.com/450/300/fashion"},
					       {"id":12, "number":12, "record":10,"picUrl":"http://lorempixel.com/420/300/abstract"}];
			var limit = 3;
			return {
				total:data.length,
				items:data.slice((page-1)*limit, page*limit)
			}
		}
	}
}])