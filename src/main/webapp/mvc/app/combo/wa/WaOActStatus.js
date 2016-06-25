Ext.define('mvc.combo.wa.WaOActStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '未发布'}
	,{value : 2,text : '发布'}
	,{value : 3,text : '关闭'}
	]
});