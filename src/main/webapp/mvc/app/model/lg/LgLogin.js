Ext.define('mvc.model.lg.LgLogin', {
	extend : 'Ext.data.Model',
	idProperty : 'bean.pkey',
	proxy : {
		type : 'ajax',
		url : base_path+'/lg_LgLogin_load'
	},
	fields : [
		{name : 'bean.pkey' , mapping : 'pkey' , type : 'int', useNull : true},
		{name : 'bean.loginTime' , mapping : 'loginTime' , type : 'date' ,dateFormat : 'Y-m-d H:i:s'},
		{name : 'bean.userSys' , mapping : 'userSys' , type : 'string', outkey : true},
		{name : 'bean.client' , mapping : 'client' , type : 'int', useNull : true},
		{name : 'bean.ip' , mapping : 'ip' , type : 'string'},
		{name : 'bean.system' , mapping : 'system' , type : 'string'},
		{name : 'bean.browser' , mapping : 'browser' , type : 'string'},
		{name : 'bean.logoutTime' , mapping : 'logoutTime' , type : 'date' ,dateFormat : 'Y-m-d H:i:s'},
		{name : 'bean.countProcTime' , mapping : 'countProcTime' , type : 'int', useNull : true},
		{name : 'bean.countSuccess' , mapping : 'countSuccess' , type : 'int', useNull : true},
		{name : 'bean.countFail' , mapping : 'countFail' , type : 'int', useNull : true},
		{name : 'bean.loadSuccess' , mapping : 'loadSuccess' , type : 'int', useNull : true},
		{name : 'bean.loadFail' , mapping : 'loadFail' , type : 'int', useNull : true}
	]
});
