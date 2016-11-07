angular.module("mainApp")
.controller("entryController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	"$routeParams",
	function(r, s, l, cs, rp) {
		s.params = cs.getEntryParams();
		s.tips = "（若在线报名失败，可以将报名信息：";
		s.params.forEach(function(param, index, b, c) {
			if(index==0)
				s.tips+=param.text;
			else
				s.tips=s.tips+"+"+param.text;
		})
		s.tips += " 发到邮箱：huyuanyuan@lexiangapp.cn）";
		console.log(s.params)
		r.home.currentTabId = 3;
		s.entrySubmit = function() {
			var data={};
			s.params.forEach(function(param) {
				data[param.name]=param.value;
			})
			var entry = cs.entry(data);
			var entrySuccess = function() {
				r.showDetail(entry)
			}
			if(entry.success) {
				r.showMessage(1, "报名成功", entrySuccess);
			} else
				r.showMessage(2, "报名失败");
		}
	}]
);