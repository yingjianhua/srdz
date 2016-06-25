Ext.define('mvc.model.wx.WxAccount',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxAccount_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.accountName',mapping : 'accountName',type : 'string'}
	,{name : 'bean.accountToken',mapping : 'accountToken',type : 'string'}
	,{name : 'bean.accountNumber',mapping : 'accountNumber',type : 'string'}
	,{name : 'bean.accountId',mapping : 'accountId',type : 'string'}
	,{name : 'bean.accountType',mapping : 'accountType',type : 'int',useNull : true}
	,{name : 'bean.openPlat',mapping : 'openPlat',type : 'string',outkey : true}
	,{name : 'bean.agentAccount',mapping : 'agentAccount',type : 'string',outkey : true}
	,{name : 'bean.accountEmail',mapping : 'accountEmail',type : 'string'}
	,{name : 'bean.accountDesc',mapping : 'accountDesc',type : 'string'}
	,{name : 'bean.accountAppid',mapping : 'accountAppid',type : 'string'}
	,{name : 'bean.accountAppsecret',mapping : 'accountAppsecret',type : 'string'}
	,{name : 'bean.mchId',mapping : 'mchId',type : 'string'}
	,{name : 'bean.mchKey',mapping : 'mchKey',type : 'string'}
	,{name : 'bean.userSys',mapping : 'userSys',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});