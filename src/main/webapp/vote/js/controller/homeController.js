angular.module("mainApp")
.controller("homeController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	function(r, s, l, c) {
		console.log("homeController")
		r.home.currentTabId = 1;
		console.log("homeController2")
		s.des = c.getHomeDes();
	}]
);