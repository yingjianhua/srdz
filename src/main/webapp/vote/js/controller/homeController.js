angular.module("mainApp")
.controller("homeController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	"$sce",
	function(r, s, l, c, $sce) {
		console.log("homeController")
		r.home.currentTabId = 1;
		console.log("homeController2")
		s.des = $sce.trustAsHtml(c.getHomeDes());
		console.log(s.des)
	}]
);