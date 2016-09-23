Ext.define('mvc.combo.wpt.WptOStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '微信'}
	,{value : 1,text : '现金'}
	]
});