Ext.define('mvc.combo.wx.WxOStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '关注'}
	,{value : 2,text : '取消关注'}
	]
});