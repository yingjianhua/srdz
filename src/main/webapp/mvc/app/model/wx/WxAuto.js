Ext.define('mvc.model.wx.WxAuto',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxAuto_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.keyword',mapping : 'keyword',type : 'string'}
	,{name : 'bean.msgType',mapping : 'msgType',type : 'int',useNull : true}
	,{name : 'bean.template',mapping : 'template',type : 'string',outkey : true}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});