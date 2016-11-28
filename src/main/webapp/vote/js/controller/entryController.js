angular.module("mainApp")
.controller("entryController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	"wxService",
	"$routeParams",
	function(r, s, l, cs, ws, rp) {
		var init = function() {
			s.params = cs.getEntryParams();
			s.tips = "（若在线报名失败，可以将报名信息：";
			s.params.forEach(function(param, index, b, c) {
				if(index==0)
					s.tips+=param.text;
				else
					s.tips=s.tips+"+"+param.text;
			})
			s.tips += " 发到邮箱：huyuanyuan@lexiangapp.cn）";
			r.home.currentTabId = 3;
			ws.config();
		}
		s.entrySubmit = function() {
			var data={};
			console.log("entrySubmit")
			var error;
			s.params.forEach(function(param) {
				if(error)
					return
				if(!param.value) {
					error={field:param.text,reason:"不能为空"};
					return;
				}
				data[param.name]=param.value;
			})
			if(error) {
				r.showMessage(1, error.field+error.reason);
				return
			}
			var entry = cs.entry(data);
			var entrySuccess = function() {
				r.showDetail(entry)
			}
			if(entry.success) {
				r.showMessage(1, "报名成功", entrySuccess);
			} else
				r.showMessage(2, "报名失败");
		}
		init();
	}]
);