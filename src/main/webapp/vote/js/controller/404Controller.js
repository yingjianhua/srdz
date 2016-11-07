angular.module("mainApp")
.controller("404Controller", [
	"$rootScope", 
	function(r) {
		delete r.homeTabs;
		delete r.banners;
		delete r.voteInfo;
	}]
);