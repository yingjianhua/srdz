Ext.define('mvc.combo.wx.WxOMotionType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '消息触发类'}
	,{value : 2,text : '网页链接类'}
	]
});