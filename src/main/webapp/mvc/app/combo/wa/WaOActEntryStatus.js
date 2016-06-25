Ext.define('mvc.combo.wa.WaOActEntryStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '初始'}
	,{value : 1,text : '已审核'}
	,{value : 2,text : '不通过'}
	]
});