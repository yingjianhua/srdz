Ext.define('mvc.combo.wx.WxOSubMsgType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '文本消息'}
	,{value : 2,text : '图文消息'}
	]
});