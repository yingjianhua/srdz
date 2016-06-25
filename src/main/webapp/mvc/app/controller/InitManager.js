Ext.define('mvc.controller.InitManager', {
	extend : 'Ext.app.Controller',
	init : function() {
		//兼容ie8及以下浏览器 js数组中的indexof方法。来自https://developer.mozilla.org/en/JavaScript/Reference/Global_Objects/Array/indexOf网
		if (!Array.prototype.indexOf) {
			Array.prototype.indexOf = function(searchElement) {
				"use strict";
				if (this == null) {
					throw new TypeError();
				}
				var t = Object(this);
				var len = t.length >>> 0;
				if (len === 0) {
					return -1;
				}
				var n = 0;
				if (arguments.length > 0) {
					n = Number(arguments[1]);
					if (n != n) {
						n = 0;
					} else if (n != 0 && n != Infinity
							&& n != -Infinity) {
						n = (n > 0 || -1) * Math.floor(Math.abs(n));
					}
				}
				if (n >= len) {
					return -1;
				}
				var k = n >= 0 ? n : Math.max(len - Math.abs(n), 0);
				for (; k < len; k++) {
					if (k in t && t[k] === searchElement) {
						return k;
					}
				}
				return -1;
			};
		}
		
		//初始化参数列表，将所有参数放入js缓存
    	var url = base_path+'/sys_SysCtype_ctypeNode';
		Ext.create('mvc.tools.ajax').view(url,null,function(resp){
			Ext.state.Manager.set('appAll_sys_ctype',resp);
		});
		
		
		//-----------------form的验证
		Ext.apply(Ext.form.VTypes, { 
       			 mobile:function (value, field) { 
       			 return /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/.test(value); 
       			 }, 
       			 mobileText:'电话格式不正确' 
		 });
		
	},
	onLaunch : function() {}
});
