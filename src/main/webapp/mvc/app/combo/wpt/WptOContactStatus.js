Ext.define('mvc.combo.wpt.WptOContactStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '手机'}
	,{value : 1,text : '微信'}
	,{value : 2,text : 'qq'}
	,{value : 3,text : '其他'}
	]
});