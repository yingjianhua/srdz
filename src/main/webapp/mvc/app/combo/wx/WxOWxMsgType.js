Ext.define('mvc.combo.wx.WxOWxMsgType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '文本消息'}
	,{value : 2,text : '图文消息'}
	,{value : 3,text : '语音消息'}
	,{value : 4,text : '视频消息'}
	,{value : 5,text : '小视频消息'}
	,{value : 6,text : '地理位置消息'}
	,{value : 7,text : '链接消息'}
	]
});