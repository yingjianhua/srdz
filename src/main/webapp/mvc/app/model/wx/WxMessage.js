Ext.define('mvc.model.wx.WxMessage',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxMessage_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.wxmsgType',mapping : 'wxmsgType',type : 'int',useNull : true}
	,{name : 'bean.wxmsgDir',mapping : 'wxmsgDir',type : 'int',useNull : true}
	,{name : 'bean.msgId',mapping : 'msgId',type : 'int',useNull : true}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.isReply',mapping : 'isReply',type : 'int',useNull : true}
	,{name : 'bean.isCollect',mapping : 'isCollect',type : 'int',useNull : true}
	,{name : 'bean.wxUser',mapping : 'wxUser',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});