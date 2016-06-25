Ext.define('mvc.combo.sys.SysOBillFlag',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '开票'}
	,{value : 0,text : '不开票'}
	,{value : 3,text : '待定'}
	]
});