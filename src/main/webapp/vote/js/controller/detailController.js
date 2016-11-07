angular.module("mainApp")
.controller("detailController", [
	"$rootScope",
	"$scope",
	"commonService",
	"$routeParams",
	function(r,s,cs,rp) {
		r.home.currentTabId = 2;
		s.detail = cs.getDetail(rp.playerId);
		s.signUp = function() {
			r.switchTab(r.homeTabs[2]);
		};
		s.vote = function(){
			var detail = cs.vote(s.detail.id);
			detail.success&&(s.detail.count=detail.count,s.detail.rank=detail.rank)
		};
	}]
);