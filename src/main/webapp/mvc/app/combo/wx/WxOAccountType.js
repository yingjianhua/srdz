Ext.define('mvc.combo.wx.WxOAccountType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '服务号'}
	,{value : 2,text : '订阅号'}
	]
});