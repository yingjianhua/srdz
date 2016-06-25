Ext.define('mvc.combo.wa.WaOActSendStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '未发货'}
	,{value : 2,text : '已发货'}
	]
});