Ext.define('mvc.tools.ajax',{
	ajax: function(url,params,sucFun,wait,async){
		if(typeof params != 'object') params = Ext.decode(params);
		if(wait){
			wait = Ext.MessageBox.wait(wait_msg, msg_title, {
				interval : 100
			});
		}
		if(async == null){
			async = true;
		}
		Ext.Ajax.request({
			method: 'POST',
			url: url,
			async: async,//添加该属性即可同步
			params: params,
			timeout : 15000,
			success: function(resp,opts){
				if(wait){
					wait.close();
				}
				var res = resp.responseText;
				if(res){
					res = Ext.decode(res);
					if(res.info){
						Ext.example.msg(msg_title,res.info);
					}
				}
				if (sucFun) {
					sucFun(res);
				}
			},
			failure: function(resp,opts){
				if(wait){
					wait.close();
				}
				try{
					var res = resp.responseText;
					if(res){
						res = Ext.decode(res);
						if(res.error){
							Ext.example.msg(msg_title,res.error);
						}
					}
				}catch(e){
					Ext.example.msg(msg_title,e);
				}
			}
		});
	},
	update : function(url, params, sucFun, async) {
		this.ajax(url, params, sucFun, true, async);
	},
	view : function(url, params, sucFun, async){
		this.ajax(url, params, sucFun, false, async);
	}
});