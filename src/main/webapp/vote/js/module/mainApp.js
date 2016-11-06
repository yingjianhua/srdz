var app = angular.module("mainApp", ["ngRoute", "ngSanitize", "configModule", "resourceModule", "angular-carousel"]);
app.config([
            "$routeProvider",
            "$locationProvider", 
            function(r, l) {
            	l.html5Mode(true);
	r.when("/", {name: "home", templateUrl: "partials/home.html", controller: "homeController"})
	r.when("/home", {name: "home", templateUrl: "partials/home.html", controller: "homeController"})
	r.when("/rank", {name: "rank", templateUrl: "partials/rank.html", controller: "rankController"})
	r.when("/entry", {name: "entry", templateUrl: "partials/entry.html", controller: "entryController"})
	r.when("/srdz", {name: "srdz", templateUrl: "partials/srdz.html", controller: "homeController"})
	.otherwise({redirectTo:"/"})
}])
app.run(["$rootScope", "$location", "commonService", "SITE_CONFIG", function(r, $location, commonService, SITE_CONFIG) {
	r.page = {
			showHeader : SITE_CONFIG.SHOW_HEADER,
			showFooter : SITE_CONFIG.SHOW_FOOTER,
			baseUrl : SITE_CONFIG.BASE_URL
	}
	r.banners = commonService.getBanners();
	r.homeTabs= commonService.getMenus();
	r.voteInfo = commonService.getVoteInfo();
	r.home = r.home||{currentTabId : 1};
	r.switchTab=function(t) {
		console.log(t)
		r.home.currentTabId=t.id;
		$location.path(t.url);
		$location.replace();
	}
}])
