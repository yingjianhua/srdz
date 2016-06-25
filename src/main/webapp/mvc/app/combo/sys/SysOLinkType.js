Ext.define('mvc.combo.sys.SysOLinkType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '收货'}
	,{value : 2,text : '发货'}
	,{value : 3,text : '财务'}
	]
});