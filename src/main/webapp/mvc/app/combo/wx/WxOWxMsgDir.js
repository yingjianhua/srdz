Ext.define('mvc.combo.wx.WxOWxMsgDir',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '接收消息'}
	,{value : 2,text : '回复消息'}
	]
});