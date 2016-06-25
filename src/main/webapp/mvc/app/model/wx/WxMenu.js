Ext.define('mvc.model.wx.WxMenu',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxMenu_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.menuUp',mapping : 'menuUp',type : 'string',outkey : true}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.msgType',mapping : 'msgType',type : 'int',useNull : true}
	,{name : 'bean.template',mapping : 'template',type : 'string',outkey : true}
	,{name : 'bean.menuKey',mapping : 'menuKey',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});