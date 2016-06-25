Ext.define('mvc.combo.wx.WxOSyncStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '未同步'}
	,{value : 2,text : '已同步'}
	,{value : 9,text : '已删除'}
	]
});