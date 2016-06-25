Ext.define('mvc.model.wx.WxUser',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxUser_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.openId',mapping : 'openId',type : 'string'}
	,{name : 'bean.unionId',mapping : 'unionId',type : 'string'}
	,{name : 'bean.nickname',mapping : 'nickname',type : 'string'}
	,{name : 'bean.sex',mapping : 'sex',type : 'int',useNull : true}
	,{name : 'bean.city',mapping : 'city',type : 'string'}
	,{name : 'bean.province',mapping : 'province',type : 'string'}
	,{name : 'bean.country',mapping : 'country',type : 'string'}
	,{name : 'bean.imageUrl',mapping : 'imageUrl',type : 'string'}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.userGroup',mapping : 'userGroup',type : 'string',outkey : true}
	,{name : 'bean.subscribeTime',mapping : 'subscribeTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.syncTime',mapping : 'syncTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.syncStatus',mapping : 'syncStatus',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});