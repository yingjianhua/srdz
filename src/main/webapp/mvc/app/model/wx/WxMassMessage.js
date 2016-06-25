Ext.define('mvc.model.wx.WxMassMessage',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxMassMessage_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.massmsgType',mapping : 'massmsgType',type : 'int',useNull : true}
	,{name : 'bean.template',mapping : 'template',type : 'string',outkey : true}
	,{name : 'bean.isToAll',mapping : 'isToAll',type : 'int',useNull : true}
	,{name : 'bean.userGroup',mapping : 'userGroup',type : 'string'}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.completeTime',mapping : 'completeTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.msgId',mapping : 'msgId',type : 'int',useNull : true}
	,{name : 'bean.msgDataId',mapping : 'msgDataId',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});