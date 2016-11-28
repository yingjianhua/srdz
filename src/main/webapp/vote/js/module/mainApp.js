var app = angular.module("mainApp", ["ngRoute", "ngSanitize", "configModule", "resourceModule", "angular-carousel", "wu.masonry"]);
app.config([
            "$routeProvider",
            "$locationProvider", 
            function(r, l) {
            	l.html5Mode(true);
	r.when("/:voteId", {name: "home", templateUrl: "partials/home.html", controller: "homeController"})
	.when("/:voteId/home", {name: "home", templateUrl: "partials/home.html", controller: "homeController"})
	.when("/:voteId/rank", {name: "rank", templateUrl: "partials/rank.html", controller: "rankController"})
	.when("/:voteId/entry", {name: "entry", templateUrl: "partials/entry.html", controller: "entryController"})
	.when("/:voteId/detail/:playerId", {name: "detail", templateUrl: "partials/detail.html", controller: "detailController"})
	.when("/:voteId/srdz", {name: "srdz", templateUrl: "partials/srdz.html", controller: "homeController"})
	.when("/:voteId/404",{name:"404",templateUrl:"partials/404.html", controller:"404Controller"})
	.otherwise({redirectTo:"/0/404"})
}])
app.run(["$rootScope", "$location", "commonService", "SITE_CONFIG", function(r, $location, commonService, SITE_CONFIG) {
	var g=document.createElement("script");
	g.src="//res.wx.qq.com/open/js/jweixin-1.0.0.js",
	g.async=!1;
	var f=document.getElementsByTagName("script")[0];
	f.parentNode.insertBefore(g,f);
	
	r.page = {
			showHeader : SITE_CONFIG.SHOW_HEADER,
			showFooter : SITE_CONFIG.SHOW_FOOTER,
			baseUrl : SITE_CONFIG.BASE_URL
	}
	r.message = {show:false, type:1, text:""}; 
	r.showDetail = function(entry) {
		$location.path("/"+r.voteId+"/detail/"+entry.id)
	}
	r.showMessage = function(type, text, callback) {
		r.message.show=true;
		r.message.type=type;
		r.message.text=text;
		r.message.callback=callback;
	}
	r.closeMessage = function() {
		r.message.show = false;
	}
	r.$on("$routeChangeStart", function(evt, next, previous) {
		if(!r.voteId&&next.params.voteId) {
			r.voteId = next.params.voteId;
			r.actionInfo = commonService.getActionInfo(r.voteId);
		}
		if(!r.homeTabs&&(menus=commonService.getMenus())) {
			menus.forEach(function(menu) {
				if(menu.type==1)
					menu.url = "/"+r.voteId+menu.url;	
			})
			r.homeTabs=menus;
		}
	})
	console.log($location)
	r.banners = commonService.getBanners();
	r.voteInfo = commonService.getVoteInfo();
	r.home = r.home||{currentTabId : 1};
	r.switchTab=function(t) {
		console.log(t)
		r.home.currentTabId=t.id;
		$location.path(t.url);
		$location.replace();
	}
}])
