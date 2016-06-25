Ext.define('mvc.combo.wa.WaOActImageShape',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '方图'}
	,{value : 1,text : '横图'}
	,{value : 2,text : '竖图'}
	,{value : 3,text : '不限制'}
	]
});