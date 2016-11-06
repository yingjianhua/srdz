var app = angular.module("mainApp", ["ngRoute", "configModule"]);
app.config([
            "$routeProvider",
            "$locationProvider", 
            function(r, l) {
            	l.html5Mode(true);
	r.when("/", {name: "home", templateUrl: "partials/home.html", controller: "homeController"})
	.otherwise({redirectTo:"/"})
}])
app.run(["$rootScope", "SITE_CONFIG", function(r, SITE_CONFIG) {
	r.page = {
			showFooter : SITE_CONFIG.SHOW_FOOTER
	}
	console.log(r)
	console.log(r.page)
}])
app.run(["$rootScope", function(r) {
	r.aaa = "aaa";
}])
